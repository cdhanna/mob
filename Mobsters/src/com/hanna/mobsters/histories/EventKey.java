package com.hanna.mobsters.histories;

/**
 * An EventKey is used to gain access to a GameEvent. Keys and Events are paired in the GameHistory 
 * class, and every physical object in the game has a list of EventKeys which describe its history.
 * @author Will
 * 
 */
public class EventKey {

	private Integer turnNumber; // the turn number the event was created on
	private Integer threadID; // the thread of execution
	private Integer eventNumber; // relative to turnNumber and threadID
	// TODO add in some kind of masking terms - bullshit and lies. Maybe this should be a separate class,
	// actually, we'll come to it later.
	
	public EventKey(Integer turnNumber,Integer threadID,Integer eventNumber) {
		this.turnNumber = turnNumber;
		this.threadID = threadID;
		this.eventNumber = eventNumber;
	}
	
	public Integer getTurnNumber(){
		return this.turnNumber;
	}
	
	public Integer getThreadID(){
		return this.threadID;
	}
	
	public Integer getEventNumber(){
		return this.eventNumber;
	}

}
