/**
 * 
 */
package com.hanna.mobsters.actors.properties;

/**
 * @author Chris Hanna
 *
 */
public class Location {

	public static final Location MOB_HOME = new Location("Sketchy Lane", 12);
	public static final Location NOWHERE = new Location("nowhere", 0);
	
	private String streetName;
	private Integer streetNumber;
	
	public Location(String streetName, Integer streetNumber){
		this.streetName = streetName;
		this.streetNumber = streetNumber;
	}
	
	public Location(){}
	
	public String getStreet(){
		return this.streetName;
	}
	public Integer getStreetNumber(){
		return this.streetNumber;
	}
	public void setStreet(String street){
		this.streetName = street;
	}
	public void setStreetNumber(Integer number){
		this.streetNumber = number;
	}
	
	public boolean isOnStreet(Location other){
		return (this.streetName.equals(other.getStreet()));
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj instanceof Location){
			Location other = (Location)obj;
			return other.streetName.equals(this.streetName)
					&& other.streetNumber.equals(this.streetNumber);
		} else return false;
	}
	
	@Override
	public String toString(){
		return this.streetNumber + " " + this.streetName;
	}
	
}
