/**
 * 
 */
package com.hanna.mobsters.actions.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Chris Hanna
 *
 */
@Target(ElementType.CONSTRUCTOR)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionInfoAnnotation {
	String name() default "action"; 
	String[] params();
}
