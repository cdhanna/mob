/**
 * 
 */
package com.hanna.mobsters.actors.traits;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionTraitElement;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;

/**
 * @author Chris Hanna
 *
 */
public class ShyTrait extends Trait{

	@Override
	public double compute(Action action, Actor actor) {
		ActionTraitElement<Double> t = action.getTraitVal(ShyTrait.class);
		Double shyness = t.getValue();
		
		//uh, this sucks for logic. Too late to think about this hard.
		//this will just be be a toggle pretty much
		if (shyness > 3.0 && ActorBin.getInstance().getNearByActors(actor).length > 0)
			return -10;
			
		return 0;
	}

}
