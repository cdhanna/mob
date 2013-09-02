package com.hanna.mobsters.items.ui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import com.hanna.mobsters.items.Item;
import com.hanna.mobsters.items.ItemState;
import com.hanna.mobsters.ui.shared.Panel;
import com.hanna.mobsters.ui.shared.TextField;
import com.hanna.mobsters.ui.shared.valuepanel.ClassValuesPanel;
import com.hanna.mobsters.ui.shared.valuepanel.ValuesPanel;
import com.hanna.mobsters.ui.shared.valuepanel.ValuesPanel.ValuePanelContent;

public class ItemDetailsPanel extends Panel{

	private JLabel itemTypeName;
	private TextField itemNameBox;
	
	private JLabel stateTitle;
	private ClassValuesPanel stateValues;
	
	private JLabel itemTitle;
	private ClassValuesPanel itemValues;
	
	
	@Override
	protected void initComponents() {
		this.itemTypeName = new JLabel("TYPE NAME");
		this.itemNameBox = new TextField("Name");
		
		this.stateTitle = new JLabel("STATE TYPE");
		this.stateValues = new ClassValuesPanel(){
			@Override
			public void valueChangedAction(Value value){
				stateFieldChanged((Field)value.getID(), value.getValue());
			}
			
		};
		
		this.itemTitle = new JLabel("ITEM TYPE");
		this.itemValues = new ClassValuesPanel(){
			@Override
			public void valueChangedAction(Value value){
				itemFieldChanged((Field)value.getID(), value.getValue());
			}
		};
		
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@Override
	protected void addComponents() {
		this.add(this.itemTypeName, "cell 0 0");
		this.add(this.itemNameBox, "cell 0 1, pushx, growx, spanx");

		this.add(this.itemTitle, "cell 0 2");
		this.add(this.itemValues, "cell 0 3, push, grow, top");
		this.add(this.stateTitle, "cell 1 2");
		this.add(this.stateValues, "cell 1 3, push, grow, top");
	}

	@Override
	public void setUpComponents(Object... parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Class<?>[] getSetUpParameterTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	public void stateFieldChanged(Field field, Object value){
		
	}
	public void itemFieldChanged(Field field, Object value){
		
	}
	
	public void setForItem(Item<?> item){
		this.itemTypeName.setText(this.getTypeName(item));
		this.itemNameBox.setText(item.getName());
		
		this.stateTitle.setText(item.getState().getClass().getSimpleName() + " variables");
		this.stateValues.setUpComponents(item.getState());

		this.itemTitle.setText(item.getClass().getSimpleName() + " variables");
		this.itemValues.setUpComponents(item);
	}
	
	private String getTypeName(Item<?> item){
		String typeName = item.getClass().getSimpleName() + " " + item.getName();
		return typeName;
	}
	
	public TextField getNameBox(){
		return this.itemNameBox;
	}
	public JLabel getTypeNameLabel(){
		return this.itemTypeName;
	}
	
}
