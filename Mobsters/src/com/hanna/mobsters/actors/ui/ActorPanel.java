/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.ui.ActionPanel;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.Property;
import com.hanna.mobsters.actors.properties.PropertyRegistry;
import com.hanna.mobsters.actors.properties.impl.InventoryProperty;
import com.hanna.mobsters.actors.traits.Trait;
import com.hanna.mobsters.histories.EventKey;
import com.hanna.mobsters.histories.GameEvent;
import com.hanna.mobsters.histories.GameHistory;
import com.hanna.mobsters.histories.GameHistory.GameHistoryEventListener;
import com.hanna.mobsters.histories.ui.HistoryController;
import com.hanna.mobsters.items.ui.InventoryController;
import com.hanna.mobsters.items.ui.InventoryPanel;
import com.hanna.mobsters.ui.shared.ObjectList;
import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.ui.shared.valuepanel.ValuesPanel;
import com.hanna.mobsters.ui.shared.valuepanel.ValuesPanel.Value;
import com.hanna.mobsters.ui.shared.valuepanel.ValuesPanel.ValuePanelContent;

/**
 * @author Chris Hanna
 *
 */
public class ActorPanel extends Panel{

	private JLabel title;

	private JPanel pane_main, pane_action, pane_inventory, pane_history;
	
	private JSplitPane panes;
	private JTabbedPane tabs;
	
	
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
	
	private InventoryController inventoryController;
	private HistoryController historyController;

	@Override
	protected void initComponents() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));

		this.title = new JLabel("Actor");

		
		this.pane_main = new JPanel(new MigLayout("insets 0 0 0 0"));
		this.pane_action = new JPanel(new MigLayout("insets 0 0 0 0"));
		this.pane_inventory = new JPanel(new MigLayout("insets 0 0 0 0"));
		this.pane_history = new JPanel(new MigLayout("insets 0 0 0 0"));
		
		
		this.tabs = new JTabbedPane();
		
		this.panes = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.pane_main, this.tabs);
		
	
		
		
		this.tabs.add("View Inventory", this.pane_inventory);
		this.tabs.add("Give Action", this.pane_action);
		this.tabs.add("View History", this.pane_history);
		
		this.tabs.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent arg0) {
				Component selected = tabs.getSelectedComponent();
				if (selected.equals(pane_inventory)){
					inventoryController.refresh();
				}
			}});
		
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
				traitChange(value);
			}
		};
	}

	@Override
	protected void addComponents() {
		
//		this.add(this.left, "cell 0 1, pushx 13, growx 13");
//		this.add(this.center, "cell 0 1, pushx 33, growx 33");
//		this.add(this.right, "cell 0 1, pushx 33, growx 33");
		this.add(this.panes, "push, grow");
//		this.add(this.left, "cell 0 1, pushx 25, growx 25");
//		this.add(this.center, "cell 1 1, pushx 50, growx 50");
		
		this.pane_main.add(this.title, "cell 0 0");
		//Pending actions
		this.pane_main.add(this.pendingActionsLabel, "cell 0 1");
		this.pane_main.add(new JScrollPane(this.pendingActionsList), "cell 0 2, growx, pushx");

		JPanel pendingActionButtons = new JPanel();
		pendingActionButtons.add(this.wakeUpButton);
		//pendingActionButtons.add(this.sleepButton);
		pendingActionButtons.add(this.postButton);

		this.pane_main.add(pendingActionButtons, "cell 0 3, right");

		//action output
		this.pane_main.add(this.actionOutputLabel, "cell 0 4");
		this.pane_main.add(new JScrollPane(this.actionOutputList), "cell 0 5,growx, pushx");

		JPanel actionOutputButtons = new JPanel();
		actionOutputButtons.add(this.clearOutputButton);

		this.pane_main.add(actionOutputButtons, "cell 0 6, right");

		//actor details
		this.pane_main.add(this.actorDetailsLabel, "cell 0 7");
		this.pane_main.add(this.actorPropertiesPanel, "cell 0 8, pushx, growx");
		
		//actor traits
		this.pane_main.add(this.actorTraitsLabel, "cell 0 9");
		this.pane_main.add(this.actorTraitsPanel, "cell 0 10, pushx, growx");
		
		
		
		
	}


	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){
			final Actor actor = (Actor)parameters[0];
			this.title.setText("Actor: " + actor.getName());
	
			
			//inventory
			this.inventoryController = new InventoryController(actor.getPropertyValue(InventoryProperty.class));
			this.pane_inventory.add(this.inventoryController.getPanel(), "push, grow");
			//this.revalidate();
			
			//history
			this.historyController = new HistoryController(new ArrayList<GameEvent>());
			this.pane_history.add(this.historyController.getPanel(), "push, grow");
			
			GameHistory.getInstance().addGameHistoryEventListener(new GameHistoryEventListener(){
				@Override
				public void addGameEvent(EventKey eventKey, GameEvent event) {
					historyController.getPanel().setUpComponents(actor.tellHistory(100));
				}});
			
			
			this.revalidate();
			
			
			//details
			final Object[] propValues = new Object[PropertyRegistry.getInstance().getKnownProperties().size()];
			for (int i = 0 ; i < propValues.length ; i ++){
				Class prop = PropertyRegistry.getInstance().getKnownProperties().get(i);
				propValues[i] = actor.getPropertyValue(prop);
			}
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
				}
				@Override
				public Object[] getActualObjects() {
					return propValues;
				}});
			
			
	
			this.setActorProperties(propValues);
			
			//traits
			
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
				}

				@Override
				public Object[] getActualObjects() {
					return traitImportance;
				}});
			this.setActorTraitImportanceValues(traitImportance);
			
//			SwingUtilities.invokeLater(new Runnable(){
//
//				@Override
//				public void run() {
//					panes.setDividerLocation(0.50);
//					panes.setDividerLocation(panes.getPreferredSize().width/2);
//				}});
//			
			int total = this.pane_main.getPreferredSize().width + this.pane_inventory.getPreferredSize().width;
			this.panes.setDividerLocation(total / 2);
			this.panes.setDividerSize(12);
			
			
		} else System.err.println("Could not set up actor because parameters did not match expected");
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{Actor.class};
	}

	public void setActorProperties(Object[] propValues){
		this.actorPropertiesPanel.setValues(propValues);
	}
	
	public void setActorTraitImportanceValues(Integer[] traitImportanceValues){
		this.actorTraitsPanel.setValues(traitImportanceValues);
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
	public void refresh(){
		this.inventoryController.refresh();
	}
	public void setActionPanel(ActionPanel panel) {
		
		if (panel != null){
		
			if (this.pane_action.getComponentCount() != 0)
				this.pane_action.remove(this.actionPanel);
//			if (this.contains(this.actionPanel))
//				this.remove(this.actionPanel);
			this.actionPanel = panel;
			
			this.pane_action.add(this.actionPanel, "push, grow");//, "cell 1 1, spany, top");
			this.tabs.setSelectedComponent(this.pane_action);
			this.revalidate();
			this.repaint();
		}
	}
}
