package com.hanna.mobsters.actions.impl;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actors.Actor;

/**
 * A dummy Action for use as a GameEvent default mutated action. <br>
 * Only has one method - toString, which returns the word 'None'
 * @author Will
 *
 */
public class NoMutateAction extends Action {

	public NoMutateAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Action arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String doIt(Actor actor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action mutateAction(double x) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString(){
		return "None";
	}

}
