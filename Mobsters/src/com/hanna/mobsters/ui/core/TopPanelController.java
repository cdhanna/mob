/**
 * 
 */
package com.hanna.mobsters.ui.core;

import java.util.ArrayList;
import java.util.List;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.ui.ActorController;


/**
 * @author Chris Hanna
 *
 */
public class TopPanelController {

	private TopPanel panel;
	private List<ActorController> allActors;
	
	public TopPanelController(){
		this.panel = new TopPanel();
		
		this.allActors = new ArrayList<>();
		
		this.addActor(ActorBin.getInstance().createActor("Mike"));
		this.addActor(ActorBin.getInstance().createActor("Chuck"));
	}
	
	
	public TopPanel getPanel(){
		return this.panel;
	}
	
	public void addActor(Actor actor){
		
		ActorController actorController = this.panel.addActor(actor);
		this.allActors.add(actorController);
		
	}
	
	public void runActorsOnce(){
		for (ActorController ac : this.allActors)
			ac.runAction();
	}
	
//	public List<Actor> getActors(){
//		return this.allActors;
//	}
}
