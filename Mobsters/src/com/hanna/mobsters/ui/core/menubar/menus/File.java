package com.hanna.mobsters.ui.core.menubar.menus;

import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.esotericsoftware.kryo.util.IdentityMap.Keys;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.ui.core.AboutWindow;
import com.hanna.mobsters.utilities.KryoHelper;

public class File extends Menu{

	private static Menu instance;
	public static Menu getInstance() {
		if (instance == null)
			instance = new File();
		return instance;
	}

	
	MenuItem exitItem;
	MenuItem aboutItem;
	MenuItem saveItem;
	MenuItem loadItem;
	
	public File(){
		super("File");
		setSave();
		setLoad();
		setAbout();
		setExit();
		
	}
	
	private void setLoad(){
		this.loadItem = new MenuItem("Load");
		this.add(loadItem);
	}
	
	private void setSave(){
		this.saveItem = new MenuItem("Save");
		this.add(saveItem);
		
		this.saveItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
//				String f = "file.bin";
//				KryoHelper.getInstance().saveSimple(f, ActorBin.getInstance().getAllActors().get(0));
//				Actor a = KryoHelper.getInstance().loadSimple(f, Actor.class);
			}});
		
	}
	private void setAbout(){
		this.aboutItem = new MenuItem("About");
		this.add(aboutItem);
		
		this.aboutItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new AboutWindow();
			}});
	}
	private void setExit(){
		this.exitItem = new MenuItem("Exit");
		this.add(exitItem);
		
		this.exitItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}});
		
	}
	
}
