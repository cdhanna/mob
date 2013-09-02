/**
 * 
 */
package com.hanna.mobsters.ui.core.tabs.history;

import com.hanna.mobsters.histories.EventKey;
import com.hanna.mobsters.histories.GameEvent;
import com.hanna.mobsters.histories.GameHistory;
import com.hanna.mobsters.histories.GameHistory.GameHistoryEventListener;

/**
 * @author cdhan_000
 *
 */
public class HistoryTopController {

	private HistoryTopPanel panel;
	
	public HistoryTopController(){
		this.panel = new HistoryTopPanel();
		this.panel.setUpComponents();
		
		GameHistory.getInstance().addGameHistoryEventListener(new GameHistoryEventListener(){
			@Override
			public void addGameEvent(EventKey eventKey, GameEvent event) {
				panel.refresh();
			}});
	}
	
	public HistoryTopPanel getPanel(){
		return this.panel;
	}
	
}
