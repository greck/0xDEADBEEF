package edu.nau.spring2012.cs386.DEADBEEF;

import java.util.Random;

import lejos.nxt.LCD;

public class Remediate implements Recipe {

	private boolean trendIsLeft;
	
	// tunables
	//
	private int slowDownInterval  = 5;
	private int maxItrsLost      = 25;

	public Remediate() {
		Random r = new Random(System.currentTimeMillis());
		trendIsLeft = r.nextBoolean();
	}
	
	public boolean execute() {

		RobotState.itrsLost++;
		
		if (RobotState.itrsLost == maxItrsLost) {
			if (trendIsLeft) {
				trendIsLeft = false;
			} else {
				trendIsLeft = true;
			}
		}
		
		if (trendIsLeft) {
			LCD.drawString(" left",11,4);
		} else {
			LCD.drawString("right",11,4);
		}

//		if (RobotState.itrsLost % slowDownInterval == 0) {
//			DrivingMacros.slowDown();
//		}

		if (trendIsLeft) {
			DrivingMacros.turnLeft();
		} else {
			DrivingMacros.turnRight();
		}

		return true;
		
	}

}
