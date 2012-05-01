package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.LCD;

public class TurnLeft implements Recipe {

	public boolean execute() {

		LCD.drawString("Turning: L ",0,4);

		RobotState.pilot.arc(RobotState.trackWidth, 90, true);
		
		return true;
		
	}

}
