package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.LCD;

public class Sweep implements Recipe {

	private boolean nextTurnIsLeft = true;
	private boolean sprinting      = true;
	
	// tunable
	private int sweepWidth = 10;
	
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

		Robot.pilot.travel(-sweepWidth);	

		if ( !sprinting ) {

			if ( nextTurnIsLeft ) {
				Robot.pilot.rotate(-90);
				Robot.pilot.travel(sweepWidth);
				Robot.pilot.rotate(-90);
			} else {
				Robot.pilot.rotate(90);
				Robot.pilot.travel(sweepWidth);
				Robot.pilot.rotate(90);
			}
			
		} else {
			
			sprinting = false;
				
			if ( nextTurnIsLeft ) {
				Robot.pilot.rotate(-90);
			} else {
				Robot.pilot.rotate(90);
			}
			
		}
		
		nextTurnIsLeft = !nextTurnIsLeft;
		displayNextTurn();
		
		return true;
		
	}

}
