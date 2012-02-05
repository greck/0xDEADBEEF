package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;

public class RobotState {

	private static TouchSensor      touch      = new TouchSensor(SensorPort.S1);
	private static LightSensor      light      = new LightSensor(SensorPort.S2);
	private static UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S3);
	
	public static boolean touched;
	public static int     lightLevel;
	public static float   range;

	public static int     lineLevel;
	public static int     itrsLost = 0;
	
	public static boolean poll() {

		Thread.yield();

		LCD.drawString("Line:      ",2,0);
		LCD.drawInt(lineLevel,5,2,11);

		LCD.drawString("Lost:      ",3,0);
		LCD.drawInt(itrsLost,5,3,11);
		
		LCD.drawString("Trend:      ",4,0);
		
		// TouchSensor
		//
		touched = touch.isPressed();
		LCD.drawString("Touched:   ",5,0);
		if ( touched ) {
			LCD.drawString(" true",5,11);
		} else {
			LCD.drawString("false",5,11);
		}
		
		// LightSensor
		//
		lightLevel = light.getNormalizedLightValue();
		LCD.drawString("Light:      ",6,0);
		LCD.drawInt(lightLevel,5,6,11);
				
		// UltrasonicSensor
		//
		range = ultrasonic.getRange();
		LCD.drawString("Range:      ",7,0);
		LCD.drawInt((int)range,5,7,11);

		if ( !Button.ESCAPE.isPressed() ) {
			return true;
		} else {
			return false;
		}
		
	}

}
