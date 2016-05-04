package org.tickator.space.definitions;

import java.util.List;

import org.tickator.definition.component.ComponentDefinition;
import org.tickator.registry.TickletsRegistry;

public class DefinitionsSpace {
	private DefinitionsSpace parent;
	private TickletsRegistry registry;
	private boolean readOnly;
	private List<ComponentDefinition> componentDefinitions;

	DefinitionsSpace(DefinitionsSpace parent, TickletsRegistry registry, boolean readOnly) {
		this.parent = parent;
		this.registry = registry;
		this.readOnly = readOnly;
	}
	
	public void add(ComponentDefinition componentDefinition) {
		componentDefinitions.add(componentDefinition);
	}
}
