package org.tickator.ticklet;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.tickator.definition.ports.MultiInputPortDefinition;
import org.tickator.definition.ports.PortDefinition;
import org.tickator.utils.ConsumerThrowingException;
import org.tickator.utils.TickatorUtils;

public class MultiInputPort<T> implements Connectable<T> {	
	private List<OutputPort<T>> sources = new ArrayList<>();

	private Ticklet ticklet;

	private MultiInputPortDefinition<T> definition;

	public MultiInputPort(Ticklet ticklet, MultiInputPortDefinition<T> definition) {
		this.ticklet = ticklet;
		this.definition = definition;
		
		ticklet.registerPort(this);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void connect(OutputPort<?> source) {
		Validate.validState(getDefinition().getKlass().isAssignableFrom(source.getDefinition().getKlass()));
		
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
	public Ticklet getTicklet() {
		return ticklet;
	}

	@Override
	public PortDefinition<T> getDefinition() {
		return definition;
	}
}
