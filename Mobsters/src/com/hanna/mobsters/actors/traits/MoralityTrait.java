package com.hanna.mobsters.actors.traits;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;

public class MoralityTrait extends Trait{
	
	/**
	 * @param importance - how much the actor cares about morality. High values correspond to 'righteous'.
	 */
	public MoralityTrait(int importance){
		this.importance = importance;
	}

	@Override
	public double compute(Action action, Actor actor) {
		ActionWeight<Double> t = action.getWeight(MoneyTrait.class);
		Double contextWeight = action.getContextWeight(actor, MoneyTrait.class);
		
		return t.getValue() * contextWeight * importance;
	}

}
