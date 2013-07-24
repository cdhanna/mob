/**
 * 
 */
package com.hanna.mobsters.actors.traits;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionWeight;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;

/**
 * @author Chris Hanna
 *
 */
public class ShyTrait extends Trait{

	private Double shy;
	
	public ShyTrait(){}
	
	public ShyTrait(Double shy){
		this.shy = shy;
	}
	
	@Override
	public double compute(Action action, Actor actor) {
		
		Double result = -this.shy * action.getContextWeight(actor, ShyTrait.class);
		
		
		ActionWeight<Double> t = action.getWeight(ShyTrait.class);
		result *= t.getValue();
//		Double shyness = t.getValue();
//		
//		
//		//work with 'shy' AND action.getTraitVal(ShyTrait.class) 
//		
//		//uh, this sucks for logic. Too late to think about this hard.
//		//this will just be be a toggle pretty much
//		if (shyness > 3.0 && ActorBin.getInstance().getNearByActors(actor).length > 0)
//			return -10;
//			
		return result;
	}

}
