/**
 * 
 */
package com.hanna.mobsters.utilities.parsers.impl;

import com.hanna.mobsters.actors.Actor;
import com.hanna.mobsters.actors.ActorBin;
import com.hanna.mobsters.utilities.parsers.Parser;

/**
 * @author Chris Hanna
 *
 */
public class ActorParser extends Parser<Actor> {

	@Override
	protected Actor parseLocal(String str) throws Exception {
		return ActorBin.getInstance().lookUpActor(str);
	}

	@Override
	public Class<Actor> getType() {
		return Actor.class;
	}

}
