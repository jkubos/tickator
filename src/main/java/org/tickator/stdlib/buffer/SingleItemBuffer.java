package org.tickator.stdlib.buffer;

import org.tickator.InputPort;
import org.tickator.OutputPort;
import org.tickator.Tickator;
import org.tickator.Ticklet;
import org.tickator.meta.InputPortDefinition;
import org.tickator.meta.OutputPortDefinition;
import org.tickator.meta.TickletSetup;
import org.tickator.meta.TickletSetupBuilder;

public class SingleItemBuffer extends Ticklet {
	
	public static final InputPortDefinition<Object> DEF_PORT_INPUT = 
			new InputPortDefinition<>(Object.class, "c3dcea16-04db-11e6-af38-eb74056ee472", "input");
	
	public static final InputPortDefinition<Object> DEF_PORT_SEND = 
			new InputPortDefinition<>(Object.class, "c9e79b40-04db-11e6-ae9e-a3cb469173ef", "send");
	
	public static final OutputPortDefinition<Object> DEF_PORT_OUTPUT = 
			new OutputPortDefinition<>(Object.class, "cecd349e-04db-11e6-be9a-db2587609a64", "output");
	
	public static final TickletSetup SETUP = new TickletSetupBuilder()
			.withAutoStart(true)
			.withPropagatedType(DEF_PORT_INPUT, DEF_PORT_OUTPUT)
			.build();
	
	private final InputPort<Object> portInput = DEF_PORT_INPUT.create(this);
	private final InputPort<Object> portSend = DEF_PORT_SEND.create(this);
	private final OutputPort<Object> portOutput = DEF_PORT_OUTPUT.create(this);
	
	public SingleItemBuffer(Tickator tickator) {
		super(tickator);
	}

	@Override
	protected void execute() throws Exception {
		portSend.ifChanged(port->{
			portOutput.set(portInput.get());
		});
	}
}
