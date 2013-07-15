/**
 * 
 */
package com.hanna.mobsters;

import actors.*;
import actions.*;

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
		System.out.println("Here is a simple test of actor decision making and reporting");
		int cash = 100; // some dummy actor property. Placeholder for now.
		
		// now we will instantiate a new actor called joe and we will make 3 actions to give to him.
		Actor joe = new Actor(cash);
		Action addAction = new mathAction(10.0,5.0,'+',1);
		Action minusAction = new mathAction(7.0,4.0,'-',1);
		Action divideAction = new mathAction(100.0,10.0,'/',3); // note priority level 3
																// this should be done first
		
		// joe decides if he will do each action.
		String decision;
		System.out.println("Hey Joe, add 10 and 5 when you get around to it.");
		decision = joe.decide(addAction);
		System.out.println(decision);
		System.out.println("Joe, I'd also like you to subtract 4 from 7 when you get a chance.");
		decision = joe.decide(minusAction); // the subtraction action is "bad" - see constructor
		System.out.println(decision);
		System.out.println("Oh crap Joe, I really need you to divide 100 by ten right away!");
		decision = joe.decide(divideAction);
		System.out.println(decision);
		
		// joe does all of the actions he decided to do.
		System.out.println("Joe says...");
		joe.evaluateActions();
		// end of Will's edits.
	}

}
