/**
 * 
 */
package com.hanna.mobsters.ui;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * @author Chris Hanna
 *
 */
public class Window extends JFrame{

	public static void main(String[] args){
		new Window();
	}
	
	private TopPanel panel;
	
	public Window(){
		
		this.setPreferredSize(new Dimension(800,800)); //this is the default screen res
		
		this.panel = new TopPanel();
		this.panel.setUpComponents();
		this.add(this.panel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	
}
