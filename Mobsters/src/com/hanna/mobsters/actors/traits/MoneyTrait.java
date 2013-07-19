package com.hanna.mobsters.actors.traits;

import com.hanna.mobsters.actions.core.Action;

public class MoneyTrait extends Trait {

	public MoneyTrait(int importance) {
		this.importance = importance;
	}

	@Override
	public double compute(Action a) {
		ActionTraitElement t = a.getTraitVal(MoneyTrait.class);
		Double moneyVal = t.getNumVal();
		Double absMoney = Math.abs(moneyVal);
		Double coefficient = 1.0;
		return coefficient * (moneyVal/absMoney) * Math.pow(absMoney, importance);
	}


}
