package com.hanna.mobsters.items.impl.vehicles;

import com.hanna.mobsters.items.ItemState;

public class VehicleState extends ItemState{

	public Double gasoline;
	
	public VehicleState() {
		this.gasoline = 1.0;
	}

	@Override
	public boolean isUsable() {
		// TODO Auto-generated method stub
		return true;
	}

}
