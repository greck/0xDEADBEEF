package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.Motor;
import lejos.nxt.Sound;

public class Avoid implements Recipe {

	public boolean execute() {

		RobotState.pilot.travel(20,false);
		RobotState.pilot.rotate(180,false);

		// drop the ball
		//
		Motor.C.rotate(-80);
		
		RobotState.pilot.travel(-10,false);
		RobotState.pilot.rotate(180,false);
		
		return true;
		
	}

}
