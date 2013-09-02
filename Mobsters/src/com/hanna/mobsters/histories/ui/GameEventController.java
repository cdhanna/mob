/**
 * 
 */
package com.hanna.mobsters.histories.ui;

import com.hanna.mobsters.histories.GameEvent;

/**
 * @author cdhan_000
 *
 */
public class GameEventController {

	private GameEventPanel panel;
	private GameEvent event;
	
	public GameEventController(GameEvent event){
		
		this.event = event;
		this.panel = new GameEventPanel();
		this.panel.setUpComponents(event);
		
	}
	
	public GameEvent getGameEvent(){
		return this.event;
	}
	public GameEventPanel getPanel(){
		return this.panel;
	}
	
}
