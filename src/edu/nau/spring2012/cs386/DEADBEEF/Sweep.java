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

		Robot.pilot.travel(-1.5*Robot.trackWidth);	
		
		if ( !sprinting ) {

			if ( nextTurnIsLeft ) {
				Robot.pilot.arc(Robot.trackWidth, 180);
			} else {
				Robot.pilot.arc(-Robot.trackWidth, -180);
			}

			nextTurnIsLeft = !nextTurnIsLeft;
			
		} else {

			sprinting = false;
			Robot.pilot.arc(Robot.trackWidth, 90);
			
		}
		
		displayNextTurn();
		
		return true;
		
	}

}
