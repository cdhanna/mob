/**
 * 
 */
package com.hanna.mobsters.actions.ui;

import javax.swing.JLabel;

import com.hanna.mobsters.actions.Action;
import com.hanna.mobsters.actions.ActionRegistry;
import com.hanna.mobsters.ui.shared.ComboBox;
import com.hanna.mobsters.ui.shared.Panel;

/**
 * @author Chris Hanna
 *
 */
public class ActionPanel extends Panel{

	private JLabel title;
	private ComboBox<Class<? extends Action>> availableActionsBox;

	private JLabel actionLabel;
	
	@Override
	protected void initComponents() {
		this.title = new JLabel("Action");
		this.availableActionsBox = new ComboBox<>("Select an Action");
		this.actionLabel = new JLabel("Action Constructor");
	}

	@Override
	protected void addComponents() {
		this.add(this.title);
		this.add(this.availableActionsBox);
	}

	@Override
	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){
			Action action = (Action)parameters[0];
			ActionRegistry registry = (ActionRegistry)parameters[1];
		
			this.availableActionsBox.setElements(registry.getRegisteredClasses());
			
		} else System.err.println("Could not set up Action. invalid params");
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{Action.class, ActionRegistry.class};
	}

}
