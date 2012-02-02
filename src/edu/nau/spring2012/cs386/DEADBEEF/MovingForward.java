package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class MovingForward implements Behavior  {

	private boolean suppressed = false;
	
	public boolean takeControl() {

		return true;

	}
	
	public void suppress() {
	
		suppressed = true;
		
	}
	
	public void action() {
		
		suppressed = false;

		Motor.A.forward();
		Motor.B.forward();

		while( !suppressed ) {
			Thread.yield();
		}

		Motor.A.stop();
		Motor.B.stop();
		
	}
	
}
