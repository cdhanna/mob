package com.hanna.mobsters.actors.traits;
import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actors.Actor;
public abstract class Trait {
	
	int importance;
	public abstract double compute(Action a, Actor actor);


}
