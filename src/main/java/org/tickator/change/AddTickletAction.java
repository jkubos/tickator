package org.tickator.change;

import java.util.UUID;

import org.tickator.Ticklet;

public class AddTickletAction {
	private String uuid;
	private Class<? extends Ticklet> klass;
	private boolean autostart = false;

	public AddTickletAction(Class<? extends Ticklet> klass) {
		this.uuid = UUID.randomUUID().toString();
		this.klass = klass;
	}

	public Connector connector(String port) {
		return new Connector(this, port);
	}

	public String getUuid() {
		return uuid;
	}

	public Class<? extends Ticklet> getKlass() {
		return klass;
	}

	public boolean isAutostart() {
		return autostart;
	}

	public void setAutostart(boolean autoschedule) {
		this.autostart = autoschedule;
	}
}
