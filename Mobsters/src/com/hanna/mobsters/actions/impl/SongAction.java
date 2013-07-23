package com.hanna.mobsters.actions.impl;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionInfoAnnotation;
import com.hanna.mobsters.actions.core.ActionTraitElement;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.traits.MoneyTrait;
import com.hanna.mobsters.actors.traits.ShyTrait;

public class SongAction extends Action {
	String song;

	@ActionInfoAnnotation(params = { "Song", "Priority", "MoneyVal" })
	public SongAction(String song, Integer priority, Double moneyVal) {
		super();
		this.song = song;
		this.priority = priority;
		ActionTraitElement<Double> t = new ActionTraitElement<Double>(moneyVal);
		traitVals.put(MoneyTrait.class, t);
		
		//added a bit of sheepishness. Hopefully, if actors are on the same street, a gangster won't sing
		ActionTraitElement<Double> shy = new ActionTraitElement<Double>(5.0);
		traitVals.put(ShyTrait.class, shy);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compareTo(Action arg0) {
		// TODO Auto-generated method stub
		return arg0.getPriority() - this.priority;
	}
	@Override
	public String doIt(Actor actor) {
		// TODO Auto-generated method stub
		return song;
	}
	
	@Override
	public String toString(){
		return song + " , PRIORITY IS " + priority + ", COST IS " + this.getTraitVal(MoneyTrait.class).getValue();
	}
	@Override
	public Action mutateAction(double x) {
		// TODO Auto-generated method stub
		if (x<0)
			this.song = "... singing is for chumps, boss.";
		
		if (x<10)
			this.song = song.substring(3);
		
		if (x<5)
			this.priority = 1;
		
		return this;
	}

}
