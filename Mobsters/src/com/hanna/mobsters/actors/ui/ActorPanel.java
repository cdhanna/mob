/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.ui.ActionPanel;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.Property;
import com.hanna.mobsters.actors.properties.PropertyRegistry;
import com.hanna.mobsters.actors.traits.Trait;
import com.hanna.mobsters.ui.shared.ObjectList;
import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.ui.shared.ValuesPanel;
import com.hanna.mobsters.ui.shared.ValuesPanel.Value;
import com.hanna.mobsters.ui.shared.ValuesPanel.ValuePanelContent;

/**
 * @author Chris Hanna
 *
 */
public class ActorPanel extends Panel{

	private JLabel title;

	private JLabel pendingActionsLabel;
	private ObjectList<Action> pendingActionsList;
	private JButton wakeUpButton, sleepButton, postButton;

	private JLabel actionOutputLabel;
	private ObjectList<String> actionOutputList;
	private JButton clearOutputButton;

	private JLabel actorDetailsLabel;
	private ValuesPanel actorPropertiesPanel;

	private JLabel actorTraitsLabel;
	private ValuesPanel actorTraitsPanel;
	
	private ActionPanel actionPanel;

	@Override
	protected void initComponents() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		this.title = new JLabel("Actor");

		this.pendingActionsLabel = new JLabel("Pending Actions");
		this.pendingActionsList = new ObjectList<>();
		this.pendingActionsList.setPreferredSize(new Dimension(300,400));
		this.wakeUpButton = new JButton("Run Action");
		this.sleepButton = new JButton("Sleep");
		this.postButton = new JButton("Post");

		this.actionOutputLabel = new JLabel("Action Output");
		this.actionOutputList = new ObjectList<>();
		this.actionOutputList.setPreferredSize(new Dimension(300,400));
		this.clearOutputButton = new JButton("Clear Output");

		this.actorDetailsLabel = new JLabel("Actor Details");
		this.actorPropertiesPanel = new ValuesPanel(){
			@Override
			protected void valueChangedAction(Value value){
				propertyChange(value);
			}
		};
		
		this.actorTraitsLabel = new JLabel("Actor Traits");
		this.actorTraitsPanel = new ValuesPanel(){
			@Override
			protected void valueChangedAction(Value value){
				System.out.println(value);
			}
		};
	}

	@Override
	protected void addComponents() {
		this.add(this.title, "cell 0 0");

		//Pending actions
		this.add(this.pendingActionsLabel, "cell 0 1");
		this.add(new JScrollPane(this.pendingActionsList), "cell 0 2, growx, pushx");

		JPanel pendingActionButtons = new JPanel();
		pendingActionButtons.add(this.wakeUpButton);
		//pendingActionButtons.add(this.sleepButton);
		pendingActionButtons.add(this.postButton);

		this.add(pendingActionButtons, "cell 0 3, right");

		//action output
		this.add(this.actionOutputLabel, "cell 0 4");
		this.add(new JScrollPane(this.actionOutputList), "cell 0 5,growx, pushx");

		JPanel actionOutputButtons = new JPanel();
		actionOutputButtons.add(this.clearOutputButton);

		this.add(actionOutputButtons, "cell 0 6, right");

		//actor details
		this.add(this.actorDetailsLabel, "cell 0 7");
		this.add(this.actorPropertiesPanel, "cell 0 8, pushx, growx");
		
		//actor traits
		this.add(this.actorTraitsLabel, "cell 0 9");
		this.add(this.actorTraitsPanel, "cell 0 10, pushx, growx");
	}


	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){
			Actor actor = (Actor)parameters[0];
			this.title.setText("Actor: " + actor.getName());
	
			//details
			this.actorPropertiesPanel.setUpComponents(new ValuePanelContent(){
				@Override
				public Class<?>[] getTypes() {
					return PropertyRegistry.getInstance().getKnownPropertyDataTypes().toArray(new Class<?>[0]);
				}
				@Override
				public String[] getTypeDescriptions() {
					return PropertyRegistry.getInstance().getKnownPropertyNames().toArray(new String[0]);
				}
				@Override
				public Object[] getItemIDs() {
					return PropertyRegistry.getInstance().getKnownProperties().toArray();
				}});
			
			
			Object[] propValues = new Object[PropertyRegistry.getInstance().getKnownProperties().size()];
			for (int i = 0 ; i < propValues.length ; i ++){
				Class prop = PropertyRegistry.getInstance().getKnownProperties().get(i);
				propValues[i] = actor.getPropertyValue(prop);
			}
			this.setActorProperties(propValues);
			
			//traits
			
			final Trait[] traits = new Trait[actor.getPersonality().size()];
			final Class<?>[] traitTypes = new Class<?>[traits.length];
			final String[] traitNames = new String[traits.length];
			for (int i = 0 ; i < traits.length ; i ++){
				traits[i] = actor.getPersonality().get(i);
				traitTypes[i] = actor.getPersonality().get(i).getClass();
				traitNames[i] = actor.getPersonality().get(i).getClass().getSimpleName();
			}
			
			this.actorTraitsPanel.setUpComponents(new ValuePanelContent(){
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
				}});
			this.setActorTraits(traits);
			
			
		} else System.err.println("Could not set up actor because parameters did not match expected");
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{Actor.class};
	}

	public void setActorProperties(Object[] propValues){
		this.actorPropertiesPanel.setValues(propValues);
	}
	
	public void setActorTraits(Trait[] traits){
		this.actorTraitsPanel.setValues(traits);
	}

	protected ObjectList<Action> getPendingActionList(){
		return this.pendingActionsList;
	}
	
	protected ObjectList<String> getOutputList(){
		return this.actionOutputList;
	}

	protected JButton getPostButton(){
		return this.postButton;
	}
	protected JButton getWakeUpButton(){
		return this.wakeUpButton;
	}
	protected JButton getClearOutputButton(){
		return this.clearOutputButton;
	}

	public ActionPanel getActionPanel(){
		return this.actionPanel;
	}
	
	protected void propertyChange(Value value){
		
	}
	protected void traitChange(Value value){
		
	}
	
	public void setActionPanel(ActionPanel panel) {
		if (panel != null){
			if (this.contains(this.actionPanel))
				this.remove(this.actionPanel);
			this.actionPanel = panel;
			//this.add(this.actionPanel, "cell 1 1, spany, top");
			//this.revalidate();
			//this.repaint();
		}
	}
}
