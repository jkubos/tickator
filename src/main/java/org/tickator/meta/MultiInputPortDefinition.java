package org.tickator.meta;

import org.tickator.MultiInputPort;
import org.tickator.Ticklet;

public class MultiInputPortDefinition<T> extends PortDefinition<T> {
	public MultiInputPortDefinition(Class<T> klass, String uuid, String name) {
		super(klass, uuid, name);
	}

	
	public MultiInputPort<T> create(Ticklet ticklet) {
		return new MultiInputPort<T>(ticklet, this);
	}
}
