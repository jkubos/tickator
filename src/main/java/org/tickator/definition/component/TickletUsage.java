package org.tickator.definition.component;

import org.apache.commons.lang3.Validate;

public class TickletUsage {
	private String uuid;
	private String tickletClass;

	public TickletUsage(String uuid, String tickletClass) {
		Validate.notBlank(uuid);
		Validate.notBlank(tickletClass);
		
		this.uuid = uuid;
		this.tickletClass = tickletClass;
	}

	public String getUuid() {
		return uuid;
	}

	public String getTickletClass() {
		return tickletClass;
	}	
}
