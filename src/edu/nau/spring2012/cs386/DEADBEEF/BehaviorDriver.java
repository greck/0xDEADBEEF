package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.robotics.subsumption.*;

public class BehaviorDriver {

	public static void main(String[] args) {

		System.out.println("Hi");
	    
		// In order of DECREASING priority.
		//
		Behavior b2 = new Stopped();
		Behavior b1 = new MovingForward();

	    Behavior [] bArray = {b1, b2};

	    Arbitrator arby = new Arbitrator(bArray);
	    arby.start();

	}
		
}
