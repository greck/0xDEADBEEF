package edu.nau.spring2012.cs386.DEADBEEF;

import java.util.Random;
import lejos.nxt.*;

public class Remediate implements Recipe {

	private boolean   nextTurnIsLeft;
	private int    firstLineLevelErr;
	private int[]  lineLevelErrTrend = new int[3];
	
	private void displayNextTurn() {
		
		if ( nextTurnIsLeft ) {
			LCD.drawString("nextTurn: L ",0,4);
		} else {
			LCD.drawString("nextTurn: R ",0,4);
		}
		
	}

	public Remediate() {
		Random r = new Random(System.currentTimeMillis());
		nextTurnIsLeft = r.nextBoolean();
	}
		
	public boolean execute() {

		int avgLineLevelErr;

		RobotState.itrsLost++;

		switch(RobotState.itrsLost) {
		case 1:
			firstLineLevelErr = RobotState.lineLevelErr;
			break;
		case 2:
		case 3:
		case 4:
			lineLevelErrTrend[RobotState.itrsLost] = RobotState.lineLevelErr;
			break;
		case 5:
			avgLineLevelErr = (lineLevelErrTrend[0] +
					lineLevelErrTrend[1] + lineLevelErrTrend[2]) / 3;
			if ( avgLineLevelErr > firstLineLevelErr ) {
				if ( nextTurnIsLeft ) {
					nextTurnIsLeft = false;
				} else {
					nextTurnIsLeft = true;
				}		
			}
			break;
		default:
				
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
