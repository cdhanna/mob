/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.hanna.mobsters.actions.MathAction;
import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.ui.ActionController;
import com.hanna.mobsters.actions.ui.ActionPanel;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.Response;
import com.hanna.mobsters.ui.Top;
import com.hanna.mobsters.ui.Window;

/**
 * @author Chris Hanna
 *
 */
public class ActorController {

	Actor actor;
	ActorPanel panel;

	ActionController actionController;

	public ActorController(Actor actor){
		this.actor = actor;	
		this.panel = new ActorPanel();
		this.panel.setUpComponents(this.actor);

		this.actionController = new ActionController(new MathAction(1.0,1.0,"+",1,100.0)){
			@Override
			public Action fireActionCreation(Action a){
				Response response = speakTo(a);
				actionController.setMessage(response.getMessage());
				return a;
			}
			@Override
			public void closingActionPanel(ActionPanel p){
				Top.toolBar.clearFocusPanel();
			}
		};

		this.panel.getPostButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setToolBarToAction();
			}});

		this.panel.getWakeUpButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				runAction();
			}});

		this.panel.getClearOutputButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.getOutputList().removeAllElements();
				panel.repaint();
			}});

	}

	public Actor getActor(){
		return this.actor;
	}

	public ActorPanel getPanel(){
		return this.panel;
	}

	public void setToolBarToAction(){
		this.panel.setActionPanel(this.actionController.getPanel());
		Top.toolBar.setFocusPanel(this.actionController.getPanel(), "ACTOR: " + this.actor.getName());
	}
	
	public void runAction(){
		this.panel.getPendingActionList().removeElement(this.actor.getPQ().peek());
		String output = this.actor.evaluateAction();
		this.panel.getOutputList().addElement(output);
		this.panel.repaint();
	}

	public Response speakTo(Action action){
		Response response = this.actor.speakTo(action);
		if (response.getYesNo()){
			this.panel.getPendingActionList().addElement(action);
			this.panel.repaint();
		}
		return response;
	}

}
