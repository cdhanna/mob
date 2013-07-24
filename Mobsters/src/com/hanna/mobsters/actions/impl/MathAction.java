package com.hanna.mobsters.actions.impl;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionInfoAnnotation;
import com.hanna.mobsters.actions.core.ActionWeight;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;
import com.hanna.mobsters.actors.traits.*;

public class MathAction extends Action{
	double a,b;
	String op;
	/**
	 * The money value associated with this action. This is always a positive value. If the action would
	 * 'cost' money to do, this is reflected in the ActionWeight for the MoneyTrait.
	 */
	Double moneyValue;

	@ActionInfoAnnotation(params = { "a", "b","Operation", "Priority", "moneyVal" })
	public MathAction(Double a, Double b,String op, Integer priority, Double moneyVal) {
		this.a = a;
		this.b = b;
		this.op = op;
		this.priority = priority;
		ActionWeight<Double> t = new ActionWeight<Double>(Math.signum(moneyVal));
		this.moneyValue = Math.abs(moneyVal);
		traitVals.put(MoneyTrait.class, t);
	}

	public String doIt(Actor actor){
		String str;

		switch (op.charAt(0)){
		case '+': str = "I added " +a+ " and " +b+ " and got " + (a+b);
		break;
		case '-': str = "I subtracted " +b+ " from " +a+ " and got " + (a-b);
		break;
		case '*': str = "I multiplied " +a+ " and " +b+ " and got " + (a*b);
		break;
		case '/':
			if (b!=0){
				str = "I divided " +a+ " by " +b+ " and got " + (a/b);}
			else
				str = " I cannot divide " +a+ "by zero boss";
			break;
		default: str = "Unkown operation " +op;
		break;
		}

		actor.pay(this.moneyValue);
		return str;
	}

	@Override
	public String toString(){
		return "OPERATION IS " +a+op+b +", PRIORITY IS " + priority + ", COST IS " + this.moneyValue;
	}

	@Override
	public int compareTo(Action arg0) {
		return arg0.getPriority() - this.priority;
	}

	@Override
	public Action mutateAction(double x) {
		if(x<0)
			return new SongAction("Ohhhh I hate doing math but I love to sing!", this.priority,0.0, 0.0);

		if (x<5)
			this.b -= 1;

		if (x<10)
			this.a = this.a + 2;

		return this;

	}

	@Override
	public Double getContextWeight(Actor actor, Class<? extends Trait> clazz) {

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
