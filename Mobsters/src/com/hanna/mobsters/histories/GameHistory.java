package com.hanna.mobsters.histories;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.kryo.*;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;




/**Keeps the master list of all GameEvents over the course of the game. A GameEvent is accessed by passing
 * in an EventKey, which contains data that allows GameHistory to find the appropriate event.
 * Once a turn is complete, the data is (will be) compiled into a HashMap and written to disk. When
 * a request for a GameEvent is received via an EventKey, the GameHistory will determine if the GameEvent
 * resides in memory or is stored on the HDD. 
 * @author Will
 * 
 */
public class GameHistory {
	
	private static GameHistory instance;
	
	private Integer currentTurn;
	
	private ArrayList<GameEvent>[] events;
	private Integer[] threadLengths;
	private ArrayList<Integer[]> prevThreadLengths;
	private ArrayList<GameEvent[]> prevTurnEvents; // get rid of this when kryo is working 
	private List<GameHistoryEventListener> listeners;
	
	private GameHistory(Integer threadCount) {
		prevThreadLengths = new ArrayList<Integer[]>();
		prevTurnEvents = new ArrayList<GameEvent[]>();
		this.listeners = new ArrayList<>();
		initializeGameHistory(threadCount);
		this.currentTurn = 0;
	}
	
	public void initializeGameHistory(Integer threadCount){
		
		events = new ArrayList[threadCount];
		threadLengths = new Integer[threadCount];

		for (int i = 0; i < threadCount; i++){
			events[i] = new ArrayList<GameEvent>();
			threadLengths[i] = 0;
		}
	}

	public GameEvent getEvent(EventKey eventKey){
		//TODO unload old data from disk if it is from previous turn
		Integer turn = eventKey.getTurnNumber();
		Integer threadID = eventKey.getThreadID();
		Integer eventNumber = eventKey.getEventNumber();
		if (turn != this.currentTurn){
			System.out.println("DEBUG: Event is from a previous turn!");
			GameEvent[] log = prevTurnEvents.get(turn);
			//GameEvent[] log = new GameEvent[1];
			//try {
				//log = getPrevHistory(turn);
			//} catch (FileNotFoundException e) {
				 //TODO Auto-generated catch block
				 //e.printStackTrace();
			//}
			Integer[] indeces = prevThreadLengths.get(turn);
			Integer index = indeces[threadID] + eventNumber;
			return log[index];
		}
			
		else
			return events[threadID].get(eventNumber);
	}
	
	public void putEvent(EventKey eventKey, GameEvent event){
		Integer threadID = eventKey.getThreadID();
		Integer eventNumber = eventKey.getEventNumber();
		events[threadID].set(eventNumber, event);
		
		this.firePutGameEvent(eventKey, event);
		
	}
	
	private void firePutGameEvent(EventKey eventKey, GameEvent event) {
		for (GameHistoryEventListener listener : this.listeners){
			listener.addGameEvent(eventKey, event);
		}
	}
	
	
	public void addGameHistoryEventListener(GameHistoryEventListener listener){
		this.listeners.add(listener);
	}
	public void removeGameHistoryEventListener(GameHistoryEventListener listener){
		this.listeners.remove(listener);
	}
	
	public interface GameHistoryEventListener {
		public void addGameEvent(EventKey eventKey, GameEvent event);
	}
	
	
	/**
	 * @param threadID the requesting thread.
	 * @return an EventKey which is paired to a blank GameEvent. This event can be accessed and modified
	 * with the key.
	 */
	public EventKey getNextEventKey(Integer threadID){
		EventKey eventKey = new EventKey(this.currentTurn,threadID,events[threadID].size() );
		events[threadID].add(new GameEvent());
		threadLengths[threadID]++;
		return eventKey;
	}
	
	public void completeTurnHistory(){
		Integer eventCount = 0;
		
		// get total number of events and change threadLengths values to represent the 
		// starting index for that thread's events.
		for (int i = 0; i < threadLengths.length; i++){
			Integer value = threadLengths[i];
			threadLengths[i] = eventCount;
			eventCount += value;
		}
		
		// rewrite current turn events into an array for saving
		GameEvent[] eventLog = new GameEvent[eventCount];
		int index = 0;
		for (ArrayList<GameEvent> log : events){
			for (int i = 0; i < log.size(); i++){
				eventLog[index++] = log.get(i);
			}
		}
		// try kryo
		try {
			writeTurnHistory(eventLog);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// clear current turn variables and increment turn counter
		prevThreadLengths.add(threadLengths);
		prevTurnEvents.add(eventLog); // get rid of this when kryo is working
		threadLengths = null;
		events = null;
		currentTurn++;
		this.initializeGameHistory(10);
		
	}
	
	private void writeTurnHistory(GameEvent[] log) throws FileNotFoundException{
		Kryo kryo = new Kryo();
		
		File file = new File("turn histories");
		if (!file.isDirectory())
			file.mkdir();
		
		Output output = new Output(new FileOutputStream(file + "\\turn" + this.currentTurn + ".bin"));
		kryo.writeObjectOrNull(output, log, GameEvent[].class);
		output.close();
	}
	
	private GameEvent[] getPrevHistory(Integer turn) throws FileNotFoundException{
		Input input = new Input(new FileInputStream("turn histories\\turn" + turn + ".bin"));	
		Kryo kryo = new Kryo();
		
		GameEvent[] log = new GameEvent[1];
		log = kryo.readObjectOrNull(input, GameEvent[].class);
		return log;
	}
	
	public static GameHistory getInstance(){
		if (instance == null)
			instance = new GameHistory(10);
		return instance;
	}
	
	public List<GameEvent> getAllHistory(){
		List<GameEvent> masterList = new ArrayList<GameEvent>();
		
		for (GameEvent[] eventLog : prevTurnEvents)
			for (GameEvent e : eventLog)
				masterList.add(e);
		
		for (ArrayList<GameEvent> eventList : events)
			for (GameEvent e : eventList)
				masterList.add(e);
		
		return masterList;
		

	}
}
