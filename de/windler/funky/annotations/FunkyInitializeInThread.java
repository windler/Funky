package de.windler.funky.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * loads the target in a parallel thread and notifies the
 * FunkyInitializedListener if any
 * 
 * @ensure addFunkyInitializedListener
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FunkyInitializeInThread {

}
