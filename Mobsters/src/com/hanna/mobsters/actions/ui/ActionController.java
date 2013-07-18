/**
 * 
 */
package com.hanna.mobsters.actions.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionRegistry;
import com.hanna.mobsters.actions.core.ActionRegistry.ActionInfo;

/**
 * @author Chris Hanna
 *
 */
public class ActionController {

	private Action action;
	private ActionPanel panel;

	public ActionController(Action action){

		this.action = action;
		this.panel = new ActionPanel();
		this.panel.setUpComponents(this.action, ActionRegistry.getInstance());

		this.panel.getAvailableActionsBox().addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				Class<? extends Action> clazz = panel.getAvailableActionsBox().getSelection();
				if (clazz!=null){
					Constructor c = ActionRegistry.getInstance().getActionConstructor(clazz);
					Class<?>[] p = ActionRegistry.getInstance().getActionConstructorParameterTypes(clazz);
					panel.setConstructor(c);
					ActionInfo info = ActionRegistry.getInstance().parseAnnotation(clazz);
					panel.setUpValuesPanel(info, (Object[])p);
					
				}
			}});

		this.panel.getPostButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Class<? extends Action> clazz = panel.getAvailableActionsBox().getSelection();
				if (clazz!=null){
					Object[] parameters = panel.getValues();
					Action a = ActionRegistry.getInstance().createAction(clazz, parameters);
					fireActionCreation(a);
				}
			}});
		
		this.panel.getCloseButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				closingActionPanel(panel);
				if (panel.getParent()!=null){
					Container parent = panel.getParent();
					panel.getParent().remove(panel);
					parent.revalidate();
					parent.repaint();
				}
			}});
		
	}

	public Action fireActionCreation(Action a){
		return a;
	}
	public void closingActionPanel(ActionPanel panel){
		
	}
	
	public void setMessage(String message){
		this.panel.setMessage(message);
	}
	
	public Action getAction(){
		return this.action;
	}

	public ActionPanel getPanel() {
		return this.panel;
	}

	

}
