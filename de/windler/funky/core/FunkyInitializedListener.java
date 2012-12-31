package de.windler.funky.core;

/**
 * will be notified if the FunkyBridge has been initialized
 * 
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
public interface FunkyInitializedListener {
	void onInitialized() throws FunkyException;
}
