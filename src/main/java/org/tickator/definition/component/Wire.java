package org.tickator.definition.component;

import org.apache.commons.lang3.Validate;

public class Wire {
	private String uuid;
	private String uuidA;
	private String portUuidA;
	private String uuidB;
	private String portUuidB;

	public Wire(String uuid, String uuidA, String portUuidA, String uuidB, String portUuidB) {
		Validate.notBlank(uuid);
		Validate.notBlank(uuidA);
		Validate.notBlank(portUuidA);
		Validate.notBlank(uuidB);
		Validate.notBlank(portUuidB);
		
		this.uuid = uuid;
		this.uuidA = uuidA;
		this.portUuidA = portUuidA;
		this.uuidB = uuidB;
		this.portUuidB = portUuidB;
	}

	public String getUuid() {
		return uuid;
	}

	public String getUuidA() {
		return uuidA;
	}

	public String getPortUuidA() {
		return portUuidA;
	}

	public String getUuidB() {
		return uuidB;
	}

	public String getPortUuidB() {
		return portUuidB;
	}
}
