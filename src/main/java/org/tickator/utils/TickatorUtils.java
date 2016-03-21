package org.tickator.utils;

import java.util.Iterator;

/**
 * Various static utility methods used by Tickator and ticklets
 */
public class TickatorUtils {
	
	public interface RunnableWithException {
		void run() throws Exception;
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
}
