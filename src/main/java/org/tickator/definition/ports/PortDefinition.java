package org.tickator.definition.ports;

import org.apache.commons.lang3.Validate;
import org.tickator.utils.TickatorUtils;

public class PortDefinition<T> {

	private String uuid;
	private String name;
	private Class<T> klass;

	public PortDefinition(Class<T> klass, String uuid, String name) {
		Validate.validState(TickatorUtils.isValidUuid(uuid), "Uuid must have uuid format! Got '%s'", uuid);
		
		this.klass = klass;
		this.uuid = uuid;
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public String getName() {
		return name;
	}
	
	public Class<T> getKlass() {
		return klass;
	}
}
