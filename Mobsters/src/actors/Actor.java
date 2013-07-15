package actors;
import actions.*;
import java.util.PriorityQueue;

public class Actor {
int money;
PriorityQueue<Action> pq;
double feeling;

	// constructor - really just a stub right now
	public Actor(int money)
{
	this.money = money;
	pq = new PriorityQueue<Action>();
	feeling = 1.0; // default feeling generation for now.
}
	// static decision making method common to all actors
	private static double decider(Action a, double feeling){
		return feeling * a.getFeelVal();
	}
	public String decide(Action a){
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
	
	public void evaluateActions(){
		Action a;
		while (!pq.isEmpty()){
			a = pq.remove();
			a.doIt();
		}
				
	}
	
}
