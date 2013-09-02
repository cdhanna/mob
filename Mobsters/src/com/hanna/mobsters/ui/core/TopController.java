/**
 * 
 */
package com.hanna.mobsters.ui.core;

import javax.swing.JTabbedPane;

/**
 * @author cdhan_000
 *
 */
public class TopController {

	private TopPanel panel;

	public TopController(){
		this.panel = new TopPanel();
		this.panel.setUpComponents();
	}
	
	public TopPanel getPanel(){
		return this.panel;
	}
	
}
