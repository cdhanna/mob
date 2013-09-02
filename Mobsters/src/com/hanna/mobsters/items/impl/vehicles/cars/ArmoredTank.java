package com.hanna.mobsters.items.impl.vehicles.cars;

import com.hanna.mobsters.items.impl.vehicles.Vehicle;
import com.hanna.mobsters.items.impl.vehicles.VehicleState;

public class ArmoredTank extends Vehicle{

	public Double armorValue;
	
	public ArmoredTank() {
		super("Armored Tank", 5000.0);
		
		this.armorValue = .5;
		
	}

	@Override
	public VehicleState createState() {
		return new VehicleState();
	}

}
