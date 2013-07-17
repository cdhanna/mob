package com.hanna.mobsters.actors.traits;
import com.hanna.mobsters.actions.core.Action;
public abstract class Trait {
	
	int importance;
	public abstract double compute(Action a);


}
