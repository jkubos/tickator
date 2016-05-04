package org.tickator.facade;

import org.tickator.space.ticklets.TickletsSpace;

public interface TickletsSpaceFacadeForExecutor {
	void applyChanges();
	
	public static TickletsSpaceFacadeForExecutor create(TickletsSpace tickletsSpace) {
		return new TickletsSpaceFacadeForExecutor() {
			
			@Override
			public void applyChanges() {
				tickletsSpace.applyChanges();
			}
		};
	}
}
