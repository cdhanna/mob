/**
 * 
 */
package com.hanna.mobsters.actors.traits;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;

/**
 * @author Chris Hanna
 *
 */
public class ShyTrait extends Trait{

	//private Double shy;
	
	public ShyTrait(){}
	
	
	/**
	 * @param Double shy - parameter describing how 'shy' the actor is. Positive values correspond to
	 * high shyness. Negative values correspond to the actor being 'outgoing'.
	 */
	public ShyTrait(int importance){
		this.importance = importance;
	}
	
	@Override
	public double compute(Action action, Actor actor) {
//		Double result = -this.shy * action.getContextWeight(actor, ShyTrait.class);
//		ActionWeight<Double> t = action.getWeight(ShyTrait.class);
//		result *= t.getValue();
//
//		return result;
		ActionWeight<Double> t = action.getWeight(ShyTrait.class);
		Double contextWeight = action.getContextWeight(actor, ShyTrait.class);
		
		return t.getValue() * contextWeight * importance;
	}

}
