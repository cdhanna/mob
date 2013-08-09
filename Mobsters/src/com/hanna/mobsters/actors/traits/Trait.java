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
	


	/**
	 * @return the importance of this trait
	 */
	public int getImportance(){
		return this.importance;
	}

	/**
	 * @param importance of the trait
	 */
	public void setImportance(Integer importance) {
		this.importance = importance;
	}
	
}
