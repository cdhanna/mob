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
	/**
	 * maps implementations of Trait (specific traits) to corresponding weights for the action.
	 * These weights are INDEPENDENT of circumstances or actor personality - they are inherent parameters
	 * for a the action. So for instance, a 'murder' action might have an inherent morality weight of -.08,
	 * regardless of who is doing the murder or why.
	 */
	protected HashMap<Class<? extends Trait>,ActionWeight<?>> traitVals;
	
	/**
	IMPORTANT NOTES ABOUT ACTIONS:<br>
 		1: There can only be ONE constructor for any particular action<br>
 		2: Constructor input parameters cannot be primitives, make them wrapper classes<br>
 		3: Constructor must follow this form<br>
  		-@ActionInfoAnnotation(params = { "xName", "yName" })<br>
  		-public ActionImp(Integer x, Double y){<br>
  		$: For now you MUST add a default ActionWeight for each Trait we write in the program.
	 */
	@ActionInfoAnnotation(name = "Base Action", params = {  })
	public Action(){
		// put in default trait weights. They are all zero.
		traitVals = new HashMap<Class<? extends Trait>,ActionWeight<?>>();
		traitVals.put(DangerTrait.class, new ActionWeight<Double>(0.0));
		traitVals.put(LoyaltyTrait.class, new ActionWeight<Double>(0.0));
		traitVals.put(MoneyTrait.class, new ActionWeight<Double>(0.0));
		traitVals.put(MoralityTrait.class, new ActionWeight<Double>(0.0));
		
		// legacy traits
		traitVals.put(ShyTrait.class, new ActionWeight<Double>(0.0));;
		
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

	
	/** This method takes a Trait class as an argument. It then checks to see if that class has an ActionWeight
	 * in the traitVals hashMap. If it does, the ActionWeight is returned. If not, an empty ActionWeight is 
	 * returned.
	 * @param key - a class that extends Trait
	 * @return an ActionWeight
	 */
	public <T> ActionWeight<T> getWeight(Class<? extends Trait> key ){
		if (traitVals.containsKey(key))
			return (ActionWeight<T>) traitVals.get(key);
		else
			return new ActionWeight<T>(null);//TODO this needs work. I don't even know if what I did is improvement //new ActionTraitElement<Double>(0.0);
	}
	public int getPriority() {
		return this.priority;
	}
	
	public Double getContextWeight(Actor actor, Class<? extends Trait> clazz) {
		return 0.0;
	}

}
