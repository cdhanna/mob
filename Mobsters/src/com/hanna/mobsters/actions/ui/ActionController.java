/**
 * 
 */
package com.hanna.mobsters.actions.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Constructor;

import com.hanna.mobsters.actions.Action;
import com.hanna.mobsters.actions.ActionRegistry;

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
					panel.getValuesPanel().setUpComponents((Object[])p);
				}
			}});

		this.panel.getPostButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Class<? extends Action> clazz = panel.getAvailableActionsBox().getSelection();
				if (clazz!=null){
					Object[] parameters = panel.getValues();
					Action a = ActionRegistry.getInstance().createAction(clazz, parameters);
					System.out.println(a);
				}
			}});
		
	}

	public Action getAction(){
		return this.action;
	}

	public ActionPanel getPanel() {
		return this.panel;
	}



}
