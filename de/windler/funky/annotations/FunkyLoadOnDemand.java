package de.windler.funky.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Determs that the wrapper will only load the target when invoke is called!
 * (lazy loading)
 * 
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FunkyLoadOnDemand {

}
