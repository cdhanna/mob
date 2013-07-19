package com.hanna.mobsters.actors;

import java.util.*;
import com.hanna.mobsters.actions.*;
import com.hanna.mobsters.actions.core.Action;
import com.hanna.mobsters.actors.traits.*;

public class Actor {
	ArrayList<Trait> personality;
	String name;
	int money; // dummy variable
	PriorityQueue<Action> pq; // for holding "to do list" of actions

	// constructor - really just a stub right now
	public Actor(String name,int money)
	{
		this.name = name;
		this.money = money;
		personality = new ArrayList<Trait>();
		personality.add(new MoneyTrait(2)); // money value will be squared for this character
		pq = new PriorityQueue<Action>();
	}
	// static decision making method common to all actors
	private static double decider(Action a, ArrayList<Trait> personality){
		double w = 0;
		for (Trait t:personality)
			w+=t.compute(a);
			
		return w;
	}

	public Response speakTo(Action a){
		String str;
		boolean yesno = false;
		double decision = decider(a,personality);
		a = a.mutateAction(decision);
		if (a != null){
			str = "I will do it";
			yesno = true;
			pq.add(a);
		}
		else{
			str = "I will not do it";
			yesno = false;
		}
		return new Response(yesno, str);
	}

	public String evaluateAction(){
		if (!pq.isEmpty())
			return pq.remove().doIt();
		else
			return "I ain't got shit to do";
		}
	
	public String getName(){return name;}
	public PriorityQueue<Action> getPQ(){return pq;}

}
