package com.hanna.mobsters.actions.impl;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionInfoAnnotation;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.traits.*;
/**
 * @author Will
 * Action for killing a designated actor.
 *
 */
public class MurderAction extends Action{
	Actor target;
	Double moneyRating; 
	/**
	 * @param target the actor to be killed.
	 * @param priority the priority value used in the actor's queue.
	 * @param moneyRating - how much money will be paid to the actor for the job.
	 * @param dangerRating - how dangerous the murder will be to carry out.
	 */
	@ActionInfoAnnotation(params = { "target", "priority", "moneyRating" , "dangerRating"})
	public MurderAction(Actor target, Integer priority, Double moneyRating, Double dangerRating){
		super();
		this.priority = priority;
		this.target = target;
		this.moneyRating = moneyRating;
		Double moralRating = -0.95; // murder is about the most immoral thing you can do!
		ActionWeight<Double> t = new ActionWeight<Double>(moneyRating);
		traitVals.put(MoneyTrait.class, t);
		ActionWeight<Double> t1 = new ActionWeight<Double>(moralRating);
		traitVals.put(MoralityTrait.class, t1);
		ActionWeight<Double> t2 = new ActionWeight<Double>(dangerRating);
		traitVals.put(DangerTrait.class, t2);

	}
	
	@Override
	public int compareTo(Action arg0) {
		return arg0.getPriority() - this.priority;
	}

	@Override
	public String doIt(Actor actor) {
		actor.pay(this.moneyRating);
		String str = "I killed him good!";
		//TODO actually make this destroy the target actor.
		return str;
	}

	@Override
	public Action mutateAction(double x) {
		// TODO Auto-generated method stub
		return null;
	}

}
