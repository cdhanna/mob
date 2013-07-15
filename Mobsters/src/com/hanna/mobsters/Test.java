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
		int cash = 100;
		Actor joe = new Actor(cash);
		Action myAction = new Action();
		String decision = joe.decide(myAction);
		System.out.println(decision);
	}

}
