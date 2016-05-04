package org.tickator.definition.component;

import java.util.List;

import org.tickator.definition.ports.PortDefinition;

public class ComponentDefinition {
	private String name;
	private List<ComponentImplementation> implementations;
	private List<PortDefinition<?>> ports;
	
	public ComponentDefinition(String name) {
	}
	
	public void addPort(PortDefinition<?> port) {
		ports.add(port);
		
	}
	
	public void addImplementation() {
		
	}
}
