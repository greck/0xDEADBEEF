package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.Motor;

public class DetectBall implements Recipe {

	public boolean execute() {

		if ( RobotState.range < 30 ) {

			RobotState.pilot.travel(20,false);

			// Code to close the claw.
			//
			Motor.C.rotate(80);

			RobotState.pilot.adjustHeading();
			
			return true;

		} else {

			return false;

		}
		
	}

}
