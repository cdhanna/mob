/**
 * 
 */
package com.hanna.mobsters.actions.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.TypeVariable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionRegistry;
import com.hanna.mobsters.actions.core.ActionRegistry.ActionInfo;
import com.hanna.mobsters.ui.shared.ComboBox;
import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.ui.shared.TextField;

/**
 * @author Chris Hanna
 *
 */
public class ActionPanel extends Panel{

	private JLabel title;
	private ComboBox<Class<? extends Action>> availableActionsBox;

	private JLabel actionLabel;
	private ValuesPanel valuesPanel;
	private JButton postButton;
	private JButton closeButton;
	private JLabel messageLabel;
	private KeyAdapter valuesListener;
	
	@Override
	protected void initComponents() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.title = new JLabel("Action");
		this.availableActionsBox = new ComboBox<>("Select an Action");
		this.actionLabel = new JLabel("Action Constructor");
		this.valuesPanel = new ValuesPanel();
		this.messageLabel = new JLabel("Awaiting Order:");
		this.postButton = new JButton("post");
		this.postButton.setEnabled(false);
		this.closeButton = new JButton("close");
		this.valuesListener = new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent k){
				postButton.setEnabled(valuesPanel.hasAllValues());
			}
		};
		
	}

	@Override
	protected void addComponents() {
		this.add(this.title, "cell 0 0");
		this.add(this.availableActionsBox, "cell 0 1");
		this.add(this.actionLabel, "cell 0 2");
		this.add(this.valuesPanel, "cell 0 3, right, pushx, growx");
		this.add(this.postButton, "cell 0 4, right, pushx, growx");
		this.add(this.messageLabel, "cell 0 5");
		this.add(this.closeButton, "cell 0 6, right, pushx, growx");
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

	protected ComboBox<Class<? extends Action>> getAvailableActionsBox() {
		return this.availableActionsBox;
	}

	protected ValuesPanel getValuesPanel(){
		return this.valuesPanel;
	}
	
	protected JButton getPostButton(){
		return this.postButton;
	}
	
	protected Object[] getValues(){
		return this.valuesPanel.getValues();
	}

	public void setUpValuesPanel(ActionInfo info, Object[] p) {
		this.valuesPanel.setUpComponents(info, p);

		for (TextField field : this.valuesPanel.getTextFields()){
			field.addKeyListener(this.valuesListener);
		}
			
		
	}

	public void setConstructor(Constructor c) {
		this.actionLabel.setText(ActionRegistry.getInstance().parseAnnotation(c).getName());
		this.repaint();
	}
	
	public void setMessage(String string){
		System.out.println("nope");
		this.messageLabel.setText(string);
		this.repaint();
	}
}
