package org.tickator.meta;

public class TickletSetupBuilder {
	
	private TickletSetup res;

	public TickletSetupBuilder() {
		res = new TickletSetup();
	}
	
	public TickletSetupBuilder withAutoStart(boolean autoStart) {
		res.setAutoStart(autoStart);
		
		return this;
	}
	
	public TickletSetup build() {
		return res;
	}
}
