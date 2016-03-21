package org.tickator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OutputPort<T> implements Port {
	private T valueA = null;
	private long validFromTickA = 0;
	
	private T valueB = null;
	private long validFromTickB = -1;

	private Class<T> klass;

	private String name;

	private Ticklet ticklet;
	
	private List<Connectable<?>> depending = new ArrayList<>();
	
	public OutputPort(Ticklet ticklet, Class<T> klass, String name) {
		this.klass = klass;
		this.name = name;
		this.ticklet = ticklet;
		
		ticklet.registerPort(this);
	}
	
	public void set(T value) {
		long tick = ticklet.getTickator().getTick();
		
		if (isCurrentA()) {
			if (!Objects.equals(valueA, value)) {
				valueB = value;
				validFromTickB = tick+1;
				ticklet.getTickator().getExecutor().markChanged(this);
			}
		} else {
			if (!Objects.equals(valueB, value)) {
				valueA = value;
				validFromTickA = tick+1;
				ticklet.getTickator().getExecutor().markChanged(this);
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
		long tick = ticklet.getTickator().getTick();
		
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
	public String getName() {
		return name;
	}

	public Class<T> getKlass() {
		return klass;
	}

	@Override
	public Ticklet getTicklet() {
		return ticklet;
	}
	
	public void registerDepending(Connectable<?> input) {
		if (!input.getKlass().isAssignableFrom(getKlass())) {
			throw new RuntimeException(String.format("Incompatible types of output %s and input %s!", 
					getKlass().getSimpleName(), input.getKlass().getSimpleName()));
		}
		
		depending.add(input);
	}

	public Iterable<Connectable<?>> getDepending() {
		return depending;
	}
}
