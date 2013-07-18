/**
 * 
 */
package com.hanna.mobsters.ui;

import java.awt.Dimension;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.hanna.mobsters.ui.core.TopPanel;
import com.hanna.mobsters.ui.core.TopPanelController;

/**
 * @author Chris Hanna
 *
 */
public class Window extends JFrame{

	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Window.getInstance();

	}
	
	private static Window instance;
	public static Window getInstance(){
		if (instance == null)
			instance = new Window();
		return instance;
	}
	
	private TopPanel panel;
	private TopPanelController controller;
	private Window(){
		
		this.setPreferredSize(new Dimension(1000,800)); //this is the default screen res
		this.controller = new TopPanelController();
		this.panel = this.controller.getPanel();
		this.panel.setUpComponents();
		this.add(this.panel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		

		
	}
	
	public TopPanelController getController(){
		return this.controller;
	}
	
	
}
