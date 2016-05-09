package org.tickator.utils;

import org.tickator.definition.ports.InputPortDefinition;
import org.tickator.definition.ports.MultiInputPortDefinition;
import org.tickator.definition.ports.OutputPortDefinition;
import org.tickator.definition.ports.PortDefinition;
import org.tickator.definition.properties.PropertyDefinition;
import org.tickator.registry.TickletMetadata;

@Deprecated
public class MetaObjectDescriber {

	public static String describe(TickletMetadata tickletMetadata) {
		return String.format("ticklet %s [%s]", tickletMetadata.getName(), tickletMetadata.getKlass().getClassLoader());
	}
	
	public static String describe(TickletMetadata tickletMetadata, PortDefinition<?> port) {
		String type = null;
		
		if (port instanceof InputPortDefinition<?>) {
			type = "input";
		} else if (port instanceof MultiInputPortDefinition<?>) {
			type = "multi input";
		} else if (port instanceof OutputPortDefinition<?>) {
			type = "output";
		} else {
			TickatorUtils.throwRuntime("Unknown port type %s!", port.getClass().getName());
		}
		
		return String.format("%s port [%s:%s]", type, tickletMetadata.getName(), port.getName());
	}

	public static String describe(TickletMetadata tickletMetadata, PropertyDefinition<?> property) {
		return String.format("property [%s:%s]", tickletMetadata.getName(), property.getName());
	}
}
