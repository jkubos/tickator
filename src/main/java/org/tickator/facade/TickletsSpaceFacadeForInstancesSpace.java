package org.tickator.facade;

import org.tickator.space.ticklets.TickletsSpace;
import org.tickator.space.ticklets.TickletsSpaceChange;

public interface TickletsSpaceFacadeForInstancesSpace {
	
	void addChange(TickletsSpaceChange change);

	static TickletsSpaceFacadeForInstancesSpace create(TickletsSpace tickletsSpace) {
		return new TickletsSpaceFacadeForInstancesSpace() {
			
			@Override
			public void addChange(TickletsSpaceChange change) {
				tickletsSpace.addChange(change);
			}
		};
	}

}
