package com.hanna.mobsters.actors.traits;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;

public class MoneyTrait extends Trait {

	public MoneyTrait(){}
	
	
	/**
	 * @param importance - how much the actor cares about money. High values correspond to 'greedy'.
	 */
	public MoneyTrait(int importance) {
		this.importance = importance;
	}

	@Override
	public double compute(Action action, Actor actor) {
		ActionWeight<Double> t = action.getWeight(MoneyTrait.class);
		Double contextWeight = action.getContextWeight(actor, MoneyTrait.class);
		
		return t.getValue() * contextWeight * importance;
	}

}
