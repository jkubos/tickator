package org.tickator.definition.ports;

import org.tickator.ticklet.MultiInputPort;
import org.tickator.ticklet.Ticklet;

public class MultiInputPortDefinition<T> extends PortDefinition<T> {
	public MultiInputPortDefinition(Class<T> klass, String uuid, String name) {
		super(klass, uuid, name);
	}

	
	public MultiInputPort<T> create(Ticklet ticklet) {
		return new MultiInputPort<T>(ticklet, this);
	}
}
