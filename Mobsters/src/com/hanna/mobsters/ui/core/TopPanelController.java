/**
 * 
 */
package com.hanna.mobsters.ui.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.personality.Personality;
import com.hanna.mobsters.actors.ui.ActorController;


/**
 * @author Chris Hanna
 *
 */
public class TopPanelController {

	private TopPanel panel;
	private List<ActorController> allActors;
	private HashMap<Actor, ActorController> allActorsToController;

	public TopPanelController(){
		this.panel = new TopPanel();

		this.allActors = new ArrayList<>();
		this.allActorsToController = new HashMap<>();

		this.panel.getActorBinController().getPanel().getActorTable().addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent arg0) {
				JTable table = panel.getActorBinController().getPanel().getActorTable();
				int selectedRow = table.getSelectedRow();
				panel.getActorTabs().setSelectedIndex(selectedRow);
			}
		});

		this.panel.getActorBinController().getPanel().getRemoveButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Integer selectedRow = panel.getActorBinController().getPanel().getActorTable().getSelectedRow();
				
				//if (selectedRow > -1){
					Actor actorToRemove = (Actor)panel.getActorBinController().getPanel().getActorTable().getModel().getValueAt(selectedRow, 0);
					removeActor(actorToRemove);

					if (allActors.size() != 0){
						if (selectedRow == allActors.size())
							selectedRow --;
						panel.getActorBinController().getPanel().setSelectedActorIndex(selectedRow);
					}
				//}
			}});
		this.panel.getActorBinController().getPanel().getAddButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String name = panel.getActorBinController().getPanel().getNameBox().getText();
				if (name.isEmpty())
					name = "Goon";
				addActor(ActorBin.getInstance().createActor(name, Personality.GANGSTER));
			}});

		this.panel.getActorTabs().addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				Integer selected = panel.getActorTabs().getSelectedIndex();
				//if (selected != null && panel.getActorTabs().getTabCount() > 0 && selected != 0)
				if (allActors.size() > 0)
					allActors.get(selected).refreshInventory();
				panel.getActorBinController().setSelectedActorIndex(selected);
			}});

		this.addActor(ActorBin.getInstance().createActor("Mike", Personality.GANGSTER));
		this.addActor(ActorBin.getInstance().createActor("Chuck", Personality.GANGSTER));
		//		this.addActor(ActorBin.getInstance().createActor("Charles", Personality.GANGSTER));
		//		this.addActor(ActorBin.getInstance().createActor("Franky", Personality.GANGSTER));
		//		this.addActor(ActorBin.getInstance().createActor("Teddy the Tank", Personality.GANGSTER));
		//		this.addActor(ActorBin.getInstance().createActor("Kit", Personality.GANGSTER));
		//		this.addActor(ActorBin.getInstance().createActor("Big Mother Fucker", Personality.GANGSTER));
		//		this.addActor(ActorBin.getInstance().createActor("Will", Personality.GANGSTER));
		//		this.addActor(ActorBin.getInstance().createActor("Chris", Personality.GANGSTER));
		//		this.addActor(ActorBin.getInstance().createActor("Andy", Personality.GANGSTER));
		//		this.addActor(ActorBin.getInstance().createActor("Doug", Personality.GANGSTER));
	}


	public TopPanel getPanel(){
		return this.panel;
	}

	public void addActor(Actor actor){

		ActorController actorController = this.panel.addActor(actor);
		this.allActors.add(actorController);
		this.allActorsToController.put(actor, actorController);
	}

	public void removeActor(Actor actor){
		ActorBin.getInstance().getAllActors().remove(actor);
		this.panel.removeActor(this.allActorsToController.get(actor));
		this.allActors.remove(this.allActorsToController.get(actor));
		this.allActorsToController.remove(actor);
	}

	public void runActorsOnce(){
		for (ActorController ac : this.allActors)
			ac.runAction();
	}

	//	public List<Actor> getActors(){
	//		return this.allActors;
	//	}
}
