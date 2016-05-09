package org.tickator.definition.component;

import java.util.Collections;
import java.util.List;

public class ComponentImplementation {
	private String uuid;
	private List<TickletUsage> tickletUsages;
	private List<ComponentUsage> componentUsages;
	private List<Wire> wires;

	ComponentImplementation(String uuid, List<TickletUsage> tickletUsages, List<ComponentUsage> componentUsages,
			List<Wire> wires) {
		this.uuid = uuid;
		this.tickletUsages = Collections.unmodifiableList(tickletUsages);
		this.componentUsages = Collections.unmodifiableList(componentUsages);
		this.wires = Collections.unmodifiableList(wires);
	}

	public String getUuid() {
		return uuid;
	}

	public List<TickletUsage> getTickletUsages() {
		return tickletUsages;
	}

	public List<ComponentUsage> getComponentUsages() {
		return componentUsages;
	}

	public List<Wire> getWires() {
		return wires;
	}
}
