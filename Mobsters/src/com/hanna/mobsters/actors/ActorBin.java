/**
 * 
 */
package com.hanna.mobsters.actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hanna.mobsters.actors.personality.Personality;
import com.hanna.mobsters.actors.properties.Location;
import com.hanna.mobsters.actors.properties.impl.InventoryProperty;
import com.hanna.mobsters.actors.properties.impl.LocationProperty;
import com.hanna.mobsters.actors.traits.Trait;
import com.hanna.mobsters.actors.ui.bin.ActorBinController;
import com.hanna.mobsters.items.impl.guns.pistol.Pistol;
import com.hanna.mobsters.items.impl.vehicles.cars.Car;

/**
 * @author Chris Hanna
 *
 */
public class ActorBin {

	private static ActorBin instance;
	public static ActorBin getInstance(){
		if (instance == null)
			instance = new ActorBin();
		return instance;
	}
	
	private List<Actor> allKnownActors;
	private HashMap<String, Actor> table_nameToActor;
	private List<ActorBinListener> listeners;
	
	private ActorBin(){
		this.listeners = new ArrayList<>();
		this.allKnownActors = new ArrayList<>();
		this.table_nameToActor = new HashMap<>();
	}
	
	private Actor setUpActor(Actor actor, String name){
		this.allKnownActors.add(actor);
		this.table_nameToActor.put(name, actor);
		
		actor.getPropertyValue(InventoryProperty.class).put(new Pistol());
		actor.getPropertyValue(InventoryProperty.class).put(new Car());
		//TODO remove this. THis should exist elsewhere, and when we move to the real game, this wont be here...
		ActorBinController.getInstance().addActor(actor);
		this.fireActorAdded(actor);
		return actor;
	}
	
	public Actor createActor(String name){
		Actor actor = new Actor(name, Personality.DEFAULT, null);
		return this.setUpActor(actor, name);
	}
	
	public Actor createActor(String name, Personality personality){
		Actor actor = new Actor(name, personality, null);
		return this.setUpActor(actor, name);
	}
	
	public Actor createActor(String name, Personality personality, List<Trait> personalityOverrides){
		Actor actor = new Actor(name, personality, personalityOverrides);
		return this.setUpActor(actor, name);
	}
	
	public Actor createActor(String name, List<Trait> personalityOverrides){
		Actor actor = new Actor(name, Personality.DEFAULT, personalityOverrides);
		return this.setUpActor(actor, name);
	}
	
	public Actor lookUpActor(String name){
		return this.table_nameToActor.get(name);
	}
	
	public Actor[] getNearByActors(Actor actor){
		
		List<Actor> neighbors = new ArrayList<>();
		for (Actor other : this.allKnownActors)
			if (other != actor){
				Location otherLocation = other.getPropertyValue(LocationProperty.class);
				if (actor.getPropertyValue(LocationProperty.class).isOnStreet(otherLocation)){
					neighbors.add(other);
				}
			}
		return neighbors.toArray(new Actor[0]);
	}
	
	/**
	 * @return all of the actors. be careful, removing an actor from list will also remove it form the bin.
	 */
	public List<Actor> getAllActors(){
		return this.allKnownActors;
	}
	public void removeActor(Actor actor){
		this.allKnownActors.remove(actor);
		this.fireActorRemoved(actor);
	}
	
	public void addListener(ActorBinListener listener){
		this.listeners.add(listener);
	}
	public void removeListener(ActorBinListener listener){
		this.listeners.remove(listener);
	}
	private void fireActorRemoved(Actor actor){
		for (ActorBinListener listener : this.listeners)
			listener.actorRemoved(actor);
	}
	private void fireActorAdded(Actor actor){
		for (ActorBinListener listener : this.listeners)
			listener.actorAdded(actor);
	}
	
	public interface ActorBinListener{
		public void actorAdded(Actor actor);
		public void actorRemoved(Actor actor);
	}
}
