package com.hanna.mobsters.items.ui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;

import com.hanna.mobsters.items.Item;

public class ItemDetailsController {

	private ItemDetailsPanel panel;
	private Item<?> item;

	public ItemDetailsController(){
		this.panel = new ItemDetailsPanel(){
			@Override
			public void stateFieldChanged(Field field, Object value){
				setStateField(field, value);
			}
			@Override
			public void itemFieldChanged(Field field, Object value){
				setItemField(field, value);
			}
		};

		this.panel.getNameBox().addKeyListener(new KeyAdapter(){
			@Override
			public void keyReleased(KeyEvent k){
				updateName(panel.getNameBox().getText());
			}
		});

	}

	public ItemDetailsPanel getPanel(){
		return this.panel;
	}

	public void setForItem(Item<?> item) {
		if (item != null){
			if (!this.panel.isVisible())
				this.panel.setVisible(true);
			this.item = item;
			this.panel.setForItem(item);
		} else {
			if (this.panel.isVisible())
				this.panel.setVisible(false);
		}
	}
	private void setStateField(Field field, Object value){
		try {
			field.set(this.item.getState(), value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void setItemField(Field field, Object value){
		try {
			field.set(this.item,  value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateName(String name){
		this.item.setName(name);
		this.panel.getTypeNameLabel().setText(this.item.getClass().getSimpleName() + " " + this.item.getName());

		itemUpdated(this.item);
	}

	public void itemUpdated(Item<?> item){

	}
	public Item<?> getItem(){
		return this.item;
	}

}
