package org.tickator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.tickator.change.AddTickletAction;
import org.tickator.change.ChangeScope;
import org.tickator.change.ConnectAction;
import org.tickator.meta.TickletMetadata;
import org.tickator.meta.TickletsRegistry;
import org.tickator.utils.TickatorUtils;

public class Tickator {
	private static final Logger logger = LoggerFactory.getLogger(Tickator.class);
	
	private volatile long tick;
	
	private final Thread controlThread = new Thread(this::execute);

	private volatile boolean started;

	private TickatorExecutor executor;
	
	private final Set<Ticklet> asyncScheduleRequests = new HashSet<>();
	
	private TickletsRegistry tickletsRegistry;

	private String id;
	
	public Tickator(String id, TickatorExecutor executor, TickletsRegistry tickletsRegistry) {
		logger.debug("Instantiating Tickator {}", id);
		this.id = id;
		this.executor = executor;
		this.tickletsRegistry = tickletsRegistry;
	}
	
	public void start() {
		start(null);
	}
	
	public void start(ChangeScope createAtStartup) {
		if (started) {
			throw new RuntimeException("Already started instance of Tickator. One instance can be executed only once.");
		}
		
		started = true;
		
		Set<Ticklet> tickletsToExecute = new HashSet<>();
		Map<String, Ticklet> ticklets = new HashMap<>();
		
		if (createAtStartup!=null) {
			applyChange(createAtStartup, tickletsToExecute, ticklets);
		}
		
		controlThread.start();
	}

	/**
	 * Wait for termination of Tickator instance. 
	 */
	public void waitForFinish() {
		while (controlThread.isAlive()) {
			TickatorUtils.sleepMillis(100);
		}
	}

	/**
	 * Ask for shutdown of this Tickator and wait for termination. Shutdown is performed after finishing current tick.
	 */
	public void shutdown() {
		controlThread.interrupt();
		waitForFinish();
	}
	
	public TickletsRegistry getTickletsRegistry() {
		return tickletsRegistry;
	}

	long getTick() {
		return tick;
	}
	
	void scheduleAsync(Ticklet ticklet) {
		synchronized (asyncScheduleRequests) {
			asyncScheduleRequests.add(ticklet);
		}		
	}
	
	/**
	 * Execution loop of 
	 */
	private void execute() {
		try {
			MDC.put("tickator", id);
			
			logger.debug("Starting control thread");
			
			while (!Thread.interrupted()) {
				phase1();
				
				if (executor.hasScheduletTicklets()) {
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+tick+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					phase2();
					phase3();
					phase4();
					//System.out.println("#########################################################################");
					++tick;
				} else {
					executor.sleepOnIdle();
				}			
			}	
		} catch (Exception e) {
			logger.error("Tickator main thread exiting due to exception!", e);
			System.exit(-1);
		}
	}

	/**
	 * Add asynchronous request for execution.
	 */
	private void phase1() {
		synchronized (asyncScheduleRequests) {
			asyncScheduleRequests.forEach(executor::schedule);
			asyncScheduleRequests.clear();
		}
	}

	/**
	 * Execute ticklets
	 */
	private void phase2() {
		executor.executeTicklets();
	}

	/**
	 * Update schema
	 */
	private void phase3() {
	}

	/**
	 * Update outputs
	 */
	private void phase4() {
		executor.planExecution();
	}

	/**
	 * Apply change defined by scope
	 * @param scope
	 * @param tickletsToExecute 
	 * @param ticklets 
	 */
	private void applyChange(ChangeScope scope, Set<Ticklet> tickletsToExecute, Map<String, Ticklet> ticklets) {
		TickatorUtils.withRuntimeException(()->{
			for (AddTickletAction addTickletAction : scope.getAddTickletActions()) {
				TickletMetadata tickletMetadata = tickletsRegistry.lookup(addTickletAction.getKlassName());
				Ticklet ticklet = tickletMetadata.getKlass().getConstructor(new Class<?>[]{getClass(), Map.class}).newInstance(new Object[]{this, addTickletAction.getProperties()});

				ticklet.validate();
				ticklets.put(addTickletAction.getUuid(), ticklet);
				
				if (addTickletAction.isAutostart()) {
					executor.schedule(ticklet);
				}
			}
			
			for (ChangeScope addScope : scope.getAddScopes()) {
				applyChange(addScope, tickletsToExecute, ticklets);
			}
			
			for (ConnectAction connectAction : scope.getConnectActions()) {
				Ticklet a = ticklets.get(connectAction.getA().getTickletUuid());
				Validate.notNull(a);
				
				Ticklet b = ticklets.get(connectAction.getB().getTickletUuid());
				Validate.notNull(b);
				
				int todo;
//				connect(a, connectAction.getA().getPort(), b, connectAction.getB().getPort());
			}
		});		
	}

//	private void connect(Ticklet a, String portNameA, Ticklet b, String portNameB) {
//		Port<?> portA = a.getPort(portNameA);
//		Port<?> portB = b.getPort(portNameB);
//		
//		OutputPort<?> output;
//		
//		Connectable<?> input;
//		
//		if (portA instanceof Connectable<?> && portB instanceof OutputPort<?>) {
//			output = (OutputPort<?>) portB;
//			input = (Connectable<?>) portA;
//		} else if (portA instanceof OutputPort<?> && portB instanceof Connectable<?>) {
//			output = (OutputPort<?>) portA;
//			input = (Connectable<?>) portB;
//		} else {
//			throw new RuntimeException(String.format("Cannot connect %s:'%s':%s to %s:'%s':%s", 
//					a.getClass().getName(), portNameA, portA.getClass().getSimpleName(), 
//					b.getClass().getName(), portNameB, portB.getClass().getSimpleName()));
//		}
//	
//		input.connect(output);
//	}

	public TickatorExecutor getExecutor() {
		return executor;
	}
}
