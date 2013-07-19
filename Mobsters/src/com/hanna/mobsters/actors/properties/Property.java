/**
 * 
 */
package com.hanna.mobsters.actors.properties;

/**
 * @author Chris Hanna
 *
 */
public abstract class Property<T> {

	T value;
	
	public Property(){
		this.setValue(this.getDefaultValue());
	}
	
	public abstract T getDefaultValue();
	public abstract String getName();
	
	
	public T getValue(){
		return value;
	}
	
	public void setValue(T object){
		this.value = object;
	}
	
	@Override
	public final String toString(){
		return "Property: " + this.getName() + " = " + this.getValue();
	}
}
