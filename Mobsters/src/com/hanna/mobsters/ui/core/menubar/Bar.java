package com.hanna.mobsters.ui.core.menubar;

import java.awt.MenuBar;

import javax.swing.JMenuBar;

import com.hanna.mobsters.ui.core.menubar.menus.File;

public class Bar extends MenuBar{

	private static Bar instance;
	public static Bar getInstance(){
		if (instance == null)
			instance = new Bar();
		return instance;
	}
	
	private Bar(){
		
		this.add(File.getInstance());
		
	}
	
}
