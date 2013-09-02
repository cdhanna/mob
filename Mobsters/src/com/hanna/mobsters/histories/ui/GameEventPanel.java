package com.hanna.mobsters.histories.ui;

import javax.swing.JLabel;

import com.hanna.mobsters.histories.GameEvent;
import com.hanna.mobsters.ui.shared.Panel;

public class GameEventPanel extends Panel{

	private JLabel title;
	private JLabel content;
	
	@Override
	protected void initComponents() {
		this.title = new JLabel("GAME EVENT");
		this.content = new JLabel("Bla bla bla");
	}

	@Override
	protected void addComponents() {
		this.add(this.title);
		this.add(this.content);
	}

	@Override
	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){
			GameEvent event = (GameEvent)parameters[0];
			
			this.content.setText(event.toString());
			System.out.println(event.toString());
		}
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{GameEvent.class};
	}

}
