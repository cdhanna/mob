package com.hanna.mobsters.actions.impl;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionInfoAnnotation;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;
import com.hanna.mobsters.actors.traits.MoneyTrait;
import com.hanna.mobsters.actors.traits.Trait;


public class GiveMoneyAction extends Action {
	private Actor gettingActor;
	private Double moneyValue;


	@ActionInfoAnnotation(params = { "Money", "Receiving Actor","Priority" })
	public GiveMoneyAction(Double money, Actor gettingActor,Integer priority) {
		this.gettingActor = gettingActor;
		this.priority = priority;
		money = 1.0 * Math.abs(money);
		this.moneyValue = money;
		ActionWeight<Double> t = new ActionWeight<Double>(-1.0);
		traitVals.put(MoneyTrait.class, t);
	}

	@Override
	public int compareTo(Action arg0) {
		return arg0.getPriority() - this.priority;
	}

	@Override
	public String doIt(Actor self) {
		Double transferCash = this.moneyValue;
		Double onHandCash = self.getPropertyValue(MoneyProperty.class);
		if (onHandCash < transferCash){
			transferCash = onHandCash;
		}

		self.takeMoney(transferCash);
		gettingActor.pay(transferCash);

		return "I transfered the money boss";

	}

	@Override
	public Action mutateAction(double x) {
		Double scaleFactor = 1.0;
		if (x<0)
			return null;

		// scale money being transfered
		if (x<15)
			scaleFactor = 0.75;
		if (x<10)
			scaleFactor = 0.5;
		if(x<5)
			scaleFactor = 0.25;

		// bring the transfer amount back to a positive value
		Double newMoney = scaleFactor * Math.abs( (Double)this.getWeight(MoneyTrait.class).getValue() );
		this.moneyValue = newMoney;

		return this;


	}

	@Override
	public String toString(){
		return "give " + this.getWeight(MoneyTrait.class).getValue() + " dollars to " + this.gettingActor.toString();
	}

	@Override
	public Double getContextWeight(Actor actor, Class<? extends Trait> clazz) {

		//Double coefficient = 1.0;
		if (clazz == MoneyTrait.class){
			Double ratio = this.moneyValue / actor.getPropertyValue(MoneyProperty.class);
			if (ratio > 1)
				return Double.MAX_VALUE;
			else return ratio;
		}
		return 0.0;

		//		if (this.moneyValue > actor.getPropertyValue(MoneyProperty.class)){
		//			
		//		}
		//		
		//		if (actor.getPropertyValue(MoneyProperty.class) < 1.0)
		//			coefficient = this.moneyValue;
		//		else coefficient = this.moneyValue/actor.getPropertyValue(MoneyProperty.class);
		//		
		//		return coefficient;
	}

}
