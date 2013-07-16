/**
 * 
 */
package com.hanna.mobsters.ui;


import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ui.ActorController;
import com.hanna.mobsters.actors.ui.ActorPanel;
import com.hanna.mobsters.ui.shared.Panel;

/**
 * @author Chris Hanna
 *
 */
public class TopPanel extends Panel{

	private ActorController controller;

	@Override
	protected void initComponents() {

	}

	@Override
	protected void addComponents() {

	}

	@Override
	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){
			this.addActor();
		}
	}


	@Override
	public Object[] getSetUpParameterTypes() {
		return new Object[]{};
	}



	public void addActor(){
		this.controller = new ActorController(new Actor(2)); //TODO replace '5' with an 'Actor'
		ActorPanel actorPanel = this.controller.getPanel();
		this.add(actorPanel);
	}
}
