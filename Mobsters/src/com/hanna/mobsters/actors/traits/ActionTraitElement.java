package com.hanna.mobsters.actors.traits;

public class ActionTraitElement {
	String str;
	Double numVal;
	public ActionTraitElement(String str, Double numVal) {
		this.str = str;
		this.numVal = numVal;
	}
	
	public void setStr(String str){this.str = str;}
	public void setNumVal(Double numVal){this.numVal = numVal;}
	
	public String getStr(){return str;}
	public Double getNumVal(){return numVal;}

}
