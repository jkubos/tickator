package org.tickator.meta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TickletMetadata {
	private final String name;
	
	private final TickletSetup setup;
	
	private final List<PortDefinition<?>> ports = new ArrayList<>();
	private final List<PortDefinition<?>> portsReadOny = Collections.unmodifiableList(ports);

	private List<PropertyDefinition<?>> properties = new ArrayList<>();
	private List<PropertyDefinition<?>> propertiesReadOnly = Collections.unmodifiableList(properties);
	
	TickletMetadata(String name, TickletSetup setup) {
		this.name = name;
		this.setup = setup;
	}
	
	public String getName() {
		return name;
	}
	
	public TickletSetup getSetup() {
		return setup;
	}

	public List<PortDefinition<?>> getPorts() {
		return portsReadOny;
	}

	public List<PropertyDefinition<?>> getProperties() {
		return propertiesReadOnly;
	}
	
	void addPort(PortDefinition<?> port) {
		ports.add(port);
	}
	
	void addProperty(PropertyDefinition<?> property) {
		properties.add(property);
	}
}
