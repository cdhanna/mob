/**
 * 
 */
package com.hanna.mobsters.items.impl.vehicles.cars;

import com.hanna.mobsters.items.impl.vehicles.Vehicle;
import com.hanna.mobsters.items.impl.vehicles.VehicleState;

/**
 * @author cdhan_000
 *
 */
public class Car extends Vehicle{

	public Car() {
		super("Ford", 1000.0);
	}
	public Car(String name, Double worth){
		super(name, worth);
	}

	@Override
	public VehicleState createState() {
		return new VehicleState();
	}

}
