package com.hanna.mobsters.actions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
		this.register(SongAction.class);
		
		
		
	}

	private void register(Class<? extends Action> actionClass){
		this.actions.add(actionClass);
	}

	public List<Class<? extends Action>> getRegisteredClasses(){
		return this.actions;
	}

	public Constructor<?> getActionConstructor(Class<? extends Action> clazz){
		Constructor<?>[] consArray = clazz.getDeclaredConstructors();
		if (consArray.length == 1){
			return consArray[0];
		} else{
			Exception e = new Exception("YOU STUPID FACK! An action : " + clazz + " has more than 1 constructor");
			e.printStackTrace();
			return null;
		}
	}

	public Class<?>[] getActionConstructorParameterTypes(Class<? extends Action> clazz){
		Constructor<?> con = this.getActionConstructor(clazz);
		if (con != null){
			return con.getParameterTypes();
		} else return null;
	}
	
	public Action createAction(Class<? extends Action> clazz, Object... parameters){
		
		Constructor<?> con = this.getActionConstructor(clazz);
		if (con != null){
			Class<?>[] paramTypes = con.getParameterTypes();
			
			boolean typesMatch = true;
			if (paramTypes.length != parameters.length)
				typesMatch = false;
			for (int i = 0 ; i < paramTypes.length ; i ++){
				if (parameters[i] == null)
				{
					typesMatch = false;
					break;
				}
				if (paramTypes[i] != parameters[i].getClass())
					if (paramTypes[i] != parameters[i].getClass().getSuperclass())
						typesMatch = false;
			}
			if (typesMatch){
				try {
					Action action = (Action) con.newInstance(parameters);
					return action;
				} catch (InstantiationException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return null;
			} else return null;
		} else return null;
	}
}
