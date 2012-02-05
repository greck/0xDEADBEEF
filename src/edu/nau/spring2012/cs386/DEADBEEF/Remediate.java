package edu.nau.spring2012.cs386.DEADBEEF;

import java.util.Random;
import lejos.nxt.*;

public class Remediate implements Recipe {

	private boolean trendIsLeft;
	private int       reversals;
	private int       triesLeft;
	
	private void displayTrend() {
		
		if (trendIsLeft) {
			LCD.drawString("Trend:   L ",0,4);
		} else {
			LCD.drawString("Trend:   R ",0,4);
		}
		
	}

	private void calcTriesLeft() {
		switch(reversals) {
		case 0: triesLeft = 1400/DrivingMacros.baseSpeed; break;
		case 1: triesLeft = 4200/DrivingMacros.baseSpeed; break;
		case 2: triesLeft = 12600/DrivingMacros.baseSpeed; break;
		default: triesLeft = 999999/DrivingMacros.baseSpeed;
		}
	}
	
	public Remediate() {
		Random r = new Random(System.currentTimeMillis());
		trendIsLeft = r.nextBoolean();
		displayTrend();
	}
		
	public boolean execute() {

		if ( RobotState.itrsLost == 0 ) {
			reversals = 0;
			calcTriesLeft();
		}
		
		RobotState.itrsLost++;
		
		if ( triesLeft == 0 ) {

			if (trendIsLeft) {
				trendIsLeft = false;
			} else {
				trendIsLeft = true;
			}

			reversals++;
			calcTriesLeft();
			
		}

		displayTrend();
		LCD.drawInt(triesLeft,5,11,4);
		
		if (trendIsLeft) {
			DrivingMacros.turnLeft();
		} else {
			DrivingMacros.turnRight();
		}

		triesLeft--;
		
		return true;
		
	}

}
