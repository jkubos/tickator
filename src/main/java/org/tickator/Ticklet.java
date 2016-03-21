package org.tickator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;

public abstract class Ticklet {
	private List<Port> ports = new ArrayList<>();
	private Tickator tickator;

	public Ticklet(Tickator tickator) {
		this.tickator = tickator;
	}
	
	protected abstract void execute() throws Exception;

	void registerPort(Port port) {
		Validate.validState(ports.stream().noneMatch(other->other.equals(port.getName())), "Already defined port '%s'", port.getName());
		
		ports.add(port);
	}
	
	Port getPort(String name) {
		return ports.stream().filter(port->port.getName().equals(name)).findFirst().orElseThrow(()->new RuntimeException("Unknown port '"+name+"'"));
	}

	public Tickator getTickator() {
		return tickator;
	}
}
