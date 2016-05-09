package org.tickator.definition.component;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ComponentImplementationBuilderTest {
	@Test
	public void testBuild() {
		ComponentImplementation componentImplementation = generateBasicImplementation();
		
		assertEquals(1, componentImplementation.getComponentUsages().size());
		assertEquals(1, componentImplementation.getTickletUsages().size());
		assertEquals(1, componentImplementation.getWires().size());
		assertEquals("3232356565", componentImplementation.getUuid());
	}
	
	private static ComponentImplementation generateBasicImplementation() {
		return ComponentImplementationBuilder
			.start("3232356565")
			.addTickletUsage(new TickletUsage("7679781", "org.tickator.stdlib.arithmetic.DoubleAdder"))
			.addComponentUsage(new ComponentUsage("4631264", "5616545"))
			.addWire(new Wire("5447989778", "78353546345", "5454547", "7679781", "da08784e-ff56-11e5-b10d-9fdb19cf055d"))
			.build();
	}
}
