package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.LCD;

public class TurnRight implements Recipe {

	public boolean execute() {

		LCD.drawString("Turning: R ",0,4);

		RobotState.pilot.arc(-RobotState.trackWidth/2, -90, true);
		
		return true;
		
	}

}
