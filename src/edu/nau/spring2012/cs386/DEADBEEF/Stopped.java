package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class Stopped implements Behavior  {
	
    private TouchSensor touch;

    public boolean takeControl() {
		
		return touch.isPressed();
	
	}
	
	public void suppress() {

	}
	
	public void action() {
		
		// this method by design doesn't exit quickly... because we
		// don't want to do anything else as long as we're stalled
		// in the Stopped behavior
		
		while ( !touch.isPressed() );
		
	}
	
}
