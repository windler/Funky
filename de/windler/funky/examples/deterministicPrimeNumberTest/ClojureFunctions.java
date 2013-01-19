package de.windler.funky.examples.deterministicPrimeNumberTest;

import de.windler.funky.core.FunkyFunction;

/**
 * Here all all Clojure-Functions defined
 * 
 * @author Nico Windler
 * @since 19.01.2013
 */
public enum ClojureFunctions implements FunkyFunction {
	PRIME_TEST("prime", "de.windler");

	private final String name;
	private final String namespace;

	private ClojureFunctions(String name, String namespace) {
		this.name = name;
		this.namespace = namespace;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getNamespace() {
		return namespace;
	}

}
