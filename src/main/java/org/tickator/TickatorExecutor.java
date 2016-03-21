package org.tickator;

public interface TickatorExecutor {
	void schedule(Ticklet ticklet);
	
	boolean hasScheduletTicklets();
	
	void executeTicklets();
	
	void planExecution();
	
	void sleepOnIdle();

	void markChanged(OutputPort<?> outputPort);
}
