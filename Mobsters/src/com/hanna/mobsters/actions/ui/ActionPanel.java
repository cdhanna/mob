/**
 * 
 */
package com.hanna.mobsters.actions.ui;

import javax.swing.JLabel;

import com.hanna.mobsters.actions.Action;
import com.hanna.mobsters.actions.ActionRegistry;
import com.hanna.mobsters.ui.shared.Panel;

/**
 * @author Chris Hanna
 *
 */
public class ActionPanel extends Panel{

	private JLabel title;
	

	@Override
	protected void initComponents() {
		this.title = new JLabel("Action");
	}

	@Override
	protected void addComponents() {
		this.add(this.title);
	}

	@Override
	public void setUpComponents(Object... parameters) {
		

		
		if (this.doesInputMatchExpected(parameters)){
			
		} else System.err.println("Could not set up Action. invalid params");
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{Action.class, ActionRegistry.class};
	}

}
