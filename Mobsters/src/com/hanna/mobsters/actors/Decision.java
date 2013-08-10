package com.hanna.mobsters.actors;

import java.util.ArrayList;

public class Decision {
	/**
	 * an UNBOUNDED number corresponding to how strongly the actor does or does 
	 *  not want to do the action
	 */
	Double val;
	/**
	 * List of magnitudes values
	 */
	ArrayList<Double> magnitudes;
	/**
	 *  List of magnitude Trait names
	 */
	ArrayList<Class<?>> names;
	
	public Decision(){
		this.val = 0.0;
		magnitudes = new ArrayList<Double>();
		names = new ArrayList<>();
	}
	
	/**
	 * @param name - the trait being added to the decision
	 * @param term - the numerical value for the trait
	 */
	public void addTerm(Class<?> name, Double term){
		magnitudes.add(term);
		names.add(name);
	}
	
	/**
	 * Adds up all of the terms in the magnitude list.
	 */
	public void makeDecision(){
		for (Double d:magnitudes)
			val += d;			
	}
	
	/**
	 * @return the signed value of the decision
	 */
	public Double getDecision(){
		return val;
	}
	

	/**
	 * @return the first magnitude in the list. Eventually this should be changed to be able
	 * to return the highest value in the list (List needs a sorting function)
	 */
	public Double getTerm(){
		if (magnitudes.size() > 0)
			return magnitudes.remove(0);
		else
			return null;
	}
	
	/**
	 * @return the first trait name in the list. Eventually this should be changed to be able
	 * to return the highest value in the list (List needs a sorting function)
	 */
	public Class<?> getTermName(){
		if (names.size() > 0)
			return names.remove(0);
		else
			return null;
	}
	
	

}
