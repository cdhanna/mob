/**
 * 
 */
package com.hanna.mobsters.items.impl.guns.pistol;

import com.hanna.mobsters.items.impl.guns.Gun;
import com.hanna.mobsters.items.impl.guns.GunState;

/**
 * @author cdhan_000
 *
 */
public class Pistol extends Gun{

	public Pistol() {
		super(".45", 10.0);
		
	}

	@Override
	public GunState createState() {
		return new GunState();
	}

	@Override
	protected void setDamage() {
		this.damage = 40.0;
	}

}
