package com.hanna.mobsters.actions.impl;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionInfoAnnotation;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.impl.MedicalStateProperty;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;
import com.hanna.mobsters.actors.traits.*;
/**
 * @author Will
 * Action for killing a designated actor.
 *
 */
public class MurderAction extends Action{
	Actor target;
	Double moneyRating; 
	Double physicalRiskRating;
	/**
	 * @param target the actor to be killed.
	 * @param priority the priority value used in the actor's queue.
	 * @param moneyRating - how much money will be paid to the actor for the job.
	 * @param dangerRating - how dangerous the murder will be to carry out.
	 */
	@ActionInfoAnnotation(params = { "target", "priority", "moneyRating" , "physicaRiskRating"})
	public MurderAction(Actor target, Integer priority, Double moneyRating, Double physicalRiskRating){
		super();
		this.priority = priority;
		this.target = target;
		this.moneyRating = moneyRating;
		this.physicalRiskRating = physicalRiskRating;
		Double moralRating = -0.95; // murder is about the most immoral thing you can do!
		ActionWeight<Double> t = new ActionWeight<Double>(moneyRating);
		traitVals.put(MoneyTrait.class, t);
		ActionWeight<Double> t1 = new ActionWeight<Double>(moralRating);
		traitVals.put(MoralityTrait.class, t1);
		ActionWeight<Double> t2 = new ActionWeight<Double>(physicalRiskRating);
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
		this.target.setPropertyValue(MedicalStateProperty.class, 0.0);
		//TODO actually make this destroy the target actor.
		return str;
	}
	@Override
	public String toString(){
		String str = "I am getting paid " + this.moneyRating + " to kill " + this.target + ". Physical Risk is " + this.physicalRiskRating; 
		return str;
	}

	@Override
	public Action mutateAction(double x) {
		if (x > 0)
			return this;
		else
			return null;
	}
	
	public Double getContextWeight(Actor actor, Class<? extends Trait> clazz) {
		
		if (clazz == MoneyTrait.class){
			Double coefficient = 1.0;
			if (actor.getPropertyValue(MoneyProperty.class) < 1.0)
				coefficient = this.moneyRating;
			else coefficient = this.moneyRating/actor.getPropertyValue(MoneyProperty.class);
			return coefficient;
		}
		
		if (clazz == MoralityTrait.class){
			Double coefficient = 1.0;
			return coefficient;
		}
		
		if (clazz == DangerTrait.class){
			Double coefficient = 1.0;
			return coefficient;
		}
		return 0.0;
	}

}
