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

		Robot.pilot.travel(-Robot.trackWidth);	
		
		if ( !sprinting ) {

			if ( nextTurnIsLeft ) {
				Robot.pilot.rotate(90);
				Robot.pilot.travel(Robot.trackWidth);
				Robot.pilot.rotate(90);
			} else {
				Robot.pilot.rotate(-90);
				Robot.pilot.travel(Robot.trackWidth);
				Robot.pilot.rotate(-90);
			}

			nextTurnIsLeft = !nextTurnIsLeft;
			
		} else {

			sprinting = false;
			Robot.pilot.rotate(90);
			
		}
		
		displayNextTurn();
		
		return true;
		
	}

}
