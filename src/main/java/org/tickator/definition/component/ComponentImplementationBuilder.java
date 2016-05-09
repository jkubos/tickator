package org.tickator.definition.component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.Validate;
import org.tickator.utils.Builder;
import org.tickator.utils.UniqueUuidValidator;

public class ComponentImplementationBuilder extends Builder<ComponentImplementation> {

	private String uuid;
	private List<TickletUsage> tickletUsages = new ArrayList<>();
	private List<ComponentUsage> componentUsages = new ArrayList<>();
	private List<Wire> wires = new ArrayList<>();
	
	public static ComponentImplementationBuilder start() {
		return new ComponentImplementationBuilder(UUID.randomUUID().toString()); 
	}
	
	public static ComponentImplementationBuilder start(String uuid) {
		Validate.notBlank(uuid);
		
		return new ComponentImplementationBuilder(uuid); 
	}
	
	private ComponentImplementationBuilder(String uuid) {
		this.uuid = uuid;
	}
	
	public ComponentImplementationBuilder addTickletUsage(TickletUsage tickletUsage) {
		Validate.notNull(tickletUsage);
		
		tickletUsages.add(tickletUsage);
		
		return this;
	}
	
	public ComponentImplementationBuilder addComponentUsage(ComponentUsage componentUsage) {
		Validate.notNull(componentUsage);
		
		componentUsages.add(componentUsage);
		
		return this;
	}

	public ComponentImplementationBuilder addWire(Wire wire) {
		Validate.notNull(wire);
		
		wires.add(wire);
		
		return this;
	}
	
	@Override
	protected ComponentImplementation create() {
		return new ComponentImplementation(uuid, tickletUsages, componentUsages, wires);
	}

	@Override
	protected void validate() {
		validateUniqueUuids();
	}

	private void validateUniqueUuids() {
		UniqueUuidValidator validator = new UniqueUuidValidator();
		
		tickletUsages.forEach(o->validator.add(o.getUuid(), o));
		componentUsages.forEach(o->validator.add(o.getUuid(), o));
		wires.forEach(o->validator.add(o.getUuid(), o));
	}
}
