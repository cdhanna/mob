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
	
	private String streetName;
	private Integer streetNumber;
	
	public Location(String streetName, Integer streetNumber){
		this.streetName = streetName;
		this.streetNumber = streetNumber;
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
