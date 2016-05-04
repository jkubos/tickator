package org.tickator.ticklet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.tickator.definition.ports.OutputPortDefinition;
import org.tickator.definition.ports.PortDefinition;

public class OutputPort<T> implements Port<T> {
	private T valueA = null;
	private long validFromTickA = 0;
	
	private T valueB = null;
	private long validFromTickB = -1;

	private Ticklet ticklet;
	
	private List<Connectable<?>> depending = new ArrayList<>();
	private OutputPortDefinition<T> definition;
	
	public OutputPort(Ticklet ticklet, OutputPortDefinition<T> definition) {
		this.definition = definition;
		this.ticklet = ticklet;
		
		ticklet.registerPort(this);
	}

	public void pulse() {
		ticklet.getTickatorExecutorFacade().markChanged(this);
	}
	
	public void set(T value) {
		long tick = ticklet.getTickatorExecutorFacade().getTick();
		
		if (isCurrentA()) {
			if (!Objects.equals(valueA, value)) {
				valueB = value;
				validFromTickB = tick+1;
				ticklet.getTickatorExecutorFacade().markChanged(this);
			}
		} else {
			if (!Objects.equals(valueB, value)) {
				valueA = value;
				validFromTickA = tick+1;
				ticklet.getTickatorExecutorFacade().markChanged(this);
			}
		}
	}

	public T get() {
		if (isCurrentA()) {
			return valueA;
		} else {
			return valueB;
		}
	}
	
	private boolean isCurrentA() {
		long tick = ticklet.getTickatorExecutorFacade().getTick();
		
		long diffA  = tick-validFromTickA;
		long diffB  = tick-validFromTickB;
		
		if (diffA>=0 && diffB<0) {
			return true;
		} else if (diffA<0 && diffB>=0) {
			return false;
		} else if (diffA<0 && diffB<0) {
			throw new RuntimeException("Both validity ticks are in future?");
		} else {
			if (diffA==diffB) {
				throw new RuntimeException("Both validity ticks are same?");
			} else if (diffA<diffB) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public Ticklet getTicklet() {
		return ticklet;
	}
	
	public void registerDepending(Connectable<?> input) {
		if (!input.getDefinition().getKlass().isAssignableFrom(getDefinition().getKlass())) {
			throw new RuntimeException(String.format("Incompatible types of output %s and input %s!", 
					getDefinition().getKlass().getSimpleName(), input.getDefinition().getKlass().getSimpleName()));
		}
		
		depending.add(input);
	}

	public Iterable<Connectable<?>> getDepending() {
		return depending;
	}

	public boolean wasChanged() {
		long tick = ticklet.getTickatorExecutorFacade().getTick();
		
		return validFromTickA==tick || validFromTickB==tick;
	}

	@Override
	public PortDefinition<T> getDefinition() {
		return definition;
	}
}
