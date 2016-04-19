package org.tickator.meta;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;
import org.tickator.Ticklet;
import org.tickator.utils.TickatorUtils;

public class PropertyDefinition<T> {
	private Class<? extends T> klass;
	private String uuid;
	private String name;

	PropertyDefinition(Class<? extends T> klass, String uuid, String name) {
		Validate.validState(TickatorUtils.isValidUuid(uuid), "Uuid must have uuid format! Got '%s'", uuid);
		
		this.klass = klass;
		this.uuid = uuid;
		this.name = name;
	}

	public T get(Ticklet ticklet) {
		return null;
	}

	public T getOrDefault(Ticklet ticklet, T defaultValue) {
		return ObjectUtils.defaultIfNull(get(ticklet), defaultValue);
	}

	void setMandatory(boolean isMandatory) {
	}

	public Class<? extends T> getKlass() {
		return klass;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}
}
