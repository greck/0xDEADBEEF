package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {

	public static final double trackWidth = 14.5;

	public static DifferentialPilot pilot =
	  new DifferentialPilot(5.6f, 5.6f, trackWidth, Motor.A, Motor.B, false);

	public static void main(String[] args) {

		pilot.setRotateSpeed(45);
		pilot.setTravelSpeed(20);
		
		try { Thread.sleep(500); } catch ( Exception e ) { }

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
		
		Recipe[] r = new Recipe[8];
		r[0] = new Quiesce();
		r[1] = new IsNotTouched();
		r[2] = new IsTouched();
		r[3] = new DetectBall();
		r[4] = new AvoidBall();
		r[5] = new LineTest();
		r[6] = new Straight();
		r[7] = new Sweep();

		int[][] s = new int[10][3];

//		     +----------+
//		     |          |
//		     v          v
		s[0][0] = 0; // recipe to execute
		s[0][1] = 1; // next state if recipe return true
		s[0][2] = 1; // next state if recipe returns false
//		  ^       ^
//		  |       |
//		  |       this is the state to transition to
//		  |		
//		  this is the current state

		s[1][0] = 1;
		s[1][1] = 2;
		s[1][2] = 1;

		s[2][0] = 2;
		s[2][1] = 3;
		s[2][2] = 2;

		s[3][0] = 1;
		s[3][1] = 4;
		s[3][2] = 3;

		s[4][0] = 2;
		s[4][1] = 0;
		s[4][2] = 5;

		s[5][0] = 3;
		s[5][1] = 6;
		s[5][2] = 7;

		s[6][0] = 4;
		s[6][1] = 7;
		s[6][2] = 7;

		s[7][0] = 5;
		s[7][1] = 8;
		s[7][2] = 9;

		s[8][0] = 7;
		s[8][1] = 4;
		s[8][2] = 4;

		s[9][0] = 6;
		s[9][1] = 4;
		s[9][2] = 4;

		// free and clear to navigate!
		//
		Sound.twoBeeps();
		
		int state = 0;

		long start = System.currentTimeMillis();
		
		while ( RobotState.poll() ) {

			switch(state) {
				case 0: LCD.drawString("QUIESCE_________",0,0); break;
				case 1: LCD.drawString("QTOUCHTESTUP1___",0,0); break;
				case 2: LCD.drawString("QTOUCHTESTDOWN__",0,0); break;
				case 3: LCD.drawString("QTOUCHTESTUP2___",0,0); break;
				case 4: LCD.drawString("TOUCHTESTDOWN___",0,0); break;
				case 5: LCD.drawString("DETECTBALL______",0,0); break;
				case 6: LCD.drawString("AVOIDBALL_______",0,0); break;
				case 7: LCD.drawString("BOUNDARYCHECK___",0,0); break;
				case 8: LCD.drawString("SWEEP___________",0,0); break;
				case 9: LCD.drawString("STRAIGHT________",0,0); break;
			}
			
			if( r[s[state][0]].execute() ) {
				state = s[state][1];
			} else {
				state = s[state][2];
			}
		
		}

		long stop = System.currentTimeMillis();

		float timePerItr = ( (float)stop - (float)start ) / (float)RobotState.totalItrs;

		LCD.clear();

		System.out.println("average");
		System.out.println("milliseconds");
		System.out.println("per");
		System.out.println("main loop:");
		System.out.println(timePerItr);

		Button.ENTER.waitForPressAndRelease();
		
	}

}