package com.hanna.mobsters.ui.shared.listpanel.impl;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.ActorBin.ActorBinListener;
import com.hanna.mobsters.ui.shared.listpanel.ListPanel;

public class ActorListPanel extends ListPanel<Actor>{

	private ActorBinListener listener;
	private boolean listenerAdded;
	
	public ActorListPanel(String name){
		super();
		
		this.setUpComponents(name, ActorBin.getInstance().getAllActors());
		
		this.listener = new ActorBinListener(){
			@Override
			public void actorAdded(Actor actor) {
				addElement(actor);
			}

			@Override
			public void actorRemoved(Actor actor) {
				removeElement(actor);
			}};
		
			
		ActorBin.getInstance().addListener(this.listener);
		this.listenerAdded = true;
	}

	public void removeListener() {
		if (this.listenerAdded){
			this.listenerAdded = false;
			ActorBin.getInstance().removeListener(this.listener);
		}
	}
	public void addListener(){
		if (!this.listenerAdded){
			this.listenerAdded = true;
			ActorBin.getInstance().addListener(this.listener);
		}
	}
	
	
	
}
