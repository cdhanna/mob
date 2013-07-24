package com.hanna.mobsters.actors.traits;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;

public class MoneyTrait extends Trait {

	public MoneyTrait(){}
	
	
	/**
	 * @param int importance - how much the actor cares about money. High values correspond to 'greediness'.
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
