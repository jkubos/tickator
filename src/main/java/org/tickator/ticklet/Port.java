package org.tickator.ticklet;

import org.tickator.definition.ports.PortDefinition;

public interface Port<T> {
	Ticklet getTicklet();
	PortDefinition<T> getDefinition();
}
