/**
 * 
 */
package com.hanna.mobsters.ui.core;


import com.hanna.mobsters.actions.impl.MathAction;
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

	private ToolBarController toolBar;
	
	@Override
	protected void initComponents() {
		this.toolBar = new ToolBarController();
	}

	@Override
	protected void addComponents() {
		this.add(this.toolBar.getPanel(), "cell 0 0, pushx, pushy, growy");
	}

	@Override
	public void setUpComponents(Object... parameters) {
		
		
		
		if (this.doesInputMatchExpected(parameters)){

			
		}
	}


	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{};
	}

	public ToolBarController getToolBarController(){
		return this.toolBar;
	}

	public ActorController addActor(Actor actor){
		
		ActorController actorController = new ActorController(actor); 
		ActorPanel actorPanel = actorController.getPanel();
		this.add(actorPanel);
		return actorController;
	}
}
