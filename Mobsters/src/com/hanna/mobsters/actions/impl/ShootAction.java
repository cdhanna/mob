/**
 * 
 */
package com.hanna.mobsters.actions.impl;

import java.util.List;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.core.ActionInfoAnnotation;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.impl.InventoryProperty;
import com.hanna.mobsters.items.Inventory;
import com.hanna.mobsters.items.impl.guns.Gun;

/**
 * @author cdhan_000
 *
 */
public class ShootAction extends Action{

	Actor target;
	
	
	@ActionInfoAnnotation(params = { "target", "priority"})
	public ShootAction(Actor target, Integer priority){
		this.priority = priority;
		this.target = target;
	}
	
	@Override
	public int compareTo(Action arg0) {
		return arg0.getPriority() - this.priority;
	}

	@Override
	public String doIt(Actor actor) {
		Inventory inv = actor.getPropertyValue(InventoryProperty.class);
		
		List<Gun> guns = inv.getItemsOfType(Gun.class);
		if (guns.size() > 0){
			guns.get(0).fire(this.target);
		}
		
		
		return "Okay";
	}

	@Override
	public Action mutateAction(double x) {
		return this;
	}

}
