package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.Motor;
import lejos.nxt.Sound;

public class GoHome implements Recipe {

	public boolean execute() {

		Sound.beep();

		float angle;
		
		// get back to the X axis
		//

		if ( RobotState.pilot.getPose().getY() > 0 ) {
			
			angle = 270 - RobotState.pilot.getPose().getHeading();
			
		} else {
			
			angle = RobotState.pilot.getPose().getHeading() - 90;
			
		}

		RobotState.pilot.rotate(angle,false);
		RobotState.pilot.adjustHeading();
		RobotState.pilot.travel(Math.abs(RobotState.pilot.getPose().getY()),false);

		// point towards the negative end of the x axis
		//
		
		if ( RobotState.pilot.getPose().getHeading() == 90 ) {
			
			RobotState.pilot.rotate(90);
			
		} else { 
			
			RobotState.pilot.rotate(-90);
		}

		RobotState.pilot.adjustHeading();

		// go home!
		//
		RobotState.pilot.travel(Math.abs(RobotState.pilot.getPose().getX())-15,false);

		// drop the ball
		//
		Motor.C.rotate(-80);

		Sound.beepSequence();
		
		return true;
		
	}

}
