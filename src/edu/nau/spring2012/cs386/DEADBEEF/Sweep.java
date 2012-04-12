package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.LCD;

public class Sweep implements Recipe {

	private boolean nextTurnIsLeft = true;
	private boolean sprinting      = true;
	
	private void displayNextTurn() {
		
		if ( nextTurnIsLeft ) {
			LCD.drawString("Turning: L ",0,4);
		} else {
			LCD.drawString("Turning: R ",0,4);
		}
		
	}

	public Sweep() {
		displayNextTurn();
	}
		
	public boolean execute() {

		RobotState.pilot.travel(-1.5*RobotState.trackWidth);	
		
		if ( !sprinting ) {

			if ( nextTurnIsLeft ) {
				RobotState.pilot.arc(RobotState.trackWidth, 180, false);
			} else {
				RobotState.pilot.arc(-RobotState.trackWidth, -180, false);
			}

			nextTurnIsLeft = !nextTurnIsLeft;
			
		} else {

			sprinting = false;
			RobotState.pilot.arc(RobotState.trackWidth, 90, false);
			
		}
		
		displayNextTurn();
		
		return true;
		
	}

}
