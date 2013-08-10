/**
 * 
 */
package com.hanna.mobsters.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actions.impl.MathAction;
import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.actors.Response;
import com.hanna.mobsters.actors.personality.Personality;

/**
 * @author cdhan_000
 *
 */
public class ActionTests {

	Actor sir;
	Actor man;
	
	@Before
	public void setup(){
		this.sir = ActorBin.getInstance().createActor("sir", Personality.GANGSTER);
		this.man = ActorBin.getInstance().createActor("man", Personality.GANGSTER);
	}
	
	@Test
	public void test() {
		Action action = new MathAction(1.0, 1.0, "+", 1, 2.0);
		Response response = this.sir.speakTo(action);
		assertTrue( (response.getYesNo()));
	}

}
