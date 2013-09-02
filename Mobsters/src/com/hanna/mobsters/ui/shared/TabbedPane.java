package com.hanna.mobsters.ui.shared;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.hanna.mobsters.utilities.MediaLoader;

import net.miginfocom.swing.MigLayout;

public class TabbedPane extends JTabbedPane{

	private static final ImageIcon tabCloseIcon = new ImageIcon(MediaLoader.load("media/x.png"));

	
	
	public void addCloseableTab(String title, Component tabContent){
		
		super.addTab(title, tabContent);
//		final int index = this.getTabCount() - 1;
//		
//		JPanel tab = new JPanel(new MigLayout("insets 0 0 0 0"));
//		tab.setOpaque(false);
//		tab.setBackground(new Color(0,0,0,0));
//		
//		JLabel tabTitle = new JLabel(title);
//		JLabel tabClose = new JLabel(tabCloseIcon);
//		
//		tabClose.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mousePressed(MouseEvent m){
//				removeTabAt(index);
//			}
//		});
//		
//		
//		tab.add(tabTitle, "cell 0 0");
//		tab.add(tabClose, "cell 0 0");
//		this.setTabComponentAt(index, tab);
		
	}
	

//	@Override
//	public void removeTabAt(int index){
//		if (this.getTabCount() > 0)
//			super.removeTabAt(index);
//	}
	
}
