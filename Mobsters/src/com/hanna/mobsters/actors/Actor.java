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

/**
 * @author Will
 *
 */
public class Actor {
	private List<Trait> personality;
	private String name;
	private PriorityQueue<Action> pq; // for holding "to do list" of actions
	private HashMap<Class<? extends Property<?>>, Property<?>> propertyTable;

	private Personality personalityType;

	
	public Actor(String name, Personality personalityType, List<Trait> personalityOverrides)
	{
		this.personalityType = personalityType;
		this.personality = PersonalityRegistry.getInstance().getPersonality(personalityType);

		// CAN WE DELETE THIS COMMENTED CODE? - Will 24 July
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
		pq = new PriorityQueue<Action>();
		this.propertyTable = PropertyRegistry.getInstance().makePropertyTable();

	}
	
	
	/**
	 * A static method for making decisions. An action and an actor are passed in.
	 * For each trait in the actor's personality a weight is computed, and the sum of all the 
	 * weights (positive and negative) will be returned.
	 * @param Action action - the action to be considered
	 * @param Actor actor - the actor making the decision
	 * @return Double combinedWeight - an UNBOUNDED number corresponding to how strongly the actor does or does 
	 *  not want to do the action
	 */
	private static double decider(Action action, Actor actor){
		
		double combinedWeight = 0;
		for (Trait trait : actor.personality){
			combinedWeight+=trait.compute(action, actor);
		}

		return combinedWeight;
	}

	
	/**
	 * This method takes an action as input and passes it to the static decider method in the Actor class.
	 * Based on the return value of the decider, the action may or may not be added to the priority queue
	 * of actions for the actor. If it is added to the queue, it is also possible that the action will be 
	 * modified by changing its parameters in a call to the mutateAction method.
	 * @param Action action - the action given to the actor for consideration
	 * @return Response - a wrapper object containing a boolean decision value and a human-readable message
	 * giving the actor's decision
	 */
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

	
	/**
	 * Removes the highest priority element in the actor's priority queue and evaluates it.
	 * @return String - a human readable message describing the result of the action.
	 */
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
	
	/**
	 * @return the set of the actor's personality. Becare, if you edit this list, you will edit the actors personality
	 */
	public List<Trait> getPersonality(){
		return this.personality;
	}
	
	/**
	 * @return the priority queue of this actor
	 */
	public PriorityQueue<Action> getPQ(){return pq;}

	@Override
	public String toString(){ return this.name;}
	
	
	/** helper function used for paying an actor some money
	 * @param amount the amount of money coming in to the actor
	 */
	public void pay(double amount){
		amount = Math.abs(amount); // gets rid of accidental negative values
		Double oldCash = getPropertyValue(MoneyProperty.class);
		setPropertyValue(MoneyProperty.class, oldCash + amount);
	}
	
	
	/** helper function to remove money from an actor
	 * @param Double amount - the amount of money to take away
	 * @return Double amount - the amount of money taken away. May be less that the amount asked for,
	 * if the actor did not have that much money to begin with.
	 */
	public Double takeMoney(Double amount){
		Double existingMoney = getPropertyValue(MoneyProperty.class);
		if ( amount > existingMoney ){
			amount = existingMoney;
		}
		setPropertyValue(MoneyProperty.class, existingMoney - amount);
		return amount;
			
	}

}
