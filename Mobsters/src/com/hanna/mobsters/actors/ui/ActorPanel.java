/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.ui.ActionPanel;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.ui.shared.ObjectList;
import com.hanna.mobsters.ui.shared.Panel;

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
	private ActorDetailsTable actorDetailsTable;
	private ActorDetailsTableModel detailsModel;

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
		this.detailsModel = new ActorDetailsTableModel();
		this.actorDetailsTable = new ActorDetailsTable(this.detailsModel);
		this.actorDetailsTable.setPreferredSize(new Dimension(300,200));
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
		JScrollPane tablePane = new JScrollPane(this.actorDetailsTable);
		tablePane.setPreferredSize(new Dimension(320,200));
		this.add(tablePane, "cell 0 8");
	}


	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){

		} else System.err.println("Could not set up actor because parameters did not match expected");
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{Actor.class};
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

	public void setActionPanel(ActionPanel panel) {
		if (panel != null){
			if (this.contains(this.actionPanel))
				this.remove(this.actionPanel);
			this.actionPanel = panel;
			this.add(this.actionPanel, "cell 1 1, spany, top");

			this.revalidate();
			this.repaint();
		}
	}
}
