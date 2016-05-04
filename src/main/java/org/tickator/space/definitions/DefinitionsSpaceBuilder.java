package org.tickator.space.definitions;

import org.apache.commons.lang3.Validate;
import org.tickator.registry.TickletsRegistry;

public class DefinitionsSpaceBuilder {

	private boolean readOnly;
	private DefinitionsSpace parent;
	private TickletsRegistry registry;

	public DefinitionsSpaceBuilder() {
	}
	
	public DefinitionsSpace build() {
		return new DefinitionsSpace(parent, registry, readOnly);
	}
	
	public DefinitionsSpaceBuilder withReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		return this;
	}
	
	public DefinitionsSpaceBuilder withParent(DefinitionsSpace parent) {
		Validate.notNull(parent);
		this.parent = parent;
		return this;
	}
	
	public DefinitionsSpaceBuilder withRegistry(TickletsRegistry registry) {
		Validate.notNull(registry);
		this.registry = registry;		
		return this;
	}

}
