package com.hanna.mobsters.actions.core;

public class ActionTraitElement <T> {

	T value;
	public ActionTraitElement(T value){
		this.value = value;
	}
	
	public void setValue(T value){
		this.value = value;
	}
	
	public T getValue(){
		return this.value;
	}
	
	/**
	 * @return the value of this element, directly casted 
	 */
	public <R> R getValueAs(Class<R> r){
		return (R)this.value;
	}
	
	public Class<T> getType(){
		return (Class<T>) this.value.getClass();
	}
	
//	public ActionTraitElement(String str, Double numVal) {
////		this.str = str;
////		this.numVal = numVal;
//	}
	
//	public void setStr(String str){this.str = str;}
//	public void setNumVal(Double numVal){this.numVal = numVal;}
//	
//	public String getStr(){return str;}
//	public Double getNumVal(){return numVal;}

}
