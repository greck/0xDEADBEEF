package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.addon.CompassSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class CalibrateCompass {

	public static void main(String[] args) {

		CompassSensor compass = new CompassSensor(SensorPort.S1);

		double trackWidth = 13.65;
		
		DifferentialPilot pilot =
				new DifferentialPilot(4f, 4f, trackWidth, Motor.A, Motor.B, false);

		pilot.setRotateSpeed(15);
		pilot.setTravelSpeed(5);

		//
		// calibrate the compass
		//
		
		System.out.println("Press ENTER and ");
		System.out.println("stand back while");
		System.out.println("the compass is  ");
		System.out.println("calibrated.     ");
		System.out.println("                ");
		System.out.println("                ");
		System.out.println("                ");
		System.out.println("                ");

		Button.ENTER.waitForPressAndRelease();

		try { Thread.sleep(750); } catch ( Exception e ) { }
		Sound.beep();
		
		try { Thread.sleep(750); } catch ( Exception e ) { }
		Sound.beep();

		try { Thread.sleep(750); } catch ( Exception e ) { }
		Sound.beep();

		try { Thread.sleep(750); } catch ( Exception e ) { }
		Sound.beep();
		
		LCD.clear();
		LCD.drawString("CALIBRATING_____",0,0);

		compass.startCalibration();

		pilot.rotate(720,false);
		
		compass.stopCalibration();

	}

}
