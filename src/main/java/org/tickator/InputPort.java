package org.tickator;

import org.apache.commons.lang3.Validate;
import org.tickator.utils.ConsumerThrowingException;

public class InputPort<T> implements Connectable<T> {
	private OutputPort<T> source;
	private String name;
	private Class<T> klass;
	private Ticklet ticklet;

	public InputPort(Ticklet ticklet, Class<T> klass, String name) {
		this.ticklet = ticklet;
		this.name = name;
		this.klass = klass;
		
		ticklet.registerPort(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void connect(OutputPort<?> source) {
		Validate.validState(getKlass().isAssignableFrom(source.getKlass()));
		
		this.source = (OutputPort<T>) source;
		source.registerDepending(this);
	}
	
	@Override
	public void disconnect(OutputPort<T> source) {
		this.source = null;
	}

	public void ifChanged(ConsumerThrowingException<OutputPort<T>> block) throws Exception {
		if (source!=null && source.wasChanged()) {
			block.accept(source);
		}
	}

	@Override
	public void forEachConnected(ConsumerThrowingException<OutputPort<T>> block) throws Exception {
		if (source!=null) {
			block.accept(source);
		}
	}
	
	@Override
	public void forEachChanged(ConsumerThrowingException<OutputPort<T>> block) throws Exception {
		ifChanged(block);
	}
	
	public T get() {
		return getOrDefault(null);
	}

	public T getOrDefault(T defaultValue) {
		if (source==null) {
			return defaultValue;
		} else {
			T res = source.get();
			
			return res==null ? defaultValue : res;
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Class<T> getKlass() {
		return klass;
	}

	@Override
	public Ticklet getTicklet() {
		return ticklet;
	}
}
