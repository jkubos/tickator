package org.tickator.meta;

import org.apache.commons.lang3.ObjectUtils;
import org.tickator.Ticklet;

public class PropertyDefinition<T> {
	PropertyDefinition(Class<? extends T> klass, String uuid, String name) {
	}

	public T get(Ticklet ticklet) {
		return null;
	}

	public T getOrDefault(Ticklet ticklet, T defaultValue) {
		return ObjectUtils.defaultIfNull(get(ticklet), defaultValue);
	}

	void setMandatory(boolean isMandatory) {
	}
}
