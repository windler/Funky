package de.windler.funky.core;

/**
 * notified when the evaluation of the called function has been finished
 * 
 * @since 02.12.2012
 * @author Nico Windler
 * 
 * @param <T>
 */
public interface FunkyInvocationFinishedListener<T> {
	void onInvocationFinished(FunkyImmutableValue<T> value);
}
