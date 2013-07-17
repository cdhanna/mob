package com.hanna.mobsters.actions;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionInfoAnnotation;
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
	public String doIt() {
		// TODO Auto-generated method stub
		return song;
	}
	
	@Override
	public String toString(){
		return song + " , PRIORITY IS " + priority + ", COST IS " + traitVals.get(MoneyTrait.class).getNumVal();
	}

}
