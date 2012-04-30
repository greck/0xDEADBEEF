package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;
import lejos.nxt.comm.RConsole;

public class Robot {

    public static final boolean DEBUG = true;

	public static void main(String[] args) {

		if ( DEBUG ) {
			RConsole.open();
		}
		
		try { Thread.sleep(500); } catch ( Exception e ) { }

		//
		// make sure the claw is closed
		//
		System.out.println("Ensure that the ");
		System.out.println("claw is in the  ");
		System.out.println("closed position,");
		System.out.println("and then press  ");
		System.out.println("ENTER.          ");
		System.out.println("                ");
		System.out.println("                ");
		System.out.println("                ");

		Button.ENTER.waitForPressAndRelease();

		//
		// set base pilot variables
		//
		RobotState.pilot.setRotateSpeed(45);
		RobotState.pilot.setTravelSpeed(10);
		
		//
		// calibrate the light sensor
		//
		RobotState.preCalibrateLight();
		
		System.out.println("Please place the");
		System.out.println("robot so that it");
		System.out.println("can 'see' the   ");
		System.out.println("BOUNDARY LINE,  ");
		System.out.println("and then press  ");
		System.out.println("ENTER.          ");
		System.out.println("                ");
		System.out.println("                ");

		Button.ENTER.waitForPressAndRelease();

		LCD.clear();
		LCD.drawString("CALIBRATING_____",0,0);

		for(int i=0; i<4; i++) {
			RobotState.calibrateLight();
			RobotState.lineLevel += RobotState.lightLevel;
			try { Thread.sleep(250); } catch( Exception e) { }
		}
		RobotState.lineLevel /= 4;

		System.out.println("Please place the");
		System.out.println("robot so that it");
		System.out.println("can 'see' THE   ");
		System.out.println("BACKGROUND      ");
		System.out.println("BEHIND THE      ");
		System.out.println("BOUNDARY LINE,  ");
		System.out.println("and then press  ");
		System.out.println("ENTER.          ");

		Button.ENTER.waitForPressAndRelease();

		LCD.clear();
		LCD.drawString("CALIBRATING_____",0,0);

		for(int i=0; i<4; i++) {
			RobotState.calibrateLight();
			RobotState.nonLineLevel += RobotState.lightLevel;
			try { Thread.sleep(250); } catch( Exception e) { }
		}
		RobotState.nonLineLevel /= 4;

		//
		// make sure the claw is closed
		//
		System.out.println("Place the robot ");
		System.out.println("in the starting ");
		System.out.println("location and    ");
		System.out.println("then press      ");
		System.out.println("ENTER.          ");
		System.out.println("                ");
		System.out.println("                ");
		System.out.println("                ");

		Button.ENTER.waitForPressAndRelease();

		RobotState.zeroCompass();
		
		//
		// open the claw
		//
		Motor.C.rotate(-80);

		long start = System.currentTimeMillis();

		FSM.bobbyHenderson();

		long stop = System.currentTimeMillis();

		float timePerItr = ( (float)stop - (float)start ) / (float)RobotState.totalItrs;

		LCD.clear();

		System.out.println("average         ");
		System.out.println("milliseconds    ");
		System.out.println("per mail loop:  ");
		System.out.println(timePerItr);

		Button.ENTER.waitForPressAndRelease();

		if ( DEBUG ) {
			RConsole.close();
		}
		
	}

}
