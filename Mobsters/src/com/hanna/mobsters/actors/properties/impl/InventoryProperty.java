/**
 * 
 */
package com.hanna.mobsters.actors.properties.impl;

import com.hanna.mobsters.actors.properties.Property;
import com.hanna.mobsters.items.Inventory;

/**
 * @author cdhan_000
 *
 */
public class InventoryProperty extends Property<Inventory>{

	@Override
	public Inventory getDefaultValue() {
		return new Inventory();
	}

	@Override
	public String getPropertyName() {
		return "Inventory";
	}

}
