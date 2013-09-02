package com.hanna.mobsters.ui.core.tabs.history;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

import com.hanna.mobsters.histories.GameHistory;
import com.hanna.mobsters.histories.ui.HistoryController;
import com.hanna.mobsters.ui.shared.Panel;

public class HistoryTopPanel extends Panel{

	private HistoryController historyController;
	
	@Override
	protected void initComponents() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.historyController = new HistoryController(GameHistory.getInstance().getAllHistory());
	
		
	}

	
	
	@Override
	protected void addComponents() {
		this.add(this.historyController.getPanel(), "push, grow");
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

	public void refresh(){
		historyController.getPanel().setUpComponents(GameHistory.getInstance().getAllHistory());
	}
	
}
