package org.tickator.exec;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.Validate;
import org.tickator.ticklet.OutputPort;
import org.tickator.ticklet.Ticklet;
import org.tickator.utils.TickatorUtils;

public class SingleThreadedExecutionStrategy implements ExectutionStrategy {
	
	private Set<Ticklet> currentTodo = new HashSet<>();
	private Set<Ticklet> futureTodo = new HashSet<>();
	private List<OutputPort<?>> changedOutputs = new ArrayList<>();
	private volatile boolean executing;
	private volatile Ticklet executedTicklet;

	@Override
	public void schedule(Ticklet ticklet) {
		currentTodo.add(ticklet);
	}

	@Override
	public boolean hasScheduletTicklets() {
		return currentTodo.size()>0;
	}

	@Override
	public void executeTicklets() {
		executing = true;
		
		TickatorUtils.withRuntimeException(()->{
			for (Ticklet ticklet : currentTodo) {
				executedTicklet = ticklet;
				ticklet.executeWithCheck();
			}

			executedTicklet = null;
		});
		
		executing = false;
	}

	@Override
	public void planExecution() {
		Set<Ticklet> tmp = currentTodo;
		currentTodo = futureTodo;
		futureTodo = tmp;
		
		futureTodo.clear();
		
		changedOutputs.forEach(output->{
			output.getDepending().forEach(input->{
				currentTodo.add(input.getTicklet());
			});
		});
		
		changedOutputs.clear();
	}

	@Override
	public void sleepOnIdle() {
		TickatorUtils.sleepMillis(1);
	}

	@Override
	public void markChanged(OutputPort<?> outputPort) {
		Validate.validState(executing, "This method cannot be called outside executing!");
		
		changedOutputs.add(outputPort);
	}

	@Override
	public boolean isExecuted(Ticklet ticklet) {
		return ticklet==executedTicklet;
	}
}
