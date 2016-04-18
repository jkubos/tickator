package org.tickator.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;

import org.tickator.Ticklet;

/**
 * Various static utility methods used by Tickator and ticklets
 */
public class TickatorUtils {
	
	public interface RunnableWithException {
		void run() throws Exception;
	}
	
	public interface SupplierWithException<T> {
		T supply() throws Exception;
	}
	
	public interface BiConsumerWithException<T, U> {
		void apply(T t, U u) throws Exception;
	}
	
	/**
	 * Sleep thread without necessity to catch InterruptedException exception
	 * @param millis Number of milliseconds to sleep
	 */
	public static void sleepMillis(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Execute block of code that may throw any exception. If exception occurs rethrow it as RuntimeException.
	 * @param block
	 */
	public static void withRuntimeException(RunnableWithException block) {
		try {
			block.run();
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException)e;
			} else {
				throw new RuntimeException(e);
			}
		}
	}
	
	/**
	 * Execute block of code that may throw any exception. If exception occurs rethrow it as RuntimeException.
	 * @param block
	 * @return Returned value of block
	 */
	public static <T> T withRuntimeException(SupplierWithException<T> block) {
		try {
			return block.supply();
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException)e;
			} else {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * Iterate over collection, passing into block current item and its index. All exceptions from block are translated into runtime exception.
	 * @param coll
	 * @param block
	 */
	public static <T> void forEachWithIndex(Iterable<T> coll, BiConsumerWithException<T, Integer> block) {
		withRuntimeException(()->{;
			int i = 0;
			
			Iterator<T> iter = coll.iterator();
			
			while (iter.hasNext()) {
				T val = iter.next();
				
				block.apply(val, i++);
			}
		});
	}
	
	/**
	 * Call block for each static field of class that matches regex pattern
	 * @param klass Target class
	 * @param pattern Regex pattern
	 * @param block Block that should be invoken
	 * @throws Exception
	 */
	public static void forEachStaticField(Class<? extends Ticklet> klass, String pattern, ConsumerThrowingException<Field> block) throws Exception {
		for (Field field : klass.getDeclaredFields()) {
			if (Modifier.isStatic(field.getModifiers())) {
				if (field.getName().matches(pattern)) {
					block.accept(field);
				}
			}
		}
	}
}
