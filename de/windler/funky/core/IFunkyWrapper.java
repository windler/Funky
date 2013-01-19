package de.windler.funky.core;

/**
 * The FunkyWrapper wrapps the driver to the functional language
 * 
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
public interface IFunkyWrapper {
	/**
	 * invokes a function
	 * 
	 * @param <T>
	 * @param function
	 *            to invoke
	 * @param clazz
	 *            return class
	 * @param params
	 *            parameters
	 * @return result
	 * @throws FunkyException
	 */
	<T> FunkyImmutableValue<T> invoke(FunkyFunction function, Class<T> clazz,
			Object... params) throws FunkyException;

	/**
	 * invokes and notifies listener
	 * 
	 * @param listener
	 *            to notify
	 * @param function
	 *            to invoke
	 * @param params
	 *            parameters
	 * @throws FunkyException
	 */
	void invokeAndNotify(FunkyInvocationFinishedListener<?> listener,
			FunkyFunction function, Object... params) throws FunkyException;

	/**
	 * checks whether invocation possible
	 * 
	 * @return
	 */
	boolean isInitialized();
}
