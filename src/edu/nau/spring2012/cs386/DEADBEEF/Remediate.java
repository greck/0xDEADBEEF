package edu.nau.spring2012.cs386.DEADBEEF;

import java.util.Random;
import lejos.nxt.*;

public class Remediate implements Recipe {

	private boolean nextTurnIsLeft;
	private int     lastError;
	private int     consecutiveErrs;
	
	// tunables
	private int  maxConsecutiveErrs = 15;
	
	private void displayNextTurn() {
		
		if ( nextTurnIsLeft ) {
			LCD.drawString("Turning: L ",0,4);
		} else {
			LCD.drawString("Turning: R ",0,4);
		}
		
	}

	public Remediate() {
		Random r = new Random(System.currentTimeMillis());
		nextTurnIsLeft = r.nextBoolean();
		displayNextTurn();
	}
		
	public boolean execute() {

		if ( RobotState.itrsLost == 0 ) {
			lastError = RobotState.lineLevelErr;
			consecutiveErrs = 0;
		}
		
		RobotState.itrsLost++;

		if ( RobotState.lineLevelErr > lastError ) {
			consecutiveErrs++;
		} else {
			if ( consecutiveErrs > 0 ) {
				consecutiveErrs--;
			}
		}

		if ( consecutiveErrs == maxConsecutiveErrs ) {
			nextTurnIsLeft = !nextTurnIsLeft;
			consecutiveErrs = 0;
		}

		lastError = RobotState.lineLevelErr;

		displayNextTurn();
		
		if ( nextTurnIsLeft ) {
			DrivingMacros.turnLeft();
		} else {
			DrivingMacros.turnRight();
		}
		
		return true;
		
	}

}
