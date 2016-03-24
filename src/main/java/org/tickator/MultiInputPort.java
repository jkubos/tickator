package org.tickator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.tickator.utils.ConsumerThrowingException;
import org.tickator.utils.TickatorUtils;

public class MultiInputPort<T> implements Connectable<T> {	
	private List<OutputPort<T>> sources = new ArrayList<>();

	private String name;

	private Class<T> klass;

	private Ticklet ticklet;

	public MultiInputPort(Ticklet ticklet, Class<T> klass, String name) {
		this.ticklet = ticklet;
		this.name = name;
		this.klass = klass;
		
		ticklet.registerPort(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void connect(OutputPort<?> source) {
		Validate.validState(getKlass().isAssignableFrom(source.getKlass()));
		
		sources.add((OutputPort<T>) source);

		source.registerDepending(this);
	}
	
	@Override
	public void disconnect(OutputPort<T> source) {
		sources.remove(source);
	}

	@Override
	public void forEachConnected(ConsumerThrowingException<OutputPort<T>> block) {
		TickatorUtils.withRuntimeException(()->{
			for (OutputPort<T> source : sources) {
				block.accept(source);
			}
		});
	}

	@Override
	public void forEachChanged(ConsumerThrowingException<OutputPort<T>> block) {
		TickatorUtils.withRuntimeException(()->{
			for (OutputPort<T> source : sources) {
				if (source.wasChanged()) {
					block.accept(source);
				}
			}
		});
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
