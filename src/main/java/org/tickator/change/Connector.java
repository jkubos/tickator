package org.tickator.change;

public class Connector {
	private AddTickletAction addTickletAction;
	private String port;

	public Connector(AddTickletAction addTickletAction, String port) {
		this.addTickletAction = addTickletAction;
		this.port = port;
	}

	public AddTickletAction getAddTickletAction() {
		return addTickletAction;
	}

	public String getTickletUuid() {
		return addTickletAction.getUuid();
	}

	public String getPort() {
		return port;
	}
}
