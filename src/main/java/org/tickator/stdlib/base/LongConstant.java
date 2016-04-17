package org.tickator.stdlib.base;

import org.tickator.OutputPort;
import org.tickator.Tickator;
import org.tickator.Ticklet;
import org.tickator.meta.OutputPortDefinition;
import org.tickator.meta.PropertyDefinition;
import org.tickator.meta.PropertyDefinitionBuilder;
import org.tickator.meta.TickletSetup;
import org.tickator.meta.TickletSetupBuilder;

public class LongConstant extends Ticklet {
	
	public static final OutputPortDefinition<Long> DEF_PORT_OUTPUT = 
			new OutputPortDefinition<>(Long.class, "83e477d8-001e-11e6-9407-e74f8dbdafbd", "output");
	
	public static final PropertyDefinition<Long> PROP_VALUE = 
			new PropertyDefinitionBuilder<>(Long.class, "dc19b176-001d-11e6-95ef-976be04fe8a4", "Value")
			.withMandatory(true)
			.build();
	
	public static final TickletSetup SETUP = new TickletSetupBuilder()
			.withAutoStart(true)
			.build();
		
	private final OutputPort<Long> portOutput = DEF_PORT_OUTPUT.create(this);
	private final long value = PROP_VALUE.get(this);

	public LongConstant(Tickator tickator) {
		super(tickator);
	}

	@Override
	protected void execute() throws Exception {
		portOutput.set(value);
	}

}
