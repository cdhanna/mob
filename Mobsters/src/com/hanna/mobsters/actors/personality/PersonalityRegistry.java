/**
 * 
 */
package com.hanna.mobsters.actors.personality;

import java.util.ArrayList;
import java.util.List;

import com.hanna.mobsters.actors.traits.MoneyTrait;
import com.hanna.mobsters.actors.traits.ShyTrait;
import com.hanna.mobsters.actors.traits.Trait;
import com.hanna.mobsters.utilities.KryoHelper;
import com.hanna.mobsters.utilities.Registry;

/**
 * @author Chris Hanna
 *
 */
public class PersonalityRegistry{

	
	private static PersonalityRegistry instance;
	public static PersonalityRegistry getInstance(){
		if (instance == null)
			instance = new PersonalityRegistry();
		return instance;
	}
	
	private List<Trait> GANGSTER;
	private List<Trait> DEFAULT;
	private List<Trait> CIVILIAN;
	private PersonalityRegistry(){
		
		this.setUpCIVILIAN();
		this.setUpDEFAULT();
		this.setUpGANGSTER();
		
	}

	private void setUpGANGSTER(){
		this.GANGSTER = new ArrayList<>();
		this.GANGSTER.add(new MoneyTrait(2));
		this.GANGSTER.add(new ShyTrait(1));
	}
	
	private void setUpCIVILIAN(){
		this.CIVILIAN = new ArrayList<>();
	}
	
	private void setUpDEFAULT(){
		this.DEFAULT = new ArrayList<>();
	}
	
	private List<Trait> getPersonalityTraitsHelper(Personality personality){
		if (personality.equals(Personality.GANGSTER)){
			return this.GANGSTER;
		} else if (personality.equals(Personality.CIVILIAN)){
			return this.CIVILIAN;
		} else return this.DEFAULT;
	}

	public List<Trait> getPersonality(Personality personality){
		return KryoHelper.getInstance().copy(this.getPersonalityTraitsHelper(personality));
	}

	
}
