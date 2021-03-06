package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;
import lejos.nxt.addon.ColorSensorHT;
import lejos.nxt.comm.RConsole;

public class RobotState {

	private static LightSensor      light      = new LightSensor(SensorPort.S2);
	private static UltrasonicSensor ultrasonic = new UltrasonicSensor(SensorPort.S3);
	private static ColorSensorHT    color      = new ColorSensorHT(SensorPort.S4);

	public static double trackWidth = 13.65;
	//
	// measured to outside = 14.0 mm
	// measure from inside = 13.3 mm
	
	public static int     desiredHeading = 0;
	
	public static boolean touched;
	public static int     lightLevel;
	public static float   range;
	public static int     redLevel;
	public static int     blueLevel;
	public static int     greenLevel;
	
	public static int     totalItrs = 0;
	public static int     lineLevel;
	public static int  nonLineLevel;
	public static int  lineLevelErr;

	public static CompassDifferentialPilot pilot =
			  new CompassDifferentialPilot(4.32f, 4.32f, (float)trackWidth, Motor.A, Motor.B, false);

	public static void preCalibrateLight() {
		light.setFloodlight(true);
	}

	public static void calibrateLight() {
		lightLevel = light.getNormalizedLightValue();
	}

	public static void setContinuousMode() {
		ultrasonic.continuous();
	}

	public static void zeroCompass() {
		pilot.zeroCompass();
	}
	
	public static boolean poll() {

		totalItrs++;
		
		String buf;
		
		buf = "poll: desiredHeading=";
		buf += desiredHeading;
		RConsole.println(buf);
		
		LCD.drawString("L        R      ",0,1);
		LCD.drawInt(Motor.A.getSpeed(),5,2,1);
		LCD.drawInt(Motor.B.getSpeed(),5,11,1);
		
		LCD.drawString("LineLvl:   ",0,2);
		LCD.drawInt(lineLevel,5,11,2);

		// TouchSensor
		//
		touched = Button.ENTER.isPressed();
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
		lineLevelErr = Math.abs(lightLevel - lineLevel);
		LCD.drawInt(lineLevelErr,4,12,4);
				
		// UltrasonicSensor
		//
		range = ultrasonic.getRange();
		LCD.drawString("Range:      ",0,7);
		LCD.drawInt((int)range,5,11,7);
		
		// ColorSensor
		//
		redLevel = color.getColor().getRed();
		blueLevel = color.getColor().getBlue();
		greenLevel = color.getColor().getGreen();
		
		if ( !Button.ESCAPE.isPressed() ) {
			return true;
		} else {
			return false;
		}
		
	}

}
