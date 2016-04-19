package org.tickator;

import org.tickator.meta.PortDefinition;

public interface Port<T> {
	Ticklet getTicklet();
	PortDefinition<T> getDefinition();
}
