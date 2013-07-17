package com.hanna.mobsters.actions;

import java.util.ArrayList;
import java.util.List;

public class ActionRegistry {

	private static ActionRegistry instance;
	public static ActionRegistry getInstance(){
		if (instance == null)
			instance = new ActionRegistry();
		return instance;
	}
	
	private List<Class<? extends Action>> actions;
	
	private ActionRegistry(){
		this.actions = new ArrayList<>();
		
		//TODO replace with reflections
		this.register(MathAction.class);
		
	}
	
	private void register(Class<? extends Action> actionClass){
		this.actions.add(actionClass);
	}
	
	public List<Class<? extends Action>> getRegisteredClasses(){
		return this.actions;
	}

}
