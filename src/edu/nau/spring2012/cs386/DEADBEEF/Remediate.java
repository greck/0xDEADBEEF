package edu.nau.spring2012.cs386.DEADBEEF;

import java.util.Random;

import lejos.nxt.LCD;

public class Remediate implements Recipe {

	private boolean trendIsLeft;
	
	// tunables
	//
	private int slowDownInterval =  2;
	private int maxItrsLost      = 10;

	public Remediate() {
		Random r = new Random(System.currentTimeMillis());
		trendIsLeft = r.nextBoolean();
	}
	
	public boolean execute() {

		if (trendIsLeft) {
			LCD.drawString(" left",4,11);
		} else {
			LCD.drawString("right",4,11);
		}

		if (RobotState.itrsLost % slowDownInterval == 0) {
			DrivingMacros.slowDown();
		}

		if (RobotState.itrsLost < maxItrsLost) {
			if (trendIsLeft) {
				DrivingMacros.turnLeft();
			} else {
				DrivingMacros.turnRight();
			}
		} else {
			if (trendIsLeft) {
				DrivingMacros.turnRight();
			} else {
				DrivingMacros.turnRight();
			}
		}

		return true;
		
	}

}
