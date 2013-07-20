/**
 * 
 */
package com.hanna.mobsters.actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hanna.mobsters.actors.personality.Personality;
import com.hanna.mobsters.actors.traits.Trait;

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
	
	private ActorBin(){
		this.allKnownActors = new ArrayList<>();
		this.table_nameToActor = new HashMap<>();
	}
	
	private Actor setUpActor(Actor actor, String name){
		this.allKnownActors.add(actor);
		this.table_nameToActor.put(name, actor);
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
}
