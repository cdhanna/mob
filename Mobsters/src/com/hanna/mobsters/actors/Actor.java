package com.hanna.mobsters.actors;

import java.util.*;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionResult;
import com.hanna.mobsters.actions.impl.results.RefusedResult;
import com.hanna.mobsters.actors.personality.Personality;
import com.hanna.mobsters.actors.personality.PersonalityRegistry;
import com.hanna.mobsters.actors.properties.Property;
import com.hanna.mobsters.actors.properties.PropertyRegistry;
import com.hanna.mobsters.actors.properties.impl.MedicalStateProperty;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;
import com.hanna.mobsters.actors.traits.*;
import com.hanna.mobsters.histories.GameEvent;
import com.hanna.mobsters.histories.EventKey;
import com.hanna.mobsters.histories.GameHistory;

/**
 * @author Will
 *
 */
public class Actor {
	private List<Trait> personality;
	private String name;
	private PriorityQueue<Action> pq; // for holding "to do list" of actions
	private HashMap<Class<? extends Property<?>>, Property<?>> propertyTable;
	private ArrayList<EventKey> history;
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
		history = new ArrayList<EventKey>();
		this.name = name;
		pq = new PriorityQueue<Action>();
		this.propertyTable = PropertyRegistry.getInstance().makePropertyTable();

	}


	/**
	 * A static method for making decisions. An action and an actor are passed in.
	 * For each trait in the actor's personality a weight is computed, and the sum of all the 
	 * weights (positive and negative) will be returned.
	 * @param action - the action to be considered
	 * @param actor - the actor making the decision
	 * @return - Decision object which contains information about how the actor made the decision
	 */
	private static Decision decider(Action action, Actor actor){
		Decision d = new Decision();
		for (Trait trait : actor.personality){
			d.addTerm(trait.getClass(), trait.compute(action, actor));
		}
		d.makeDecision();

		return d;
	}


	/**
	 * This method takes an action as input and passes it to the static decider method in the Actor class.
	 * Based on the return value of the decider, the action may or may not be added to the priority queue
	 * of actions for the actor. If it is added to the queue, it is also possible that the action will be 
	 * modified by changing its parameters in a call to the mutateAction method.
	 * @param Action action - the action given to the actor for consideration
	 * @return Response - a wrapper object containing a boolean decision value, a human-readable message
	 * giving the actor's decision, and a Decision object which contains information about how the actor 
	 * made the decision
	 */
	public Response speakTo(Action action){
		//TODO Make the thread ID actally work
		Integer threadID = 1;
		GameHistory gameHistory = GameHistory.getInstance();
		Response response;
		EventKey eventKey = action.addToHistory(threadID,gameHistory);
		GameEvent event = gameHistory.getEvent(eventKey);
		if (this.getPropertyValue(MedicalStateProperty.class) > 0.0){
			String responseMessage;
			boolean yesno = false;
			Decision d = decider(action, this);
			Double decision = d.getDecision();
			action = action.mutateAction(decision);
			event.setMutation(action);
			if (action != null){
				responseMessage = "I will do it";
				yesno = true;
				pq.add(action);
			}
			else{
				responseMessage = "I will not do it";
				event.setResult(new RefusedResult());
				yesno = false;
			}
			response =  new Response(yesno, responseMessage, d);
		} else response =  new Response(false, "No. I'm currently dead.",new Decision());
		
		event.setResponse(response);
		history.add(eventKey);
		gameHistory.putEvent(eventKey, event);
		return response;
	}
	


	/**
	 * Removes the highest priority element in the actor's priority queue and evaluates it.
	 * @return String - a human readable message describing the result of the action.
	 */
	public String evaluateAction(){
		GameHistory gameHistory = GameHistory.getInstance();
		ActionResult actionResult;
		Action action;
		String result;
		Boolean flag = true;
		if (this.getPropertyValue(MedicalStateProperty.class) > 0.0){
			if (!pq.isEmpty()){
				action = pq.remove();
				result =  action.doIt(this);
				actionResult = new ActionResult(result);
				EventKey eventKey = action.getEventKey();
				GameEvent event = gameHistory.getEvent(eventKey);
				event.setResult(actionResult);
				gameHistory.putEvent(eventKey, event);
			}
			else{
				result =  "I ain't got shit to do";
				flag = false;
			}
		} else result = "I am dead, you jerk.";
		
		
		return result;
			
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
	
	public ArrayList<GameEvent> tellHistory(Integer nTurns){
		//TODO this does not find events from earlier turns that are added later to the history
		if (history.size() == 0)
		return null;
		
		ArrayList<GameEvent> masterList = new ArrayList<GameEvent>();
		if (nTurns<=0)
			return null;
		
		GameHistory gameHistory = GameHistory.getInstance();
		int len = history.size();
		nTurns = Math.min(nTurns, len);
		int mostRecentTurn = history.get(len - 1).getTurnNumber();
		int n = mostRecentTurn - nTurns + 1; // take care of offset
		
		for (EventKey eventKey : history){
			if (eventKey.getTurnNumber() >= n)
				masterList.add(gameHistory.getEvent(eventKey));
		}
		return masterList;
	}

}
