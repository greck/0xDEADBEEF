package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.Sound;

public class Avoid implements Recipe {

	public boolean execute() {

		Sound.buzz();
		
		Robot.pilot.rotate(45);
		Robot.pilot.travel(3*Robot.trackWidth);
		Robot.pilot.rotate(-90);
		Robot.pilot.travel(3*Robot.trackWidth);
		Robot.pilot.rotate(45);
		
		return true;
		
	}

}
