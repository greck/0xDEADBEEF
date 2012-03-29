package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.Motor;
import lejos.nxt.Sound;

public class GoHome implements Recipe {

	public boolean execute() {

		Sound.buzz();

		float angle;
		
		// get back to the X axis
		//

		if ( RobotState.poseProvider.getPose().getY() > 0 ) {
			
			angle = 270 - RobotState.poseProvider.getPose().getHeading();
			
		} else {
			
			angle = RobotState.poseProvider.getPose().getHeading() - 90;
			
		}

		RobotState.pilot.rotate(angle,false);
		RobotState.pilot.travel(Math.abs(RobotState.poseProvider.getPose().getY()),false);

		// point towards the negative end of the x axis
		//
		
		if ( RobotState.poseProvider.getPose().getHeading() == 90 ) {
			
			RobotState.pilot.rotate(90);
			
		} else { 
			
			RobotState.pilot.rotate(-90);
		}

		// go home!
		//
		RobotState.pilot.travel(Math.abs(RobotState.poseProvider.getPose().getX())-15,false);

		// drop the ball
		//
		Motor.C.rotate(-80);
		
		return true;
		
	}

}
