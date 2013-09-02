package com.hanna.mobsters.histories.ui;

import java.util.List;

import com.hanna.mobsters.histories.GameEvent;

public class HistoryController {

	private HistoryPanel panel;
	private List<GameEvent> gameEvents;
	
	
	public HistoryController(List<GameEvent> gameEvents){
		this.gameEvents = gameEvents;
		this.panel = new HistoryPanel();
		this.panel.setUpComponents(gameEvents);
		
		
		
	}
	
	public HistoryPanel getPanel(){
		return this.panel;
	}
	
	public List<GameEvent> getGameEvents(){
		return this.gameEvents;
	}
}
