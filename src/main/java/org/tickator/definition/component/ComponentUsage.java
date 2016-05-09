package org.tickator.definition.component;

import org.apache.commons.lang3.Validate;

public class ComponentUsage {

	private String uuid;
	private String componentUuid;

	public ComponentUsage(String uuid, String componentUuid) {
		Validate.notBlank(uuid);
		Validate.notBlank(componentUuid);
		
		this.uuid = uuid;
		this.componentUuid = componentUuid;
	}

	public String getUuid() {
		return uuid;
	}

	public String getComponentUuid() {
		return componentUuid;
	}
}
