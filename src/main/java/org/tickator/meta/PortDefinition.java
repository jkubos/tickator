package org.tickator.meta;

public class PortDefinition<T> {

	private String uuid;
	private String name;
	private Class<T> klass;

	public PortDefinition(Class<T> klass, String uuid, String name) {
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
