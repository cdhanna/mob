package com.hanna.mobsters.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.ui.Splasher;
import com.hanna.mobsters.utilities.ReflectionsHelper;

public class ItemRegistry {

	private static ItemRegistry instance;
	public static ItemRegistry getInstance(){
		if (instance == null)
			instance = new ItemRegistry();
		return instance;
	}
	
	private List<Class<? extends Item<?>>> allItemTypes;
	
	private ItemRegistry(){
		this.allItemTypes = new ArrayList<>();
		this.scanForActions();
	}
	
	private void scanForActions(){
		
		Set<Class<? extends Item>> subTypes = ReflectionsHelper.getInstance().getSubTypes(Item.class, "com.hanna.mobsters.items.impl");
		//System.out.println("Actions Scanned " +subTypes.size());
		for (Class<? extends Item> c : subTypes){
			//System.out.println("\t"+c);
			//System.out.println(c + " : " + c.getModifiers());
			if (c.getModifiers() != 1025) //TODO HORRIBLE HACK. This is saying 'if the class is not abstract'. CHANGE THIS PLEASE. I need the help of the internet to figure this out
				this.register(c);
		}
	}
	
	private void register(Class<? extends Item> itemClass){
		this.allItemTypes.add((Class<? extends Item<?>>) itemClass);
		Splasher.setMsg(itemClass.getSimpleName() + " item");
	}

	public List<Class<? extends Item<?>>> getAllItemTypes() {
		return this.allItemTypes;
	}
}
