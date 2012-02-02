package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;

public class TouchTest {

	public static void main(String[] args) {

		TouchSensor touch = new TouchSensor(SensorPort.S1);

		while ( true ) {
		
		while ( !touch.isPressed() );
		System.out.println("button down");

		while ( touch.isPressed() );
		System.out.println("button up");
	    
		}
				
	}
		
}
