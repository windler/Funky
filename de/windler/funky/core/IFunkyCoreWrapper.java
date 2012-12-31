package de.windler.funky.core;

/**
 * for internal purposes only!
 * 
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
public interface IFunkyCoreWrapper extends IFunkyWrapper {
	void initialize() throws FunkyException;

	boolean isInitialized();
}
