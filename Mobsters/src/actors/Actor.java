package actors;
import actions.*;
import java.util.PriorityQueue;

public class Actor {
int money;
PriorityQueue<Action> pq;

	// constructor - really just a stub right now
	public Actor(int money)
{
	this.money = money;
	pq = new PriorityQueue<Action>();
}
	// static decison making method common to all actors
	private static double decider(Action a){
		return 1;
	}
	public String decide(Action a){
		String str;
		double decision = decider(a);
		if (decision > 0){
			str = "I will do it";
			pq.add(a);
		}
		else
			str = "I will not do it";
		
		return str;
	}
	
	public void evaluateActions(){
		for (Action a:pq)
			a.doIt();
		
	}
	
}
