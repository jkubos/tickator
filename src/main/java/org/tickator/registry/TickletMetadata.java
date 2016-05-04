package org.tickator.registry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.tickator.definition.ports.PortDefinition;
import org.tickator.definition.properties.PropertyDefinition;
import org.tickator.ticklet.Ticklet;

public class TickletMetadata {
	private final String name;
	
	private final TickletSetup setup;
	
	private final List<PortDefinition<?>> ports = new ArrayList<>();
	private final List<PortDefinition<?>> portsReadOny = Collections.unmodifiableList(ports);

	private List<PropertyDefinition<?>> properties = new ArrayList<>();
	private List<PropertyDefinition<?>> propertiesReadOnly = Collections.unmodifiableList(properties);

	private Class<? extends Ticklet> klass;
	
	TickletMetadata(String name, Class<? extends Ticklet> klass, TickletSetup setup) {
		this.name = name;
		this.setup = setup;
		this.klass = klass;
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

	public Class<? extends Ticklet> getKlass() {
		return klass;
	}

	public boolean hasPort(PortDefinition<?> definition) {
		return ports.contains(definition);
	}
}
