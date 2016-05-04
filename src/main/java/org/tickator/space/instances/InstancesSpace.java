package org.tickator.space.instances;

import org.apache.commons.lang3.Validate;
import org.tickator.facade.TickletsSpaceFacadeForInstancesSpace;

public class InstancesSpace {

	private TickletsSpaceFacadeForInstancesSpace tickletsSpaceFacadeForInstancesSpace;

	public InstancesSpace(TickletsSpaceFacadeForInstancesSpace tickletsSpaceFacadeForInstancesSpace) {
		Validate.notNull(tickletsSpaceFacadeForInstancesSpace);
		
		this.tickletsSpaceFacadeForInstancesSpace = tickletsSpaceFacadeForInstancesSpace;
	}

}
