package com.hanna.mobsters.actions.impl;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionInfoAnnotation;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.traits.ActionTraitElement;
import com.hanna.mobsters.actors.traits.MoneyTrait;

public class SongAction extends Action {
	String song;

	@ActionInfoAnnotation(params = { "Song", "Priority", "MoneyVal" })
	public SongAction(String song, Integer priority, Double moneyVal) {
		super();
		this.song = song;
		this.priority = priority;
		ActionTraitElement t = new ActionTraitElement("",moneyVal);
		traitVals.put(MoneyTrait.class, t);
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
		return song + " , PRIORITY IS " + priority + ", COST IS " + traitVals.get(MoneyTrait.class).getNumVal();
	}
	@Override
	public Action mutateAction(double x) {
		// TODO Auto-generated method stub
		if (x<0)
			return null;
		
		if (x<10)
			this.song = song.substring(3);
		
		if (x<5)
			this.priority = 1;
		
		return this;
	}

}
