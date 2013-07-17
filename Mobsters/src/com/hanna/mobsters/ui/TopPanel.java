/**
 * 
 */
package com.hanna.mobsters.ui;


import com.hanna.mobsters.actions.MathAction;
import com.hanna.mobsters.actions.ui.ActionController;
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
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{};
	}



	public void addActor(){
		
		Actor mike = new Actor("Mike", 2);
		
		this.controller = new ActorController(mike); 
		ActorPanel actorPanel = this.controller.getPanel();
		this.add(actorPanel);
		
		controller.speakTo(new MathAction(2.0,3.0,"+",2));
	}
}
