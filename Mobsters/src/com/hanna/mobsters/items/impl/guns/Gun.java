/**
 * 
 */
package com.hanna.mobsters.items.impl.guns;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.Location;
import com.hanna.mobsters.actors.properties.impl.MedicalStateProperty;
import com.hanna.mobsters.items.Item;
import com.hanna.mobsters.items.ItemState;

/**
 * @author cdhan_000
 *
 */
public abstract class Gun extends Item<GunState>{
	
	public Double damage;
	
	public Gun(String name, Double worth){
		super(name, worth);
		this.damage = 0.0;
		this.setDamage();
	}

	/**
	 * @param target
	 * @return the amount of damage done to the target
	 */
	public Double fire(Actor target){

		if (this.state.mayFire && !this.state.didJam()){
			//Did I hit the target?
			//if (hit....)
			//deal the target damage
			target.setPropertyValue(MedicalStateProperty.class, 
					target.getPropertyValue(MedicalStateProperty.class) - this.damage);
		}
		
		return 0.0;
	}

	protected abstract void setDamage();
}
