/**
 * 
 */
package com.hanna.mobsters.items.impl.guns.rifles;

import com.hanna.mobsters.items.impl.guns.Gun;
import com.hanna.mobsters.items.impl.guns.GunState;

/**
 * @author cdhan_000
 *
 */
public class Rifle extends Gun{

	public Rifle() {
		super("Rifle", 25.0);
	}

	@Override
	public GunState createState() {
		return new GunState();
	}

	@Override
	protected void setDamage() {
		this.damage = 90.0;
	}


}
