/**
 * 
 */
package com.hanna.mobsters.items;

import java.util.ArrayList;
import java.util.List;

import com.hanna.mobsters.items.impl.guns.pistol.Pistol;


/**
 * @author chris
 *
 */
public class Inventory {

	private static int idmax;

	private List<Item<?>> items;
	private int id;
	
	public Inventory(){
		this.items = new ArrayList<>();
		idmax++;
		id = idmax;
	}
	
	public boolean isEmpty(){
		return this.items.size() == 0;
	}
	
	public int getSize(){
		return this.items.size();
	}
	
	/**
	 * Put an item into the inventory
	 * @param item
	 */
	public void put(Item<?> item){
		this.items.add(item);

	}
	
	/**
	 * Take an item out of the inventory
	 * @param item the item to take
	 * @return the item that was removed from the inventory, or null if the item was not found in the inventory
	 */
	public <S extends ItemState, I extends Item<S>> I take(I item){
		if (this.items.contains(item)){
			if (this.items.remove(item))
				return item;
			else return null;
		} else return null;
	}
	
	
	public List<Item<?>> getItems(){
		return this.items;
	}
	
	/**
	 * Searches through the inventory and finds the items of a certain type
	 * @param type
	 * @return a list of items that are of the given type
	 */
	public <S extends ItemState, I extends Item<S>> List<I> getItemsOfType(Class<I> type){
		List<I> itemsOfType = new ArrayList<>();
		for (Item<?> item : this.items){
			if (type.isAssignableFrom(item.getClass())){
			//if (item.getClass().isAssignableFrom(type)){
				itemsOfType.add((I) item);
			}
		}
		return itemsOfType;
	}
	
	
	@Override
	public String toString(){
//		String str = "{ ";
//		for (Item<?> item : this.items)
//			str += item.getName() + ", ";
//		str = str.substring(0, str.length()-2) + " }";
//		return str;
		return "inventory";
	}
}
