package de.windler.funky.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * annotate this to a field within your FunkyBridge and it will be automatically
 * filled with the corresponding function. The called function has to be
 * evaluated with no parameters!
 * 
 * @ensure getConstantsNamespace()
 * @since 02.12.2012
 * @author Nico Windler
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface FunkyConstant {
}
