package com.hanna.mobsters.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.hanna.mobsters.actions.impl.ShootAction;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.properties.impl.InventoryProperty;
import com.hanna.mobsters.actors.properties.impl.MedicalStateProperty;
import com.hanna.mobsters.items.Inventory;
import com.hanna.mobsters.items.impl.guns.Gun;
import com.hanna.mobsters.items.impl.guns.pistol.Pistol;
import com.hanna.mobsters.items.impl.guns.rifles.Rifle;
import com.hanna.mobsters.items.impl.vehicles.cars.Car;
import com.hanna.mobsters.items.impl.vehicles.cars.Convertable;

public class IventoryTests {

	@Test
	public void test() {
		
		Actor bob = ActorBin.getInstance().createActor("Bob");
		Actor jill = ActorBin.getInstance().createActor("Jill");
		Inventory inv = bob.getPropertyValue(InventoryProperty.class);
		
		Pistol pistol = new Pistol();
		Rifle rifle = new Rifle();
		Convertable convertable = new Convertable();
		
		
		inv.put(pistol);
		inv.put(rifle);
		inv.put(convertable);
		
		
		List<Pistol> guns = inv.getItemsOfType(Pistol.class);
		
		assertEquals(false, inv.isEmpty());
		assertEquals(inv.getSize(), 3);
		assertEquals(1, guns.size());
		assertEquals(pistol, guns.get(0));
		assertEquals(rifle, inv.take(rifle));
		assertEquals(1, inv.getItemsOfType(Car.class).size());
		
		
		bob.speakTo(new ShootAction(jill, 1));
		bob.evaluateAction();
		System.out.println(jill.getPropertyValue(MedicalStateProperty.class));
		
	}

}
