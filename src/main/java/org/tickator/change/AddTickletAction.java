package org.tickator.change;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.Validate;

public class AddTickletAction {	
	private final String uuid;
	private boolean autostart = false;
	private final String klassName;
	private Map<String, Object> properties;

	public AddTickletAction(String klassName) {
		this.uuid = UUID.randomUUID().toString();
		this.klassName = klassName;
	}

	public Connector connector(String port) {
		return new Connector(this, port);
	}

	public String getUuid() {
		return uuid;
	}

	public String getKlassName() {
		return klassName;
	}

	public boolean isAutostart() {
		return autostart;
	}

	public AddTickletAction withAutostart(boolean autoschedule) {
		this.autostart = autoschedule;
		return this;
	}

	public void setProperty(String uuid, Object value) {
		if (properties==null) {
			properties = new HashMap<>();
		}
		
		Validate.validState(!properties.containsKey(uuid), "Redefining property %s! (value %s->%s)", uuid, properties.get(uuid), value);
		properties.put(uuid, value);		
	}
	
	public Map<String, Object> getProperties() {
		return properties;
	}
}
