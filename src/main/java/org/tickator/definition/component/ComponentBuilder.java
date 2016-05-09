package org.tickator.definition.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.tickator.definition.ports.InputPortDefinition;
import org.tickator.definition.ports.MultiInputPortDefinition;
import org.tickator.definition.ports.OutputPortDefinition;
import org.tickator.definition.ports.PortDefinition;

public class ComponentBuilder {

	private boolean builded;
	private String uuid;
	private String name;
	private List<ComponentImplementation> componentImplementations = new ArrayList<>();
	private List<PortDefinition<?>> ports = new ArrayList<>();

	public static ComponentBuilder start(String uuid, String name) {
		return new ComponentBuilder(uuid, name);
	}
	
	private ComponentBuilder(String uuid, String name) {
		Validate.notBlank(uuid);
		Validate.notBlank(name);
		
		this.uuid = uuid;
		this.name = name;
	}

	public ComponentBuilder addImplementation(ComponentImplementation componentImplementation) {
		Validate.notNull(componentImplementation);
		componentImplementations.add(componentImplementation);
		return this;
	}
	
	public <T> ComponentBuilder addInputPort(Class<T> klass, String portUuid, String portName) {
		Validate.notNull(klass);
		Validate.notBlank(portUuid);
		Validate.notBlank(portName);
		
		ports.add(new InputPortDefinition<>(klass, portUuid, portName));
		return this;
	}
	
	public <T> ComponentBuilder addMultiInputPort(Class<T> klass, String portUuid, String portName) {
		Validate.notNull(klass);
		Validate.notBlank(portUuid);
		Validate.notBlank(portName);
		
		ports.add(new MultiInputPortDefinition<>(klass, portUuid, portName));
		return this;
	}
	
	public <T> ComponentBuilder addOutputPort(Class<T> klass, String portUuid, String portName) {
		Validate.notNull(klass);
		Validate.notBlank(portUuid);
		Validate.notBlank(portName);
		
		ports.add(new OutputPortDefinition<>(klass, portUuid, portName));
		return this;
	}
	
	public ComponentDefinition build() {
		Validate.validState(!builded, "This builder was already used.");
		builded = true;
		validate();
		
		return new ComponentDefinition(uuid, name, componentImplementations, ports);
	}

	private void validate() {
		
	}

}
