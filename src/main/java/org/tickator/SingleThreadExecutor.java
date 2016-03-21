package org.tickator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.tickator.utils.TickatorUtils;

public class SingleThreadExecutor implements TickatorExecutor {
	
	private Set<Ticklet> currentTodo = new HashSet<>();
	private Set<Ticklet> futureTodo = new HashSet<>();
	private List<OutputPort<?>> changedOutputs = new ArrayList<>();

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
		TickatorUtils.withRuntimeException(()->{
			for (Ticklet ticklet : currentTodo) {
				ticklet.execute();
			}
		});
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
		changedOutputs.add(outputPort);
	}
}
