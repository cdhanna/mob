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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionRegistry;
import com.hanna.mobsters.actions.core.ActionRegistry.ActionInfo;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.Decision;
import com.hanna.mobsters.actors.traits.Trait;
import com.hanna.mobsters.ui.shared.ComboBox;
import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.ui.shared.TextField;
import com.hanna.mobsters.ui.shared.valuepanel.ValuesPanel;
import com.hanna.mobsters.ui.shared.valuepanel.ValuesPanel.Value;
import com.hanna.mobsters.ui.shared.valuepanel.ValuesPanel.ValuePanelContent;
import com.hanna.mobsters.utilities.inputbox.InputBox;
import com.hanna.mobsters.utilities.inputbox.InputBox.ValueChangedListener;

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
	
	private ValuesPanel decisionWeightsPanel;
	
	@Override
	protected void initComponents() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.title = new JLabel("Action");
		this.availableActionsBox = new ComboBox<Class<? extends Action>>("Select an Action"){
			@Override
			public String elementToString(Class<? extends Action> e){
				return e.getSimpleName();
			}
		};
		this.actionLabel = new JLabel("Action Constructor");
		this.valuesPanel = new ValuesPanel();
		this.messageLabel = new JLabel("Awaiting Order:");
		this.postButton = new JButton("post");
		this.postButton.setEnabled(false);
		this.closeButton = new JButton("close");
//		this.valuesListener = new KeyAdapter(){
//			@Override
//			public void keyReleased(KeyEvent k){
//				postButton.setEnabled(valuesPanel.hasAllValues());
//			}
//		};
		
		
		this.decisionWeightsPanel = new ValuesPanel();
		this.decisionWeightsPanel.setEnabled(false);
		
	}

	@Override
	protected void addComponents() {
		this.add(this.title, "cell 0 0");
		this.add(this.availableActionsBox, "cell 0 1");
		this.add(this.actionLabel, "cell 0 2");
		this.add(this.valuesPanel, "cell 0 3, right, pushx, growx");
		this.add(this.postButton, "cell 0 4, right, pushx, growx");
		this.add(this.messageLabel, "cell 0 5");
		this.add(this.decisionWeightsPanel, "cell 0 6, pushx, growx");
		this.add(this.closeButton, "cell 0 7, right, pushx, growx");
		
		
	}

	@Override
	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){
			Action action = (Action)parameters[0];
			ActionRegistry registry = (ActionRegistry)parameters[1];
			Actor actor = (Actor)parameters[2];
			this.availableActionsBox.setElements(registry.getRegisteredClasses());
			
			
			final Trait[] traits = new Trait[actor.getPersonality().size()];
			final Class<?>[] traitTypes = new Class<?>[traits.length];
			final String[] traitNames = new String[traits.length];
			final Integer[] traitImportance = new Integer[traits.length];
			for (int i = 0 ; i < traits.length ; i ++){
				traits[i] = actor.getPersonality().get(i);
				traitTypes[i] = Integer.class;
				traitNames[i] = actor.getPersonality().get(i).getClass().getSimpleName();
				traitImportance[i] = actor.getPersonality().get(i).getImportance();
			}
			
			this.decisionWeightsPanel.setUpComponents(new ValuePanelContent(){
				@Override
				public Object[] getItemIDs() {
					return traits;
				}

				@Override
				public Class<?>[] getTypes() {
					return traitTypes;
				}

				@Override
				public String[] getTypeDescriptions() {
					return traitNames;
				}

				@Override
				public Object[] getActualObjects() {
					return traitImportance;
				}});
			
			
		} else System.err.println("Could not set up Action. invalid params");
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{Action.class, ActionRegistry.class, Actor.class};
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
	
	protected JButton getCloseButton() {
		return this.closeButton;
	}
	
	protected Object[] getValues(){
		return this.valuesPanel.getValues();
	}

	public void setUpValuesPanel(final ActionInfo info) {
		
		final Object[] values = new Object[info.getParameters().length];
		
		ValuePanelContent c = new ValuePanelContent(){
			@Override
			public Class<?>[] getTypes() {
				return info.getParameters();
			}
			@Override
			public String[] getTypeDescriptions() {
				return info.getParameterDescriptions();
			}
			@Override
			public Object[] getItemIDs() {
				return null;
			}
			@Override
			public Object[] getActualObjects() {
				return values;
			}};
		this.valuesPanel.setUpComponents(c);

//		for (TextField field : this.valuesPanel.getTextFields()){
//			field.addKeyListener(this.valuesListener);
//		}
		for (InputBox field : this.valuesPanel.getTextFields()){
			field.addValueChangedListener(new ValueChangedListener(){
				@Override
				public void valueChanged(Object instance) {
					postButton.setEnabled(valuesPanel.hasAllValues());
				}});
		}
		
	}

	protected void setConstructor(Constructor c) {
		this.actionLabel.setText(ActionRegistry.getInstance().getActionInfo(c).getName());
		this.repaint();
	}
	
	protected void setMessage(String string){
		this.messageLabel.setText(string);
		this.repaint();
	}
	protected void setDecisionWeights(Decision decision){
		List<String> decisionNames = new ArrayList<>();
		List<Double> decisionMags = new ArrayList<>();
		
		Object[] params = new Object[this.decisionWeightsPanel.getTextFields().size()];
		int paramIndex = 0;
		for (Object param : params)
			param = "nil";
		
		
		boolean keepGoing = true;
		
	    while (keepGoing){
	    	Double mag = decision.getTerm();
	    	Class<?> name = decision.getTermName();
	    	if (mag != null && name != null){
	    		List<Value> vals = this.decisionWeightsPanel.getVals();
	    		for (Value val : vals){
	    			if (val.getID().getClass() == name){
	    				this.decisionWeightsPanel.setValue(val.getID(), mag);
	    			}
	    		}
	    		//find the trait in the table
	    	} else {
	    		keepGoing = false;
	    	}
	    }
		
	}


}
