/**
 * 
 */
package com.hanna.mobsters.ui.core.tabs.actor;


import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import net.miginfocom.swing.MigLayout;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.ActorBin.ActorBinListener;
import com.hanna.mobsters.actors.properties.Location;
import com.hanna.mobsters.actors.ui.ActorController;
import com.hanna.mobsters.actors.ui.ActorPanel;
import com.hanna.mobsters.actors.ui.bin.ActorBinController;
import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.ui.shared.TabbedPane;
import com.hanna.mobsters.ui.shared.listpanel.ListPanel;
import com.hanna.mobsters.utilities.MediaLoader;
import com.hanna.mobsters.utilities.console.ConsoleController;
import com.hanna.mobsters.utilities.inputbox.DefaultInputBox;
import com.hanna.mobsters.utilities.inputbox.impl.LocationInputBox;

/**
 * @author Chris Hanna
 *
 */
public class ActorTopPanel extends Panel{

	//private ActorController controller;

	//private ToolBarController toolBar;
	private ActorBinController actorBinController;
	private TabbedPane actorTabs;

	
	
	@Override
	protected void initComponents() {
		//this.toolBar = new ToolBarController();
		this.actorBinController = ActorBinController.getInstance();
		this.actorTabs = new TabbedPane();
		
	

		
		
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@Override
	protected void addComponents() {
		this.add(this.actorBinController.getPanel(), "cell 0 0, top, pushy, growy, pushx 20, growx 20");
		this.add(this.actorTabs, "cell 0 0, top, pushy, growy, pushx 80, growx 80");
		
		//this.add(this.toolBar.getPanel(), "cell 0 1, pushx,growx,  pushy, growy");
	}

	@Override
	public void setUpComponents(Object... parameters) {
		
		
		
		if (this.doesInputMatchExpected(parameters)){

			
		}
	}


	@Override
	public Class<?>[] getSetUpParameterTypes() {
		return new Class<?>[]{};
	}

//	public ToolBarController getToolBarController(){
//		return this.toolBar;
//	}

	public ActorController addActor(Actor actor){
		//ActorTabController tabController = new ActorTabController(actor);
		
		ActorController actorController = new ActorController(actor); 
		ActorPanel actorPanel = actorController.getPanel();
		//this.add(actorPanel);
		
		String tabName = actor.getName();
		
		//this.actorTabs.addTab(tabName, actorPanel);
		this.actorTabs.addCloseableTab(tabName, actorPanel);
		
//		JPanel tabComponent = new JPanel(new MigLayout("insets 0 0 0 0"));
//		JLabel tabTitle = new JLabel(tabName);
//		JLabel tabClose = new JLabel(new ImageIcon(MediaLoader.load("media/x.png")));
//		final int index = this.actorTabs.getTabCount() -1;
//		MouseAdapter a;
//		tabClose.addMouseListener(new MouseAdapter(){
//			@Override
//			public void mousePressed(MouseEvent m){
//				actorTabs.removeTabAt(index);
//			}
//		});
//		tabComponent.setOpaque(false);
//		tabComponent.setBackground(new Color(255,0,0,0));
//		tabComponent.add(tabTitle, "cell 0 0");
//		tabComponent.add(tabClose, "cell 0 0");
//		
//		this.actorTabs.setTabComponentAt(index, tabComponent);
		
		
		//ActorController actorController = tabController.getActorController();
		//this.actorTabs.add(actor.getName(), actorController.getPanel());
		return actorController;
	}
	
	public void removeActor(ActorController actorController){
		this.actorTabs.remove(actorController.getPanel());
		this.actorBinController.removeActor(actorController.getActor());
	}
	
	public ActorBinController getActorBinController(){
		return this.actorBinController;
	}
	public JTabbedPane getActorTabs(){
		return this.actorTabs;
	}
}
