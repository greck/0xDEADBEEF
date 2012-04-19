package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.LCD;

public class SweepRight implements Recipe  {
	//is currently still blocking
	public boolean execute() {
		LCD.drawString("Turning: R ",0,4);
		
		RobotState.pilot.travel(-1.5*RobotState.trackWidth);
		RobotState.pilot.arc(-RobotState.trackWidth, -180);
		
		return true;
	}
}
