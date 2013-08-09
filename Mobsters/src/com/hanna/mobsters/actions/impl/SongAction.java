package com.hanna.mobsters.actions.impl;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionInfoAnnotation;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;
import com.hanna.mobsters.actors.traits.MoneyTrait;
import com.hanna.mobsters.actors.traits.ShyTrait;
import com.hanna.mobsters.actors.traits.Trait;

public class SongAction extends Action {
	String song;
	Double moneyValue;
	@ActionInfoAnnotation(params = { "Song", "Priority", "MoneyVal" , "Publicity"})
	public SongAction(String song, Integer priority, Double moneyVal, Double publicity) {
		super();
		this.song = song;
		this.priority = priority;
		ActionWeight<Double> t = new ActionWeight<Double>(moneyVal);
		traitVals.put(MoneyTrait.class, t);
		this.moneyValue = moneyVal;
		//added a bit of sheepishness. Hopefully, if actors are on the same street, a gangster won't sing
		ActionWeight<Double> shy = new ActionWeight<Double>(publicity);
		traitVals.put(ShyTrait.class, shy);

	}
	@Override
	public int compareTo(Action arg0) {
		return arg0.getPriority() - this.priority;
	}
	@Override
	public String doIt(Actor actor) {
		actor.pay(this.moneyValue);
		return song;
	}
	
	@Override
	public String toString(){
		return song + " , PRIORITY IS " + priority + ", IT PAYS " + this.getWeight(MoneyTrait.class).getValue() +
			   " PUBLICITY IS " + this.getWeight(ShyTrait.class).getValue();
	}
	@Override
	public Action mutateAction(double x) {
		if (x<0)
			this.song = "... singing is for chumps, boss.";
		
		if (x<10)
			this.song = song.substring(3);
		
		if (x<5)
			this.priority = 1;
		
		return this;
	}
	
	
	@Override
	public Double getContextWeight(Actor actor, Class<? extends Trait> clazz) {
		
		if (clazz == ShyTrait.class){
			return (double) ActorBin.getInstance().getNearByActors(actor).length;
		}
		
		if (clazz == MoneyTrait.class){
			Double coefficient = 1.0;
			if (actor.getPropertyValue(MoneyProperty.class) < 1.0)
				coefficient = this.moneyValue;
			else coefficient = this.moneyValue/actor.getPropertyValue(MoneyProperty.class);
			return coefficient;
		}
		
		return 0.0;
	}

}
