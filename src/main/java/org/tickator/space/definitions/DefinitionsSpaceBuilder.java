package org.tickator.space.definitions;

import org.apache.commons.lang3.Validate;
import org.tickator.registry.TickletsRegistry;

public class DefinitionsSpaceBuilder {

	private TickletsRegistry registry;

	public DefinitionsSpaceBuilder() {
	}
	
	public DefinitionsSpaceBuilder withRegistry(TickletsRegistry registry) {
		Validate.notNull(registry);
		this.registry = registry;		
		return this;
	}
	
	public DefinitionsSpace build() {
		return new DefinitionsSpace(registry);
	}
}
