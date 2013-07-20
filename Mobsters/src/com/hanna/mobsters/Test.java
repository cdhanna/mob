/**
 * 
 */
package com.hanna.mobsters;

import com.hanna.mobsters.actions.*;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.Response;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;


/**
 * @author Chris Hanna
 *
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("this is the dev branch");
		
		// Will - 15 July 2013
		Actor joe = ActorBin.getInstance().createActor("Joe"); //new Actor("Joe",100); Chris swapped this out.
		Actor bob = ActorBin.getInstance().createActor("bob"); //new Actor("bob",100); Eventually the createActor will also take a personality
		TransferMoneyAction transferCash = new TransferMoneyAction(8.0,bob,1);
		TransferMoneyAction giveBack = new TransferMoneyAction(4.0,joe,1);
		System.out.println(transferCash.toString());
		
		System.out.println(joe.getPropertyValue(MoneyProperty.class));
		System.out.println(bob.getPropertyValue(MoneyProperty.class));
		
		Response r = joe.speakTo(transferCash);
		System.out.println(r.getMessage());
		System.out.println(joe.evaluateAction());
		System.out.println(joe.getPropertyValue(MoneyProperty.class));
		System.out.println(bob.getPropertyValue(MoneyProperty.class));
		
		r = bob.speakTo(giveBack);
		System.out.println(r.getMessage());
		System.out.println(bob.evaluateAction());
		System.out.println(joe.getPropertyValue(MoneyProperty.class));
		System.out.println(bob.getPropertyValue(MoneyProperty.class));
		// end of Will's edits.
	}

}
