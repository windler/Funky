package de.windler.funky.examples.misc;

import de.windler.funky.core.FunkyFunction;

/**
 * example Implementation of the FunkyFunctions. Enum is the smartest way to
 * implement this ;)
 * 
 * @author Nico Windler
 * 
 */
public enum ExampleFunctions implements FunkyFunction {

	MY_FUNCTION("func1", "de.windler.jfunk.examples.kalkulation"), FACTORIAL(
			"fak", "de.windler.jfunk.examples.kalkulation"), ASYNC("async",
			"de.windler.jfunk.examples.kalkulation");

	private final String name;
	private final String namespace;

	private ExampleFunctions(final String name, final String namespace) {
		this.name = name;
		this.namespace = namespace;
	}

	@Override
	public final String getName() {
		return name;
	}

	@Override
	public String getNamespace() {
		return namespace;
	}
}
