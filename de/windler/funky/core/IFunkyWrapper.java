package de.windler.funky.core;

/**
 * The FunkyWrapper wrapps the driver to the functional language
 * 
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
public interface IFunkyWrapper {
	<T> FunkyImmutableValue<T> invoke(FunkyFunction function, Class<T> clazz,
			Object... params) throws FunkyException;

	void invokeAndNotify(FunkyInvocationFinishedListener<?> listener,
			FunkyFunction function, Object... params) throws FunkyException;
}
