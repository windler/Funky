package de.windler.funky.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.windler.funky.drivers.FunkyDriver;

/**
 * This one is funky. Funky is needed for every FunkyBridge. It defines which
 * driver and which source to use.
 * 
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Funky {
	String target();

	FunkyDriver driver();
}
