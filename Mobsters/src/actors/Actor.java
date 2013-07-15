package actors;
import actions.*;

public class Actor {
int money;
	public Actor(int money)
{
	this.money = money;
}
	private static double decider(Action a){
		return 0;
	}
	public String decide(Action a){
		String str;
		double decision = decider(a);
		if (decision > 0)
			str = "I will do it";
		else
			str = "I will not do it";
		
		return str;
	}
	
}
