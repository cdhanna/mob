/**
 * 
 */
package com.hanna.mobsters.ui.core;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.hanna.mobsters.ui.Top;

/**
 * @author Chris Hanna
 *
 */
public class ToolBarController {

	private ToolBarPanel panel;
	
	public ToolBarController(){
		this.panel = new ToolBarPanel();
		
		this.panel.getRunOnceButton().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Top.controller.runActorsOnce();
			}});
		
	}
	
	public Component getPanel() {
		return this.panel;
	}

	public void setFocusPanel(JPanel focusPanel, String signerature){
		this.panel.setFocusPanel(focusPanel, signerature);
	}
	
	public void clearFocusPanel(){
		this.panel.clearFocusPanel();
	}
	
}
