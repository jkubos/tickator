package org.tickator.definition.ports;

import org.tickator.ticklet.InputPort;
import org.tickator.ticklet.Ticklet;

public class InputPortDefinition<T> extends PortDefinition<T> {
	
	public InputPortDefinition(Class<T> klass, String uuid, String name) {
		super(klass, uuid, name);
	}
	
	public InputPort<T> create(Ticklet ticklet) {
		return new InputPort<>(ticklet, this);
	}
}
