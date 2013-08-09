/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.impl.MathAction;
import com.hanna.mobsters.actions.ui.ActionController;
import com.hanna.mobsters.actions.ui.ActionPanel;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.Response;
import com.hanna.mobsters.actors.properties.Property;
import com.hanna.mobsters.actors.properties.PropertyRegistry;
import com.hanna.mobsters.actors.traits.Trait;
import com.hanna.mobsters.ui.Top;
import com.hanna.mobsters.ui.Window;
import com.hanna.mobsters.ui.shared.ValuesPanel.Value;

/**
 * @author Chris Hanna
 *
 */
public class ActorController {

	private static List<ActorController> all = new ArrayList<>();
	
	private Actor actor;
	private ActorPanel panel;

	private ActionController actionController;
	
	
	public ActorController(final Actor actor){
		all.add(this);
		this.actor = actor;	
		this.panel = new ActorPanel(){
			@Override
			protected void propertyChange(Value value){
				if (value.getValue()!=null){
					actor.setPropertyValueUnSafe(value.getID(), value.getValue());
				}
			}
			@Override
			protected void traitChange(Value value){
				if (value.getValue() != null){
					Trait trait = (Trait) value.getID();
					trait.setImportance((int)value.getValue());
					System.out.println("trait change to " + value.getValue());
					int s = actor.getPersonality().get(actor.getPersonality().indexOf(trait)).getImportance();
					System.out.println(s);
				}
			}
		};
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
		all_refreshActorProperties();
		this.panel.repaint();
	}

	private void all_refreshActorProperties(){
		for (ActorController c : all){
			c.refreshActorProperties();
		}
	}
	
	public void refreshActorProperties(){
		Object[] propValues = new Object[PropertyRegistry.getInstance().getKnownProperties().size()];
		for (int i = 0 ; i < propValues.length ; i ++){
			Class prop = PropertyRegistry.getInstance().getKnownProperties().get(i);
			propValues[i] = actor.getPropertyValue(prop);
		}
		this.panel.setActorProperties(propValues);
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
