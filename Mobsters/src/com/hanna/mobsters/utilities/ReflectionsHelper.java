/**
 * 
 */
package com.hanna.mobsters.utilities;

import java.util.Set;

import org.reflections.Reflections;

/**
 * @author Chris Hanna
 *
 */
public class ReflectionsHelper {

	private static ReflectionsHelper instance;
	public static ReflectionsHelper getInstance(){
		if (instance == null)
			instance = new ReflectionsHelper();
		return instance;
	}
	
	
	private ReflectionsHelper(){
		
	}
	
	public <T> Set<Class<? extends T>> getSubTypes(Class<T> superType, String packageToLookIn){
		Reflections r = new Reflections(packageToLookIn);
		Set<Class<? extends T>> children = r.getSubTypesOf(superType);
		return children;
	}
	
}
