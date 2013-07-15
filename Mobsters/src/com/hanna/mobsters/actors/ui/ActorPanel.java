/**
 * 
 */
package com.hanna.mobsters.actors.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.ui.shared.Panel;

/**
 * @author Chris Hanna
 *
 */
public class ActorPanel extends Panel{

	private JLabel title;
	
	@Override
	protected void initComponents() {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.title = new JLabel("Actor");
	}

	@Override
	protected void addComponents() {
		this.add(this.title);
		
	}


	public void setUpComponents(Object... parameters) {
		if (this.doesInputMatchExpected(parameters)){
			this.title.setText("test");
		} else System.out.println("Could not set up actor because parameters did not match expected");
	}

	@Override
	public Class<?>[] getSetUpParameters() {
		return new Class<?>[]{Actor.class};
	}

}
