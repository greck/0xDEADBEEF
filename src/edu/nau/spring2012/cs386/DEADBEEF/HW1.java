package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;
import lejos.robotics.*;
import lejos.robotics.subsumption.*;

public class HW1 {

	public static void main(String[] args) {

		// In order of DECREASING priority.
		//
		Behavior b2 = new Stopped();
		Behavior b1 = new MovingForward();

	    Behavior [] bArray = {b1, b2};

	    Arbitrator arby = new Arbitrator(bArray);
	    arby.start();
	    
	}
		
}
