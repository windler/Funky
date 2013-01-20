package de.windler.funky.examples.concurrentTransactions;

import de.windler.funky.core.FunkyFunction;

/**
 * All functions we need
 * 
 * @author Nico Windler
 * @since 20.01.2013
 * 
 */
public enum AccountFunctions implements FunkyFunction {
	INIT_ACCOUNT("init!", "de.windler.transaction"), TRANSFER("transfer!",
			"de.windler.transaction"), CURRENT_AMOUNT("ammount",
			"de.windler.transaction");

	private final String name;
	private final String namespace;

	private AccountFunctions(String name, String namespace) {
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
