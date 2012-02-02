package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

public class Stopped implements Behavior  {

	private boolean startup = true;
	private boolean stopped = true;
	private boolean suppressed = false;

	private TouchSensor touch = new TouchSensor(SensorPort.S1);

    public boolean takeControl() {

    	if ( startup ) {
   
    		System.out.println("startup!");
    		return true;
    		
    	} else if ( touch.isPressed() ) {

    			System.out.println("button pressed");
    			stopped = true;
    			return true;
    		
    	} else {
    		
    		return false;
  
    	}
    	
	}
	
	public void suppress() {
		
		suppressed = true;		
		
	}
	
	public void action() {
		
		System.out.println("stopped action start");		

		startup = false;
		suppressed = false;

		if ( stopped ) {

			System.out.println("entered stopped loop");
			
			while ( !touch.isPressed() );
			stopped = false;
			
		}

		System.out.println("stopped action end");		
		
	}
		
}
