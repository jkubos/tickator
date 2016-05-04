package org.tickator;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tickator.exec.ExectutionStrategy;
import org.tickator.exec.TickatorExecutor;
import org.tickator.facade.TickatorFacadeForExecutor;
import org.tickator.facade.TickletsSpaceFacadeForExecutor;
import org.tickator.facade.TickletsSpaceFacadeForInstancesSpace;
import org.tickator.space.definitions.DefinitionsSpace;
import org.tickator.space.definitions.DefinitionsSpaceBuilder;
import org.tickator.space.instances.InstancesSpace;
import org.tickator.space.ticklets.TickletsSpace;

public class Tickator {
	private static final Logger logger = LoggerFactory.getLogger(Tickator.class);

	private String id;
	
	private final TickatorExecutor executor;
	
	public Tickator(String id, DefinitionsSpace definitionsSpace, ExectutionStrategy executionStrategy) {
		Validate.notBlank(id);
		
		logger.debug("Instantiating Tickator {}", id);
		
		this.id = id;
		
		Validate.notNull(definitionsSpace);
		Validate.notNull(executionStrategy);
		
		TickletsSpace tickletsSpace = new TickletsSpace();
		
		InstancesSpace instancesSpace = new InstancesSpace(TickletsSpaceFacadeForInstancesSpace.create(tickletsSpace));
		
		DefinitionsSpaceBuilder dynamicDefinitionsSpaceBuilder = new DefinitionsSpaceBuilder()
			.withReadOnly(true)
			.withParent(definitionsSpace);
		
		DefinitionsSpace dynamicDefinitionsSpace = dynamicDefinitionsSpaceBuilder.build();
		
		executor = new TickatorExecutor(executionStrategy, 
				TickatorFacadeForExecutor.create(this), 
				TickletsSpaceFacadeForExecutor.create(tickletsSpace));
	}
	
	/**
	 * Id of tickator instance.
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Start this tickator. Can be called only once per instance.
	 */
	public void start() {
		executor.start();
	}

	/**
	 * Shutdown this instance. This methods waits synchronously for end of current tick.
	 */
	public void shutdown() {
		executor.shutdown();
	}
}
