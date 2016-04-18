package org.tickator.meta;

import java.util.ArrayList;
import java.util.List;

public class TickletMetadata {
	private final String name;
	
	private final List<PortDefinition<?>> ports = new ArrayList<>();
	
	private final TickletSetup setup;

	private List<PropertyDefinition<?>> properties = new ArrayList<>();
	
	TickletMetadata(String name, TickletSetup setup) {
		this.name = name;
		this.setup = setup;
	}
	
	void addPort(PortDefinition<?> port) {
		ports.add(port);
	}
	
	void addProperty(PropertyDefinition<?> property) {
		properties.add(property);
	}
}
