package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.Button;
import lejos.nxt.Motor;

public class CheckColor implements Recipe {

	public boolean execute() {
		
		// Code to close the claw.
		Motor.C.rotate(80);
		Button.ENTER.waitForPressAndRelease();
		
		// ----- CODE TO OPEN CLAW:
		// Motor.C.rotate(-80);
		// Button.ENTER.waitForPressAndRelease();
		
		if ( RobotState.colorObj.getRed() > RobotState.colorObj.getBlue() ) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}

}
