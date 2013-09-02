package com.hanna.mobsters.histories.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

import com.hanna.mobsters.histories.GameEvent;
import com.hanna.mobsters.ui.shared.Panel;

public class HistoryPanel extends Panel{

	private List<GameEventController> eventControllers;
	private JPanel eventsPanel;
	
	
	@Override
	protected void initComponents() {
		this.eventControllers = new ArrayList<>();

		this.eventsPanel = new JPanel(new MigLayout());
		this.eventsPanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
	}

	@Override
	protected void addComponents() {
		this.add( new JScrollPane(this.eventsPanel), "cell 0 1, push, grow");
		
	}

	@Override
	public void setUpComponents(Object... parameters) {
		//EXPECTS A LIST OF GAME EVENTS
		if (this.doesInputMatchExpected(parameters)){
			List<GameEvent> gameEvents = (List<GameEvent>) parameters[0];
			
			this.removeAllEventControllers();
			for (GameEvent event : gameEvents){
				GameEventController controller = new GameEventController(event);
				this.addEventController(controller);
			}
			this.repaint();
		}
		
	}
	
	private void addEventController(GameEventController event){
		this.eventsPanel.add(event.getPanel(), "wrap");
		this.eventControllers.add(event);
	}
	
	public void removeEventController(GameEventController event){
		if (this.eventControllers.remove(event))
			this.eventsPanel.remove(event.getPanel());
	}
	
	private void removeAllEventControllers(){
		while (this.eventControllers.size() > 0)
			this.removeEventController(this.eventControllers.get(0));
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{ArrayList.class};
	}
	
	

}
