/**
 * 
 */
package com.hanna.mobsters.items;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.properties.Location;

/**
 * @author Chris
 *
 */
public abstract class Item <S extends ItemState>{

	protected String name;
	public Double worth;
	public Location location;
	protected Actor owner;
	protected S state;
	protected Integer id;
	
	private static Integer mid = 0;
	
	public Item(String name){
		this.name = name;
		this.worth = 0.0;
		this.location = Location.NOWHERE;
		this.state = this.createState();
		id = mid; mid++;
	}
	public Item(String name, Double worth){
		this.name = name;
		this.worth = worth;
		this.location = Location.NOWHERE;
		this.state = this.createState();
		id = mid; mid++;
	}
	/**
	 * WHEN YOU OVERRIDE THIS, THE FINAL CONSTRUCTOR MUST NOT HAVE ANY PARAMETERS.
	 * @param name
	 * @param worth
	 * @param location
	 */
	public Item(String name, Double worth, Location location){
		this.name = name;
		this.worth = worth;
		this.location = location;
		this.state = this.createState();
		id = mid; mid++;
	}
	
	public abstract S createState();
	
	public final void setOwner(Actor owner){
		this.owner = owner;
	}
	public final Actor getOwner(){
		return this.owner;
	}
	public String getName() {
		return this.name;
	}
	public S getState(){
		return this.state;
	}
	public Double getWorth(){
		return this.worth;
	}
	public void setWorth(Double worth){
		this.worth = worth;
	}
	
	public Location getLocation(){
		return this.location;
	}
	public void setLocation(Location location){
		this.location = location;
	}
	public Integer getId() {
		return this.id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.getClass().getSimpleName();
	}
}
