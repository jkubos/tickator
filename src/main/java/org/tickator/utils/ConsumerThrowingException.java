package org.tickator.utils;

public interface ConsumerThrowingException<T> {
	void accept(T value) throws Exception;
}
