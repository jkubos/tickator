package org.tickator;

import org.apache.commons.lang3.Validate;
import org.tickator.meta.InputPortDefinition;
import org.tickator.meta.PortDefinition;
import org.tickator.utils.ConsumerThrowingException;

public class InputPort<T> implements Connectable<T> {
	private OutputPort<T> source;
	private Ticklet ticklet;
	private InputPortDefinition<T> definition;

	public InputPort(Ticklet ticklet, InputPortDefinition<T> definition) {
		this.ticklet = ticklet;
		this.definition = definition;
		
		ticklet.registerPort(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void connect(OutputPort<?> source) {
		Validate.validState(getDefinition().getKlass().isAssignableFrom(source.getDefinition().getKlass()));
		
		this.source = (OutputPort<T>) source;
		source.registerDepending(this);
	}
	
	@Override
	public void disconnect(OutputPort<T> source) {
		this.source = null;
	}
	
	@Override
	public PortDefinition<T> getDefinition() {
		return definition;
	}

	@Override
	public Ticklet getTicklet() {
		return ticklet;
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
}
