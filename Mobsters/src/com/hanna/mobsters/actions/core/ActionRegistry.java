package com.hanna.mobsters.actions.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.hanna.mobsters.utilities.ReflectionsHelper;

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

		this.scanForActions();

	}

	private void scanForActions(){
		
		Set<Class<? extends Action>> subTypes = ReflectionsHelper.getInstance().getSubTypes(Action.class, "com.hanna.mobsters.actions.impl");
		//System.out.println("Actions Scanned " +subTypes.size());
		for (Class<? extends Action> c : subTypes){
			//System.out.println("\t"+c);
			this.register(c);
		}
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

	public ActionInfo getActionInfo(Class<? extends Action> clazz){
		Constructor<?> con = ActionRegistry.getInstance().getActionConstructor(clazz);
		return this.getActionInfo(con);
	}
	public ActionInfo getActionInfo(Constructor<?> con){
		if (con != null){
			if (con.isAnnotationPresent(ActionInfoAnnotation.class)){
				ActionInfoAnnotation test = (ActionInfoAnnotation) con.getAnnotation(ActionInfoAnnotation.class);
				String name = test.name();
				String[] params = test.params();
				return new ActionInfo(name, params, con.getParameterTypes());
			}
		}
		return null;
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
					
					e.printStackTrace();
				}

				return null;
			} else return null;
		} else return null;
	}
	
	
	
	public class ActionInfo {

		private String name;
		private String[] parameterDescriptions;
		private Class<?>[] parameters;
		
		public ActionInfo(String name, String[] parameterDescriptions, Class<?>[] parameters){
			this.name = name;
			this.parameterDescriptions = parameterDescriptions;
			this.parameters = parameters;
			if (name == null)
				this.name = "";
			if (parameters == null)
				this.parameterDescriptions = new String[]{};
		}
		public String getName(){
			return this.name;
		}
		public String[] getParameterDescriptions(){
			return this.parameterDescriptions;
		}
		public Class<?>[] getParameters(){
			return this.parameters;
		}
	}
}
