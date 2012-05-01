package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.Sound;

public class Panic implements Recipe {

	public boolean execute() {

		Sound.buzz();
		
		return true;
		
	}

}
