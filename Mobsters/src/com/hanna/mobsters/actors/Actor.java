package com.hanna.mobsters.actors;
import java.util.PriorityQueue;

import com.hanna.mobsters.actions.*;

public class Actor {
	String name;
	int money; // dummy variable
	PriorityQueue<Action> pq; // for holding "to do list" of actions
	double feeling; // expand on this as we build - it should be an array eventually

	// constructor - really just a stub right now
	public Actor(String name,int money)
	{
		this.name = name;
		this.money = money;
		pq = new PriorityQueue<Action>();
		feeling = 1.0; // default feeling generation for now.
	}
	// static decision making method common to all actors
	private static double decider(Action a, double feeling){
		return feeling * a.getFeelVal();
	}

	public String speakTo(Action a){
		String str;
		double decision = decider(a,feeling);
		if (decision > 0){
			str = "I will do it";
			pq.add(a);
		}
		else
			str = "I will not do it";

		return str;
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
