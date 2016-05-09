package org.tickator.definition.component;

import java.util.Collections;
import java.util.List;

import org.tickator.definition.ports.PortDefinition;

public class ComponentDefinition {
	private String uuid;
	private String name;
	private List<ComponentImplementation> componentImplementations;
	private List<PortDefinition<?>> ports;
	
	ComponentDefinition(String uuid, String name, List<ComponentImplementation> componentImplementations, List<PortDefinition<?>> ports) {
		this.uuid = uuid;
		this.name = name;
		this.componentImplementations = Collections.unmodifiableList(componentImplementations);
		this.ports = Collections.unmodifiableList(ports);
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}

	public List<ComponentImplementation> getComponentImplementations() {
		return componentImplementations;
	}

	public List<PortDefinition<?>> getPorts() {
		return ports;
	}
}
