package com.hanna.mobsters.actions.impl;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionInfoAnnotation;
import com.hanna.mobsters.actions.core.ActionTraitElement;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;
import com.hanna.mobsters.actors.traits.MoneyTrait;


public class TransferMoneyAction extends Action {
	private Actor gettingActor;
	
	@ActionInfoAnnotation(params = { "Money", "Receiving Actor","Priority" })
	public TransferMoneyAction(Double money, Actor gettingActor,Integer priority) {
		this.gettingActor = gettingActor;
		this.priority = priority;
		money = 1.0 * Math.abs(money);
		
		ActionTraitElement<Double> t = new ActionTraitElement<Double>(money);
		traitVals.put(MoneyTrait.class, t);
	}

	@Override
	public int compareTo(Action arg0) {
		return arg0.getPriority() - this.priority;
	}

	@Override
	public String doIt(Actor self) {
		Double transferCash = this.getTraitVal(MoneyTrait.class).getValueAs(Double.class);
		Double onHandCash = self.getPropertyValue(MoneyProperty.class);
		if (onHandCash < transferCash)
			transferCash = onHandCash;
		
		Double receiverCash = this.gettingActor.getPropertyValue(MoneyProperty.class);
		
		self.setPropertyValue(MoneyProperty.class, onHandCash - transferCash);
		this.gettingActor.setPropertyValue(MoneyProperty.class, receiverCash + transferCash);
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
		Double newMoney = scaleFactor * Math.abs( (Double)this.getTraitVal(MoneyTrait.class).getValue() );
		
	
		//ActionTraitElement t = new ActionTraitElement("",newMoney);
		//this.traitVals.put(MoneyTrait.class, t);
		//((ActionTraitElement<Double>)this.getTraitVal(MoneyTrait.class)).setValue(newMoney);
		//TODO uhm, this is still not all that pretty....
		this.getTraitVal(MoneyTrait.class).setValue(newMoney);
		
		return this;
	
		
	}
	
	@Override
	public String toString(){
		return "give " + this.getTraitVal(MoneyTrait.class).getValue() + " dollars to " + this.gettingActor.toString();
	}

}
