package com.hanna.mobsters.actors.traits;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;

public class PhysicalRiskTrait extends Trait{
	
	/**
	 * @param importance - how much the actor cares about physical danger. High values correspond to 'cowardly'.
	 */
	public PhysicalRiskTrait(int importance){
		this.importance = importance;
	}
	public PhysicalRiskTrait(){}

	@Override
	public double compute(Action action, Actor actor) {
		ActionWeight<Double> t = action.getWeight(PhysicalRiskTrait.class);
		Double contextWeight = action.getContextWeight(actor, PhysicalRiskTrait.class);
		
		return t.getValue() * contextWeight * importance;
	}

}
