package org.tickator.exec;

import org.tickator.ticklet.OutputPort;
import org.tickator.ticklet.Ticklet;

public interface ExectutionStrategy {
	void schedule(Ticklet ticklet);
	
	boolean hasScheduletTicklets();
	
	void executeTicklets();
	
	void planExecution();
	
	void sleepOnIdle();

	void markChanged(OutputPort<?> outputPort);
	
	boolean isExecuted(Ticklet ticklet);
}
