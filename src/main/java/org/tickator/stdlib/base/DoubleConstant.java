package org.tickator.stdlib.base;

import org.tickator.OutputPort;
import org.tickator.Tickator;
import org.tickator.Ticklet;
import org.tickator.meta.OutputPortDefinition;
import org.tickator.meta.PropertyDefinition;
import org.tickator.meta.PropertyDefinitionBuilder;
import org.tickator.meta.TickletSetup;
import org.tickator.meta.TickletSetupBuilder;

public class DoubleConstant extends Ticklet {
	
	public static final OutputPortDefinition<Double> DEF_PORT_OUTPUT = 
			new OutputPortDefinition<>(Double.class, "3109de60-04db-11e6-b617-b7761a185122", "output");
	
	public static final PropertyDefinition<Double> PROP_VALUE = 
			new PropertyDefinitionBuilder<>(Double.class, "3749ac60-04db-11e6-8801-7f310e5c78a6", "Value")
			.withMandatory(true)
			.build();
	
	public static final TickletSetup SETUP = new TickletSetupBuilder()
			.withAutoStart(true)
			.build();
		
	private final OutputPort<Double> portOutput = DEF_PORT_OUTPUT.create(this);
	private final double value = PROP_VALUE.get(this);

	public DoubleConstant(Tickator tickator) {
		super(tickator);
	}

	@Override
	protected void execute() throws Exception {
		portOutput.set(value);
	}

}
