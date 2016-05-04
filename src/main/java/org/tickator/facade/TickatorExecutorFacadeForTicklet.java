package org.tickator.facade;

import org.apache.commons.lang3.Validate;
import org.tickator.exec.TickatorExecutor;
import org.tickator.ticklet.OutputPort;
import org.tickator.ticklet.Ticklet;

public interface TickatorExecutorFacadeForTicklet {
	
	void scheduleAsync();
	
	long getTick();
	
	void markChanged(OutputPort<?> port);
	
	boolean isExecuted();
	
	static TickatorExecutorFacadeForTicklet create(TickatorExecutor executor, Ticklet ticklet) {
		return new TickatorExecutorFacadeForTicklet() {
			
			@Override
			public void scheduleAsync() {
				executor.scheduleAsync(ticklet);
			}

			@Override
			public long getTick() {
				return executor.getTick();
			}

			@Override
			public void markChanged(OutputPort<?> outputPort) {
				Validate.validState(outputPort.getTicklet()==ticklet);
				
				executor.markChanged(outputPort);
			}

			@Override
			public boolean isExecuted() {
				return executor.isExecuted(ticklet);
			}
		};
	}
}
