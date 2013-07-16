package com.hanna.mobsters.actions;

public abstract class Action implements Comparable<Action> {
	
	int priority; // must have a priority.
	double feelVal; // must have a feeling value (array of them eventually)
	

	/**
	 * all actions must have a method that describes how to complete the action
	 */
	public abstract String doIt();
	
	
	
	/**
	 * the decider method in the actor class needs these parameters for decision making
	 * @return
	 */
	public double getFeelVal(){
		return feelVal;
	}

	
}
