package org.tickator.stdlib.arithmetic;

import org.tickator.InputPort;
import org.tickator.OutputPort;
import org.tickator.Tickator;
import org.tickator.Ticklet;
import org.tickator.meta.InputPortDefinition;
import org.tickator.meta.OutputPortDefinition;

public class LongAdder extends Ticklet {
	
	public static final InputPortDefinition<Long> DEF_PORT_A = 
			new InputPortDefinition<>(Long.class, "fd72dd6c-0017-11e6-a87c-53e6fa398123", "A");
	
	public static final InputPortDefinition<Long> DEF_PORT_B = 
			new InputPortDefinition<>(Long.class, "08b0e17e-0018-11e6-a823-f3f221c39649", "B");
	
	public static final OutputPortDefinition<Long> DEF_PORT_RES = 
			new OutputPortDefinition<>(Long.class, "0dd39df4-0018-11e6-bc77-afc04787bcdc", "res");
	
	private final InputPort<Long> portA = DEF_PORT_A.create(this);
	private final InputPort<Long> portB = DEF_PORT_B.create(this);
	private final OutputPort<Long> portRes = DEF_PORT_RES.create(this);

	public LongAdder(Tickator tickator) {
		super(tickator);
	}

	@Override
	protected void execute() throws Exception {
		portRes.set(portA.getOrDefault(0l)+portB.getOrDefault(0l));
	}
}
