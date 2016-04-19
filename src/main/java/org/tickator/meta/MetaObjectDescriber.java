package org.tickator.meta;

import org.tickator.utils.TickatorUtils;

public class MetaObjectDescriber {

	public static String describe(TickletMetadata tickletMetadata) {
		return "ticklet "+tickletMetadata.getName();
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
