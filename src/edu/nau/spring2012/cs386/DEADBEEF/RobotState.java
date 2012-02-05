package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;

public class RobotState {

	private static TouchSensor      touch      = new TouchSensor(SensorPort.S1);
	private static LightSensor      light      = new LightSensor(SensorPort.S2);
	private static UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S3);
	
	public static boolean touched;
	public static int     lightLevel;
	public static float   range;

	public static boolean moving = false;
	public static int     lineLevel;
	public static int     itrsLost = 0;
	
	public static boolean poll() {

		Thread.yield();

		LCD.drawString("L        R      ",0,1);
		LCD.drawInt(Motor.A.getSpeed(),5,2,1);
		LCD.drawInt(Motor.B.getSpeed(),5,11,1);
		
		LCD.drawString("LineLvl:   ",0,2);
		LCD.drawInt(lineLevel,5,11,2);

		LCD.drawString("LostItrs:  ",0,3);
		LCD.drawInt(itrsLost,5,11,3);
		
		// TouchSensor
		//
		touched = touch.isPressed();
		LCD.drawString("Touched:   ",0,5);
		if ( touched ) {
			LCD.drawString(" true",11,5);
		} else {
			LCD.drawString("false",11,5);
		}
		
		// LightSensor
		//
		lightLevel = light.getNormalizedLightValue();
		LCD.drawString("LightLvl:   ",0,6);
		LCD.drawInt(lightLevel,5,11,6);
				
		// UltrasonicSensor
		//
		range = ultrasonic.getRange();
		LCD.drawString("Range:      ",0,7);
		LCD.drawInt((int)range,5,11,7);

		if ( !Button.ESCAPE.isPressed() ) {
			return true;
		} else {
			return false;
		}
		
	}

}
