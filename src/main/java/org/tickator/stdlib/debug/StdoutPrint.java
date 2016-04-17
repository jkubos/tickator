package org.tickator.stdlib.debug;

import org.tickator.MultiInputPort;
import org.tickator.Tickator;
import org.tickator.Ticklet;
import org.tickator.meta.MultiInputPortDefinition;
import org.tickator.meta.PropertyDefinition;
import org.tickator.meta.PropertyDefinitionBuilder;

public class StdoutPrint extends Ticklet {
	
	public static final MultiInputPortDefinition<Object> DEF_PORT_INPUT = 
			new MultiInputPortDefinition<>(Object.class, "5a4e29a6-001d-11e6-b127-9beb6f16e8ec", "input");
	
	public static final PropertyDefinition<Boolean> PROP_PRINT_NEW_LINES = 
			new PropertyDefinitionBuilder<>(Boolean.class, "dc19b176-001d-11e6-95ef-976be04fe8a4", "Print new lines")
			.build();
	
	private final MultiInputPort<Object> portInput = DEF_PORT_INPUT.create(this);
	private final boolean propertyPrintNewLines = PROP_PRINT_NEW_LINES.getOrDefault(this, true);

	public StdoutPrint(Tickator tickator) {
		super(tickator);
	}

	@Override
	protected void execute() throws Exception {
		portInput.forEachChanged(propertyPrintNewLines ? System.out::println : System.out::print);
	}

}
