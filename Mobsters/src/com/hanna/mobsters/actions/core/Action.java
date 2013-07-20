package com.hanna.mobsters.actions.core;
import java.util.*;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.traits.*;
/* THREE IMPORTANT NOTES ABOUT ACTIONS:
// 1: There can only be ONE constructor for any particular action
// 2: Constructor input parameters cannot be primitives, make them wrapper classes
// 3: Constructor must follow this form
 * 	@ActionINfoAnnotation(params = { "xName", "yName" })
 * 	public ActionImp(Integer x, Double y){
 * 		//
 *  }
*/
public abstract class Action implements Comparable<Action> {
	
	protected int priority; // must have a priority.
	protected HashMap<Class<? extends Trait>,ActionTraitElement> traitVals;
	
	/**
	THREE IMPORTANT NOTES ABOUT ACTIONS:<br>
 		1: There can only be ONE constructor for any particular action<br>
 		2: Constructor input parameters cannot be primitives, make them wrapper classes<br>
 		3: Constructor must follow this form<br>
  		-@ActionInfoAnnotation(params = { "xName", "yName" })<br>
  		-public ActionImp(Integer x, Double y){<br>
	 */
	@ActionInfoAnnotation(name = "Base Action", params = {  })
	public Action(){
		traitVals = new HashMap<Class<? extends Trait>,ActionTraitElement>();
		
	}
	/**
	 * all actions must have a method that describes how to complete the action
	 */
	public abstract String doIt(Actor actor);
	
	/**
	 * @param x the value from the actor decider method
	 * @return a (possibly) modified version of the action
	 */
	public abstract Action mutateAction(double x);

	
	public ActionTraitElement getTraitVal(Class<? extends Trait> key ){
		if (traitVals.containsKey(key))
			return traitVals.get(key);
		else
			return new ActionTraitElement("",0.0);
	}
	public int getPriority() {
		return this.priority;
	}
	
	
	/**
	 * the decider method in the actor class needs these parameters for decision making
	 * @return
	 */

	
}
