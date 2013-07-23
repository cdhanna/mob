package com.hanna.mobsters.actors;

import java.util.*;
import com.hanna.mobsters.actions.*;
import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actors.personality.Personality;
import com.hanna.mobsters.actors.personality.PersonalityRegistry;
import com.hanna.mobsters.actors.properties.Property;
import com.hanna.mobsters.actors.properties.PropertyRegistry;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;
import com.hanna.mobsters.actors.traits.*;

public class Actor {
	private List<Trait> personality;
	private String name;
	
	private PriorityQueue<Action> pq; // for holding "to do list" of actions

	private HashMap<Class<? extends Property<?>>, Property<?>> propertyTable;

	private Personality personalityType;

	// constructor - really just a stub right now
	public Actor(String name, Personality personalityType, List<Trait> personalityOverrides)
	{
		this.personalityType = personalityType;
		this.personality = PersonalityRegistry.getInstance().getPersonality(personalityType);

//		if (personalityOverrides != null){
//			List<Trait> unAddedTraits = new ArrayList<>(personalityOverrides); //make a list to hold traits that don't wind up being in personality
//			for (Trait overrideTrait : personalityOverrides){
//				for (Trait regularTrait : this.personality){
//					if (overrideTrait.getClass() == regularTrait.getClass()){ //if the override trait is the regular trait
//						regularTrait = overrideTrait; //make the regular BE the override
//						unAddedTraits.remove(overrideTrait); //we added this trait, so we don't need to add it later
//					}
//				}
//			}
//			this.personality.addAll(unAddedTraits); //add the rest of the override traits
//		}

		this.name = name;
		//this.money = money;
		//personality = new ArrayList<Trait>();
		//personality.add(new MoneyTrait(2)); // money value will be squared for this character//THIS HAS
		//BEEN MOVED TO BE THE GANGSTER PERSONALITY TYPE
		pq = new PriorityQueue<Action>();

		this.propertyTable = PropertyRegistry.getInstance().makePropertyTable();

	}
	// static decision making method common to all actors
	private static double decider(Action action, Actor actor){
		
		double combinedWeight = 0;
		for (Trait trait : actor.personality){
			combinedWeight+=trait.compute(action, actor);
		}

		return combinedWeight;
	}

	public Response speakTo(Action action){
		String responseMessage;
		boolean yesno = false;
		double decision = decider(action, this);
		action = action.mutateAction(decision);
		if (action != null){
			responseMessage = "I will do it";
			yesno = true;
			pq.add(action);
		}
		else{
			responseMessage = "I will not do it";
			yesno = false;
		}
		return new Response(yesno, responseMessage);
	}

	public String evaluateAction(){

		if (!pq.isEmpty())
			return pq.remove().doIt(this);
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
	 * This seems pretty hoaky to me, but I am at my wits end. <br>
	 * <b> WARNING </b><br>
	 * only use this if YOU KNOW your inputs are valid
	 * @param property the class of the property you get 
	 * @return
	 */
	public final Object getPropertyValueUnSafe(Object property){
		Property p = this.getProperty((Class<? extends Property<?>>) property);
		if (p == null) System.err.println("Actor.getpropertyvalueUnSafe: " + property);
		return p.getValue();
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

	/**
	 * This seems pretty hoaky to me, but I am at my wits end. <br>
	 * <b> WARNING </b><br>
	 * only use this if YOU KNOW your inputs are valid
	 * @param property the class of the property you want to change
	 * @param value a valid value for the property
	 */
	public final void setPropertyValueUnSafe(Object property, Object value){
		Property p = this.getProperty((Class<? extends Property<?>>) property);
		if (p == null) System.err.println("Actor.setpropertyvalueUnSafe: " + property);
		p.setValue(value);
	}


	/**
	 * @return The name of this actor. 
	 */
	public String getName(){return name;}
	
	
	public PriorityQueue<Action> getPQ(){return pq;}

	@Override
	public String toString(){ return this.name;}

}
