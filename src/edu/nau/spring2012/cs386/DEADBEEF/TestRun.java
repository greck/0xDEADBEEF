package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;


public class TestRun {
	
	public static void main(String[] args) {

		TouchSensor touch = new TouchSensor(SensorPort.S1);
		LightSensor light = new LightSensor(SensorPort.S2);
		UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S3);;
		Motor.A.stop();
		Motor.B.stop();
		
		boolean direction;
		
		int Lval, Lavg = 0;
		
		light.setFloodlight(true);
		
		System.out.println("Calabrate bright");
		while (!touch.isPressed());
		light.calibrateLow();
		Lavg = light.getLightValue();
		
		while (touch.isPressed());
		
		System.out.println("Calabrate dark");
		while (!touch.isPressed());
		light.calibrateHigh();
		Lavg += light.getLightValue();
		
		while (touch.isPressed());
		Lavg /= 2;

		System.out.println("Press to start");
		while (!touch.isPressed());
		while (touch.isPressed());
		
		if (light.getLightValue() >  Lavg) {
			direction = false;
		} else {
			direction = true;
		}
		
		while ( true ) {
			Lval = light.getLightValue();
			
			if (Lval > Lavg) {
				if (direction == false) {
					direction = true;
					Motor.A.stop(true);
					Motor.B.forward();
				}
			} else {
				if (direction == true) {
					direction = false;
					Motor.B.stop(true);
					Motor.A.forward();
				}
			}
			
			System.out.println("" + sonic.getDistance() + ", " + Lval);
			
			if (touch.isPressed()) { //pause program
				while (touch.isPressed());
				while (!touch.isPressed());
				while (touch.isPressed());
			}
		}
		
		
	}
	
}
