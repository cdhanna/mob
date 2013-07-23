package com.hanna.mobsters.actors.traits;
import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actors.Actor;
public abstract class Trait {
	
	int importance;
	
	
	/**
	 * @param action 
	 * What action to compute
	 * @param actor
	 * The actor that is going to be running the given action
	 * @return A double. If it is a high value, that means the actor will do it. If it is low, then the actor
	 * wont do it.
	 */
	public abstract double compute(Action action, Actor actor);


}
