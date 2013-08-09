package com.hanna.mobsters.actors.traits;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;

public class LoyaltyTrait extends Trait{
	
	/**
	 * @param importance - how much the actor cares about loyality. High values correspond to 'faithful'.
	 */
	public LoyaltyTrait(int importance){
		this.importance = importance;
	}
	public LoyaltyTrait(){}
	
	@Override
	public double compute(Action action, Actor actor) {
		ActionWeight<Double> t = action.getWeight(LoyaltyTrait.class);
		Double contextWeight = action.getContextWeight(actor, LoyaltyTrait.class);
		
		return t.getValue() * contextWeight * importance;
	}

}
