package com.hanna.mobsters.actors;

import java.util.ArrayList;

public class Decision {
	Double val;
	ArrayList<Double> magnitudes;
	ArrayList<Class<?>> names;
	
	public Decision(){
		this.val = 0.0;
		magnitudes = new ArrayList<Double>();
	}
	
	public void addTerm(Class<?> name, Double term){
		magnitudes.add(term);
		names.add(name);
	}
	
	public void makeDecision(){
		for (Double d:magnitudes)
			val += d;			
	}
	
	public Double getDecision(){
		return val;
	}
	
	public Double getTerm(){
		if (magnitudes.size() > 0)
			return magnitudes.remove(1);
		else
			return null;
	}
	
	public Class<?> getTermName(){
		if (names.size() > 0)
			return names.remove(1);
		else
			return null;
	}
	
	

}
