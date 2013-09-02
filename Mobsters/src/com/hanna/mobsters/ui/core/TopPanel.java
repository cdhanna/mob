package com.hanna.mobsters.ui.core;

import javax.swing.JTabbedPane;

import com.hanna.mobsters.ui.core.tabs.actor.ActorTopPanelController;
import com.hanna.mobsters.ui.core.tabs.history.HistoryTopController;
import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.utilities.console.ConsoleController;

public class TopPanel extends Panel{

	private JTabbedPane tabs;
	
	
	private ActorTopPanelController actorController;
	private HistoryTopController historyController;
	
	@Override
	protected void initComponents() {
		this.tabs = new JTabbedPane();
		
		this.actorController = new ActorTopPanelController();
		this.actorController.getPanel().setUpComponents();
		
		this.historyController = new HistoryTopController();
		
		this.tabs.addTab("Actors", this.actorController.getPanel());
		this.tabs.addTab("History", this.historyController.getPanel());
	}

	@Override
	protected void addComponents() {
		this.add(this.tabs, "cell 0 0, push, grow");
		this.add(ConsoleController.getInstance().getPanel(), "cell 0 1, push, grow, spanx");
	}

	@Override
	public void setUpComponents(Object... parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
