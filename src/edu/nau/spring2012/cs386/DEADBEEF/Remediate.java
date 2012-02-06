package edu.nau.spring2012.cs386.DEADBEEF;

import java.util.Random;
import lejos.nxt.*;

public class Remediate implements Recipe {

	private boolean nextTurnIsLeft;
	private int     initialError;
	private int     errorTrend;
	
	// tunables
	private int     reversalThreshold = 3500/DrivingMacros.baseSpeed;
	
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

		RobotState.itrsLost++;

		if ( RobotState.itrsLost == 1 ) {
			initialError = RobotState.lineLevelErr;
		}
		
		if ( RobotState.itrsLost < reversalThreshold ) {
			errorTrend += RobotState.lineLevelErr;
		}
		
		if ( RobotState.itrsLost == reversalThreshold ) {
			errorTrend /= reversalThreshold - 1;
			if ( errorTrend > initialError ) {
				if ( nextTurnIsLeft ) {
					nextTurnIsLeft = false;
				} else {
					nextTurnIsLeft = true;
				}
			}
		}

		displayNextTurn();
		
		if ( nextTurnIsLeft ) {
			DrivingMacros.turnLeft();
		} else {
			DrivingMacros.turnRight();
		}
		
		return true;
		
	}

}
