package com.hanna.mobsters.actions;

public class SongAction extends Action {
	String song;
	public SongAction(String song, Integer priority, Integer feelVal) {
		this.song = song;
		this.priority = priority;
		this.feelVal = feelVal;
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compareTo(Action arg0) {
		// TODO Auto-generated method stub
		return arg0.priority - this.priority;
	}
	@Override
	public String doIt() {
		// TODO Auto-generated method stub
		return song;
	}

}
