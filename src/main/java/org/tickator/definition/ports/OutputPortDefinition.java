package org.tickator.definition.ports;

import org.tickator.ticklet.OutputPort;
import org.tickator.ticklet.Ticklet;

public class OutputPortDefinition<T> extends PortDefinition<T> {

	public OutputPortDefinition(Class<T> klass, String uuid, String name) {
		super(klass, uuid, name);
	}

	
	public OutputPort<T> create(Ticklet ticklet) {
		return new OutputPort<>(ticklet, this);
	}
}
