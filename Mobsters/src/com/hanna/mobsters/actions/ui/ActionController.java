/**
 * 
 */
package com.hanna.mobsters.actions.ui;

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
	}

	public Action getAction(){
		return this.action;
	}
	
	public ActionPanel getPanel() {
		return this.panel;
	}
	
	
	
}
