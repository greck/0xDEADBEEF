package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.Sound;

public class Avoid implements Recipe {

	public boolean execute() {

		Sound.buzz();
		
		RobotState.pilot.rotate(45);
		RobotState.pilot.travel(3*RobotState.trackWidth);
		RobotState.pilot.rotate(-90);
		RobotState.pilot.travel(3*RobotState.trackWidth);
		RobotState.pilot.rotate(45);
		
		return true;
		
	}

}
