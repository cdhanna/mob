/**
 * 
 */
package com.hanna.mobsters.items.impl.guns;

import com.hanna.mobsters.items.ItemState;

/**
 * @author cdhan_000
 *
 */
public class GunState extends ItemState{

	public Boolean mayFire;
	public Double gunCleanlyness;
	public Double gunCleanlynessThreshold;
	
	public GunState() {
		
		this.mayFire = true;
		this.gunCleanlyness = .8;
		this.gunCleanlynessThreshold = .05;
	}

	public void setGunCleanlyness(Double cleanlyness){
		this.gunCleanlyness = cleanlyness;
	}
	
	public void setGunCleanlynessThreshold(Double threshold){
		this.gunCleanlynessThreshold = threshold;
	}
	
	protected boolean didJam(){
		boolean didJam = (Math.random() * this.gunCleanlyness) < this.gunCleanlynessThreshold;
		System.out.println("SHOOTING GUN. DID JAM = " + didJam);
		return didJam;
	}
	
	@Override
	public boolean isUsable(){
		return (this.gunCleanlyness > this.gunCleanlynessThreshold);
	}
}
