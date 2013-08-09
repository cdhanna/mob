package com.hanna.mobsters.actors.traits;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;

public class DangerTrait extends Trait{
	
	/**
	 * @param importance - how much the actor cares about danger. High values correspond to 'cowardly'.
	 */
	public DangerTrait(int importance){
		this.importance = importance;
	}

	@Override
	public double compute(Action action, Actor actor) {
		ActionWeight<Double> t = action.getWeight(DangerTrait.class);
		Double contextWeight = action.getContextWeight(actor, DangerTrait.class);
		
		return t.getValue() * contextWeight * importance;
	}

}
