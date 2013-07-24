package com.hanna.mobsters.actors.traits;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;

public class MoneyTrait extends Trait {

	public MoneyTrait(){}
	
	public MoneyTrait(int importance) {
		this.importance = importance;
	}

	@Override
	public double compute(Action action, Actor actor) {
		ActionWeight<Double> t = action.getWeight(MoneyTrait.class);
		Double contextWeight = action.getContextWeight(actor, MoneyTrait.class);
		
		return t.getValue() * contextWeight * importance;
//		ActionWeight<Double> t = action.getWeight(MoneyTrait.class);
//		Double moneyVal = t.getValue();
//		Double absMoney = Math.abs(moneyVal);
//		Double coefficient = 1.0;
//		if (actor.getPropertyValue(MoneyProperty.class) == 0)
//			coefficient = 0.0;
//		else coefficient = 1/actor.getPropertyValue(MoneyProperty.class);
//		
//		if (absMoney == 0) //wait what?
//			absMoney = 1.0;
//		
//		return coefficient * (moneyVal/absMoney) * Math.pow(absMoney, importance);
	}


}
