/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.hanna.mobsters.actions.Action;
import com.hanna.mobsters.actions.MathAction;
import com.hanna.mobsters.actions.ui.ActionController;
import com.hanna.mobsters.actors.Actor;

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
				speakTo(a);
				return a;
			}
		};
		
		this.panel.getPostButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				panel.setActionPanel(actionController.getPanel());
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
	
	public void runAction(){
		this.panel.getPendingActionList().removeElement(this.actor.getPQ().peek());
		String output = this.actor.evaluateAction();
		this.panel.getOutputList().addElement(output);
		this.panel.repaint();
	}
	
	public void speakTo(Action action){
		this.actor.speakTo(action);
		this.panel.getPendingActionList().addElement(action);
		this.panel.repaint();
	}
	
}
