/**
 * 
 */
package com.hanna.mobsters;

import com.hanna.mobsters.actions.*;
import com.hanna.mobsters.actors.Actor;


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
		Actor joe = new Actor("Joe",100);
		SongAction mySong = new SongAction("Old MacDonald had a farm...",1,1);
		SongAction badSong = new SongAction("This is a terrible song",1,-1);
		System.out.println(joe.speakTo(mySong));
		System.out.println(joe.evaluateAction());
		System.out.println(joe.speakTo(badSong));
		System.out.println(joe.evaluateAction());
		// end of Will's edits.
	}

}
