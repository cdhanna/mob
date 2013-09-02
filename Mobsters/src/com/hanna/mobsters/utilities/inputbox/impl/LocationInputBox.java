package com.hanna.mobsters.utilities.inputbox.impl;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.properties.Location;
import com.hanna.mobsters.actors.properties.impl.LocationProperty;
import com.hanna.mobsters.ui.shared.TextField;
import com.hanna.mobsters.ui.shared.listpanel.ListPanel;
import com.hanna.mobsters.ui.shared.listpanel.impl.ActorListPanel;
import com.hanna.mobsters.utilities.inputbox.CustomInputBox;
import com.hanna.mobsters.utilities.parsers.ParseRegistry;

public class LocationInputBox extends CustomInputBox<Location>{

	private TextField streetName;
	private JLabel streetNameLabel;

	private JLabel numberLabel;
	private TextField number;

	private ActorListPanel actorList;



	public LocationInputBox(Location location) {
		super(Location.class, location);

	}

	@Override
	public void initContent(JPanel panel) {
		this.streetNameLabel = new JLabel("Street Name");
		this.streetName = new TextField("Street Name");

		this.numberLabel = new JLabel("#");
		this.number = new TextField("Street Number");

		this.actorList = new ActorListPanel("Actor Location");

		this.actorList.getList().addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (actorList.getSelection() != null){
					setContentForValue(actorList.getSelection().getPropertyValue(LocationProperty.class));
					//setValue();
				}
			}});
	}

	@Override
	public void addContent(JPanel panel) {
		panel.add(this.numberLabel, "cell 0 0");
		panel.add(this.number, "cell 0 1, pushx, growx");
		panel.add(this.streetNameLabel, "cell 1 0");
		panel.add(this.streetName, "cell 1 1, pushx, growx");
		panel.add(this.actorList, "cell 0 2, pushx, growx, spanx");

	}

	@Override
	public void setContentForValue(Location instance) {
		this.instance = instance;
		this.number.setText(""+this.instance.getStreetNumber());
		this.streetName.setText(this.instance.getStreet());

	}

	@Override
	public void setValue() {
		this.instance.setStreet(this.streetName.getText());
		this.instance.setStreetNumber(ParseRegistry.getInstance().parse(this.number.getText(), Integer.class));
		this.fireValueChanged();
	}

	@Override
	public Class<Location> getType() {
		return Location.class;
	}

	@Override
	public void cleanUp() {
		this.actorList.removeListener();
	}




}
