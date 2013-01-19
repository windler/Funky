package de.windler.funky.core;

/**
 * for internal purposes only!
 * 
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
public interface IFunkyCoreWrapper extends IFunkyWrapper {

	/**
	 * initializes the wrapper
	 * 
	 * @throws FunkyException
	 */
	void initialize() throws FunkyException;
}
