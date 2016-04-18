package org.tickator.meta;

public class TickletSetupBuilder {
	
	private TickletSetup res;

	public TickletSetupBuilder() {
		res = new TickletSetup();
	}
	
	public TickletSetup build() {
		return res;
	}
	
	public TickletSetupBuilder withAutoStart(boolean autoStart) {
		res.setAutoStart(autoStart);
		
		return this;
	}

	public TickletSetupBuilder withPropagatedType(InputPortDefinition<?> from,
			OutputPortDefinition<?> to) {
		
		
		return this;
	}
}
