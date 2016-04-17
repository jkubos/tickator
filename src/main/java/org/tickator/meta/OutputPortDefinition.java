package org.tickator.meta;

import org.tickator.OutputPort;
import org.tickator.Ticklet;

public class OutputPortDefinition<T> extends PortDefinition<T> {

	public OutputPortDefinition(Class<T> klass, String uuid, String name) {
		super(klass, uuid, name);
	}

	
	public OutputPort<T> create(Ticklet ticklet) {
		return new OutputPort<>(ticklet, getKlass(), getName());
	}
}
