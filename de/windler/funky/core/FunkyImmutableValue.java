package de.windler.funky.core;

/**
 * Creates a wrapper-object for a given type of value. This value is final and
 * can not be modified!
 * 
 * @since 02.12.2012
 * @author Nico Windler
 * 
 * @param <T>
 */
public class FunkyImmutableValue<T> {

	private final T value;

	public FunkyImmutableValue(final T value) {
		this.value = value;
	}

	public final T getValue() {
		return value;
	}
}
