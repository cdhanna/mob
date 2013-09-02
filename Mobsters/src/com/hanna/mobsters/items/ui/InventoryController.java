package com.hanna.mobsters.items.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.impl.InventoryProperty;
import com.hanna.mobsters.items.Inventory;
import com.hanna.mobsters.items.Item;
import com.hanna.mobsters.items.impl.guns.pistol.Pistol;

public class InventoryController {

	private Inventory inventory;
	private InventoryPanel panel;

	public InventoryController(final Inventory inventory){
		this.inventory = inventory;
		this.panel = new InventoryPanel();
		this.panel.setUpComponents(inventory);

		this.panel.getGiveToButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				giveTo(panel.getSelectedItem(), panel.getGiveToBox().getSelection());
			}});


		this.panel.getRemoveButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Item<?> item = panel.getSelectedItem();
				removeItem(item);
			}});
		
		this.panel.getAddItemButton().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Item<?> selected = create(panel.getAddItemTypeBox().getSelection(), panel.getAddItemNameBox().getText());
				
				panel.refresh(inventory);
				panel.setSelectedItem(selected);
			}});
		
	}

	public Inventory getInventory(){
		return this.inventory;
	}
	public InventoryPanel getPanel(){

		return this.panel;
	}


	public void giveTo(Item<?> item, Actor whom){
		if (item != null && whom != null){
			this.removeItem(item);
			whom.getPropertyValue(InventoryProperty.class).put(item);
		}
		this.refresh();
	}
	public void removeItem(Item<?> item){
		if (item != null){
			this.inventory.take(item);
			this.panel.removeItem(item);
		}
	}
	public Item<?> create(Class<? extends Item<?>> itemClass, String name){
		try {
			Item<?> obj = (Item<?>) itemClass.getConstructors()[0].newInstance();
			obj.setName(name);
			inventory.put(obj);
			return obj;
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void refresh() {
		this.panel.refresh(this.inventory);
	}

}
