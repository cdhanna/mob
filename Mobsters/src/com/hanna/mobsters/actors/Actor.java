package com.hanna.mobsters.actors;

import java.util.*;
import com.hanna.mobsters.actions.*;
import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actors.properties.Property;
import com.hanna.mobsters.actors.properties.PropertyRegistry;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;
import com.hanna.mobsters.actors.traits.*;

public class Actor {
	ArrayList<Trait> personality;
	String name;
	int money; // dummy variable
	PriorityQueue<Action> pq; // for holding "to do list" of actions

	private HashMap<Class<? extends Property<?>>, Property<?>> propertyTable;

	// constructor - really just a stub right now
	public Actor(String name,int money)
	{
		this.name = name;
		this.money = money;
		personality = new ArrayList<Trait>();
		personality.add(new MoneyTrait(2)); // money value will be squared for this character
		pq = new PriorityQueue<Action>();

		this.propertyTable = PropertyRegistry.getInstance().makePropertyTable();
		
	}
	// static decision making method common to all actors
	private static double decider(Action a, ArrayList<Trait> personality){
		double w = 0;
		for (Trait t:personality)
			w+=t.compute(a);

		return w;
	}

	public Response speakTo(Action a){
		String str;
		boolean yesno = false;
		double decision = decider(a,personality);
		if (decision > 0){
			str = "I will do it";
			yesno = true;
			Action modAction = a.mutateAction(decision);
			pq.add(modAction);
		}
		else{
			str = "I will not do it";
			yesno = false;
		}
		return new Response(yesno, str);
	}

	public String evaluateAction(){
		
		if (!pq.isEmpty())
			return pq.remove().doIt();
		else
			return "I ain't got shit to do";
	}


	/**
	 * Grab the property instance for this actor
	 * @param property
	 * @return
	 */
	public final <P extends Property<?>> P getProperty(Class<? extends Property<?>> property){
		return (P) this.propertyTable.get(property);
	}
	
	/**
	 * Grab the value of the given property
	 * @param property
	 * @return
	 */
	public final <T> T getPropertyValue(Class<? extends Property<T>> property){
		return (T) this.propertyTable.get(property).getValue();
	}
	
	/**
	 * Set the value of the given property. The type of 'value' must match the type of the property.
	 * @param property
	 * @param value
	 */
	public final <T> void setPropertyValue(Class<? extends Property<T>> property, T value){
		Property<T> p = this.getProperty(property);
		p.setValue(value);
	}
	
	//	public final <T> void setPropertyValue(Class<? extends Property<T>> property, T object){
	//		this.propertyTable.get(property).setValue(object);
	//	}

	public String getName(){return name;}
	public PriorityQueue<Action> getPQ(){return pq;}

}
