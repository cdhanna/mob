/**
 * 
 */
package com.hanna.mobsters.actors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	public Actor createActor(String name){
		Actor actor = new Actor(name);
		this.allKnownActors.add(actor);
		this.table_nameToActor.put(name, actor);
		return actor;
	}
	
	public Actor lookUpActor(String name){
		return this.table_nameToActor.get(name);
	}
}
