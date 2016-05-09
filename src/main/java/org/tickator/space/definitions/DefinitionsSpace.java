package org.tickator.space.definitions;

import java.util.List;

import org.tickator.definition.component.ComponentDefinition;
import org.tickator.registry.TickletsRegistry;

public class DefinitionsSpace {
	private TickletsRegistry registry;
	private List<ComponentDefinition> componentDefinitions;

	DefinitionsSpace(TickletsRegistry registry) {
		this.registry = registry;
	}
	
	public void add(ComponentDefinition componentDefinition) {
		componentDefinitions.add(componentDefinition);
	}
}
