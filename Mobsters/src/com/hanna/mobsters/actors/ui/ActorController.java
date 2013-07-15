/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import com.hanna.mobsters.actors.Actor;

/**
 * @author Chris Hanna
 *
 */
public class ActorController {

	Actor actor;
	ActorPanel panel;
	public ActorController(Actor actor){
		this.actor = actor;	
		this.panel = new ActorPanel();
		this.panel.setUpComponents(this.actor);
	}
	
	public Actor getActor(){
		return this.actor;
	}
	
	public ActorPanel getPanel(){
		return this.panel;
	}
	
}
