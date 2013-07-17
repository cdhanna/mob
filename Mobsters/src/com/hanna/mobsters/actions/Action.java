package com.hanna.mobsters.actions;
import java.util.*;
import com.hanna.mobsters.actors.traits.ActionTraitElement;
// TWO IMPORTANT NOTES ABOUT ACTIONS:
// 1: There can only be ONE constructor for any particular action
// 2: Constructor input parameters cannot be primitives, make them wrapper classes
public abstract class Action implements Comparable<Action> {
	
	int priority; // must have a priority.
	HashMap<Class<?>,ActionTraitElement> traitVals;

	public Action(){
		traitVals = new HashMap<Class<?>,ActionTraitElement>();
	}
	/**
	 * all actions must have a method that describes how to complete the action
	 */
	public abstract String doIt();
	
	public ActionTraitElement getTraitVal(Class<?> key){
		if (traitVals.containsKey(key))
			return traitVals.get(key);
		else
			return new ActionTraitElement("",0.0);
	}
	
	
	/**
	 * the decider method in the actor class needs these parameters for decision making
	 * @return
	 */

	
}
