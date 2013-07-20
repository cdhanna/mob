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
	public abstract String getPropertyName();
	
	
	public T getValue(){
		return value;
	}
	
	public void setValue(T object){
		this.value = object;
	}
	
	public final Class<?> getType(){
		
		return this.getDefaultValue().getClass();
	}
	
	@Override
	public final String toString(){
		return "Property: " + this.getPropertyName() + " = " + this.getValue();
	}
}
