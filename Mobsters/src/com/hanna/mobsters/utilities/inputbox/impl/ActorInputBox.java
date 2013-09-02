/**
 * 
 */
package com.hanna.mobsters.utilities.inputbox.impl;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.ActorBin.ActorBinListener;
import com.hanna.mobsters.ui.shared.ComboBox;
import com.hanna.mobsters.ui.shared.listpanel.ListPanel;
import com.hanna.mobsters.ui.shared.listpanel.impl.ActorListPanel;
import com.hanna.mobsters.utilities.inputbox.CustomInputBox;

/**
 * @author cdhan_000
 *
 */
public class ActorInputBox extends CustomInputBox<Actor>{

	private JLabel actorTitle;

	private ActorListPanel actorList;
	private ActorBinListener actorListListener;
	
	public ActorInputBox(Actor object) {
		super(Actor.class, object);
	}

	@Override
	public void initContent(JPanel panel) {
		this.actorTitle = new JLabel("Actor");
		this.actorList = new ActorListPanel("Actors");
		//this.actorList.setUpComponents("Actors", ActorBin.getInstance().getAllActors());
		
//		this.actorListListener = new ActorBinListener(){
//			@Override
//			public void actorAdded(Actor actor) {
//				actorList.addElement(actor);
//			}
//			@Override
//			public void actorRemoved(Actor actor) {
//				actorList.removeElement(actor);
//			}};
			
		//ActorBin.getInstance().addListener(this.actorListListener);
	}

	@Override
	public void addContent(JPanel panel) {
		//panel.add(actorTitle, "cell 0 0");
		panel.add(this.actorList, "cell 0 0, pushx, growx");
		
	}

	@Override
	public Class<Actor> getType() {
		return Actor.class;
	}

	@Override
	public void setContentForValue(Actor instance) {
		this.actorList.setSelection(instance);
	}

	@Override
	public void setValue() {
		this.instance = this.actorList.getSelection();
	}

	@Override
	public void cleanUp() {
		this.actorList.removeListener();
	}

}
