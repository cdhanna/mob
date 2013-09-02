package com.hanna.mobsters.items.impl.guns;

public class TommyGun extends Gun{

	public Integer shotsPerTurn;
	
	public TommyGun() {
		super("Tommy Gun", 40.0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setDamage() {
		this.damage = 2500000.0;
	}

	@Override
	public GunState createState() {
		return new GunState();
	}

}
