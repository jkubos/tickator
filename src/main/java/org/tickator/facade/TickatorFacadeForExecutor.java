package org.tickator.facade;

import org.tickator.Tickator;

public interface TickatorFacadeForExecutor {
	String getId();
	
	static TickatorFacadeForExecutor create(Tickator tickator) {
		return new TickatorFacadeForExecutor() {
			@Override
			public String getId() {
				return tickator.getId();
			}
		};
	}
}
