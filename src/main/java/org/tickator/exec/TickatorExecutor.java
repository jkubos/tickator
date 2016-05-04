package org.tickator.exec;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.tickator.facade.TickatorFacadeForExecutor;
import org.tickator.facade.TickletsSpaceFacadeForExecutor;
import org.tickator.ticklet.OutputPort;
import org.tickator.ticklet.Ticklet;
import org.tickator.utils.TickatorUtils;

public class TickatorExecutor {
	private static final Logger logger = LoggerFactory.getLogger(TickatorExecutor.class);
	
	private volatile long tick;
	
	private final Thread controlThread = new Thread(this::execute);

	private final Set<Ticklet> asyncScheduleRequests = new HashSet<>();
	
	private volatile boolean started;

	private final ExectutionStrategy executionStrategy;

	private TickletsSpaceFacadeForExecutor tickletsSpaceFacade;

	private TickatorFacadeForExecutor tickatorFacade;
	
	public TickatorExecutor(ExectutionStrategy executionStrategy, TickatorFacadeForExecutor tickatorFacade, 
			TickletsSpaceFacadeForExecutor tickletsSpaceFacade) {
		Validate.notNull(executionStrategy);
		Validate.notNull(tickatorFacade);
		Validate.notNull(tickletsSpaceFacade);
		
		this.executionStrategy = executionStrategy;
		this.tickatorFacade = tickatorFacade;
		this.tickletsSpaceFacade = tickletsSpaceFacade;
	}
	
	public void start() {
		if (started) {
			throw new RuntimeException("Already started instance of Tickator. One instance can be executed only once.");
		}
		
		started = true;
		
		tickletsSpaceFacade.applyChanges();
		
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
	
	public long getTick() {
		return tick;
	}
	
	public void scheduleAsync(Ticklet ticklet) {
		synchronized (asyncScheduleRequests) {
			asyncScheduleRequests.add(ticklet);
		}		
	}

	/**
	 * Execution loop of 
	 */
	private void execute() {
		try {
			MDC.put("tickator", tickatorFacade.getId());
			
			logger.debug("Starting control thread");
			
			while (!Thread.interrupted()) {
				phase1();
				
				if (executionStrategy.hasScheduletTicklets()) {
					System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"+tick+"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
					phase2();
					phase3();
					phase4();
					//System.out.println("#########################################################################");
					++tick;
				} else {
					executionStrategy.sleepOnIdle();
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
			asyncScheduleRequests.forEach(executionStrategy::schedule);
			asyncScheduleRequests.clear();
		}
	}

	/**
	 * Execute ticklets
	 */
	private void phase2() {
		executionStrategy.executeTicklets();
	}

	/**
	 * Update schema
	 */
	private void phase3() {
		tickletsSpaceFacade.applyChanges();
	}

	/**
	 * Update outputs
	 */
	private void phase4() {
		executionStrategy.planExecution();
	}

	public void markChanged(OutputPort<?> outputPort) {
		executionStrategy.markChanged(outputPort);
	}

	public boolean isExecuted(Ticklet ticklet) {
		return executionStrategy.isExecuted(ticklet);
	}
}
