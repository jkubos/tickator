package org.tickator.change;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.tickator.Ticklet;

public class AddTickletAction {

	private static final List<Class<?>> EMPTY_LIST = Collections.unmodifiableList(new ArrayList<>());
	
	private String uuid;
	private Class<? extends Ticklet> klass;
	private List<Class<?>> templateArgs;
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

	public void addTemplateArg(Class<?> klass) {
		if (templateArgs==null) {
			templateArgs = new ArrayList<>();
		}		
		
		templateArgs.add(klass);
	}
	
	public List<Class<?>> getTemplateArgs() {
		return templateArgs==null ? EMPTY_LIST : templateArgs;
	}

	public boolean isAutostart() {
		return autostart;
	}

	public void setAutostart(boolean autoschedule) {
		this.autostart = autoschedule;
	}
}
