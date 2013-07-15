/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


import com.hanna.mobsters.actions.Action;
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
	private ObjectList<String> pendingActionsList;
	private JButton wakeUpButton, sleepButton, postButton;
	
	private JLabel actionOutputLabel;
	private ObjectList<String> actionOutputList;
	private JButton clearOutputButton;
	
	private JLabel actorDetailsLabel;
	private ActorDetailsTable actorDetailsTable;
	private ActorDetailsTableModel detailsModel;
	
	@Override
	protected void initComponents() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.title = new JLabel("Actor");
		
		this.pendingActionsLabel = new JLabel("Pending Actions");
		this.pendingActionsList = new ObjectList<>();
		this.pendingActionsList.setPreferredSize(new Dimension(300,400));
		this.wakeUpButton = new JButton("Wake Up");
		this.sleepButton = new JButton("Sleep");
		this.postButton = new JButton("Post");
		
		this.actionOutputLabel = new JLabel("Action Output");
		this.actionOutputList = new ObjectList<>();
		this.actionOutputList.setPreferredSize(new Dimension(300,400));
		this.clearOutputButton = new JButton("Clear Output");
		
		this.actorDetailsLabel = new JLabel("Actor Details");
		//this.detailsModel = new ActorDetailsTableModel();
		//this.actorDetailsTable = new ActorDetailsTable(this.detailsModel);
		
	}

	@Override
	protected void addComponents() {
		this.add(this.title, "cell 0 0");
		
		//Pending actions
		this.add(this.pendingActionsLabel, "cell 0 1");
		this.add(new JScrollPane(this.pendingActionsList), "cell 0 2, growx, spanx, pushx");
		
		JPanel pendingActionButtons = new JPanel();
		pendingActionButtons.add(this.wakeUpButton);
		pendingActionButtons.add(this.sleepButton);
		pendingActionButtons.add(this.postButton);
		
		this.add(pendingActionButtons, "cell 0 3, right");
		
		//action output
		this.add(this.actionOutputLabel, "cell 0 4");
		this.add(new JScrollPane(this.actionOutputList), "cell 0 5,growx, spanx, pushx");
		
		JPanel actionOutputButtons = new JPanel();
		actionOutputButtons.add(this.clearOutputButton);
		
		this.add(actionOutputButtons, "cell 0 6, right");
		
		//actor details
		//this.add(this.actorDetailsLabel, "cell 0 7");
		//this.add(new JScrollPane(this.actorDetailsTable), "cell 0 7");
	}


	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){
			
			
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			this.pendingActionsList.addElement("test");
			
		} else System.out.println("Could not set up actor because parameters did not match expected");
	}

	@Override
	public Class<?>[] getSetUpParameters() {
		return new Class<?>[]{Actor.class};
	}

}
