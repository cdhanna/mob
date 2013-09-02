/**
 * 
 */
package com.hanna.mobsters.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.hanna.mobsters.ui.core.TopController;
import com.hanna.mobsters.ui.core.menubar.Bar;
import com.hanna.mobsters.ui.core.tabs.actor.ActorTopPanel;
import com.hanna.mobsters.ui.core.tabs.actor.ActorTopPanelController;
import com.hanna.mobsters.utilities.MediaLoader;
import com.hanna.mobsters.utilities.console.CustomPrintStream;
import com.hanna.mobsters.utilities.parsers.ParseRegistry;

/**
 * @author Chris Hanna
 *
 */
public class Window extends JFrame{

	public static void launch(String[] args){
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
	
	//private ActorTopPanel panel;
	private TopController controller;
	
	
	
	private Window(){
		
		super();
		//this.setUndecorated(true);
		

		
		this.setTitle("Saint");
		this.setName("Saint");
		
		
		
		CustomPrintStream.initialize();
		System.out.println("Creating ai sim window...");
		
		ParseRegistry.getInstance();
		
		Dimension size = new Dimension(1440,900);
		this.setPreferredSize(size); //this is the default screen res
		this.setExtendedState(MAXIMIZED_BOTH);
		
		this.controller = new TopController();
		//this.controller = new ActorTopPanelController();
//		this.panel = this.controller.getPanel();
//		this.panel.setUpComponents();
		
		this.setMenuBar(Bar.getInstance());
		
		//this.add(this.panel);
		this.add(this.controller.getPanel());
		
		ImageIcon ico = new ImageIcon(MediaLoader.load("media/icon.png"));
		this.setIconImage(ico.getImage());
		//this.pack();
		
		Dimension screenSize = this.getContentPane().getToolkit().getScreenSize();
		
		this.setLocation(screenSize.width/2 - (int)(size.getWidth()/2), screenSize.height/2 - (int)(size.getHeight()/2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
		
		
		System.out.println("ai sim window created.");
		
	}
	
//	public ActorTopPanelController getController(){
//		return this.controller;
//	}
	
	
}
