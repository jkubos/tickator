package org.tickator;

import org.tickator.utils.ConsumerThrowingException;

public interface Connectable<T> extends Port {
	void connect(OutputPort<?> source);
	
	void disconnect(OutputPort<T> source);
	
	void forEachConnected(ConsumerThrowingException<OutputPort<T>> block) throws Exception;
	
	void forEachChanged(ConsumerThrowingException<OutputPort<T>> block) throws Exception;
	
	Class<T> getKlass();
}
