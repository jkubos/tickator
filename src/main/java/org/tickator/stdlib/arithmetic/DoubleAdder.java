package org.tickator.stdlib.arithmetic;

import org.tickator.InputPort;
import org.tickator.OutputPort;
import org.tickator.Tickator;
import org.tickator.Ticklet;
import org.tickator.meta.InputPortDefinition;
import org.tickator.meta.OutputPortDefinition;

public class DoubleAdder extends Ticklet {
	
	public static final InputPortDefinition<Double> DEF_PORT_A = 
			new InputPortDefinition<>(Double.class, "da08784e-ff56-11e5-b10d-9fdb19cf055d", "A");
	
	public static final InputPortDefinition<Double> DEF_PORT_B = 
			new InputPortDefinition<>(Double.class, "f4d254d8-ff56-11e5-a32a-539f765436b3", "B");
	
	public static final OutputPortDefinition<Double> DEF_PORT_RES = 
			new OutputPortDefinition<>(Double.class, "d766e33e-0017-11e6-8feb-337bda0cb8e9", "res");
	
	private final InputPort<Double> portA = DEF_PORT_A.create(this);
	private final InputPort<Double> portB = DEF_PORT_B.create(this);
	private final OutputPort<Double> portRes = DEF_PORT_RES.create(this);

	public DoubleAdder(Tickator tickator) {
		super(tickator);
	}

	@Override
	protected void execute() throws Exception {
		portRes.set(portA.getOrDefault(0.0)+portB.getOrDefault(0.0));
	}
}
