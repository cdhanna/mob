/**
 * 
 */
package com.hanna.mobsters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.hanna.mobsters.actions.*;
import com.hanna.mobsters.actions.impl.*;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.Response;
import com.hanna.mobsters.actors.properties.impl.MoneyProperty;
import com.hanna.mobsters.histories.GameEvent;
import com.hanna.mobsters.histories.EventKey;
import com.hanna.mobsters.histories.GameHistory;


/**
 * @author Chris Hanna
 *
 */
public class Test {

	public static HashMap<EventKey,GameEvent> history;
	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("this is the dev branch");
		
		// Will - 15 July 2013
		Integer turnCount = 0;
		Integer dummyThreadID = 1;
		Integer dummyThreadID2 = 2;
		Integer dummyThreadCount = 10;
		GameHistory history =  GameHistory.getInstance();
		
		Actor joe = ActorBin.getInstance().createActor("Joe"); //new Actor("Joe",100); Chris swapped this out.
		Actor bob = ActorBin.getInstance().createActor("bob"); //new Actor("bob",100); Eventually the createActor will also take a personality
		GiveMoneyAction transferCash = new GiveMoneyAction(8.0,bob,1);
		GiveMoneyAction giveBack = new GiveMoneyAction(4.0,joe,1);
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
		
	//	joe.tellHistory(history);
	//	bob.tellHistory(history);
		
		
		
		MurderAction murder = new MurderAction(joe,1,100.0,-0.1);
		r = bob.speakTo(murder);
		System.out.println(r.getMessage());
		System.out.println(bob.evaluateAction());
	//	bob.tellHistory(history);
		
		history.completeTurnHistory();
		history.initializeGameHistory(dummyThreadCount);
		turnCount++;
		joe.speakTo(murder);
	//	joe.tellHistory(history);
		history.completeTurnHistory();
		history.initializeGameHistory(dummyThreadCount);
		turnCount++;
		
	
		
		
		// end of Will's edits.
	}

}
