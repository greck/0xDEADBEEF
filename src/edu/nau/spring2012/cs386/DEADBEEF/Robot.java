package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;

public class Robot {

    public static final boolean DEBUG = true;
	
	public static void main(String[] args) {

		RobotState.pilot.setRotateSpeed(45);
		RobotState.pilot.setTravelSpeed(20);
		
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
		
		Recipe[] r = new Recipe[10];
		r[0] = new Quiesce();
		r[1] = new IsNotTouched();
		r[2] = new IsTouched();
		r[3] = new Straight();
		r[4] = new BoundaryCheck();
		r[5] = new DetectBall();
		r[6] = new CheckColor();
		r[7] = new Capture();
		r[8] = new Avoid();
		r[9] = new Sweep();

		int[][] s = new int[17][3];

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

		s[4][0] = 3;
		s[4][1] = 5;
		s[4][2] = 5;

		s[5][0] = 4;
		s[5][1] = 11;
		s[5][2] = 6;

		s[6][0] = 5;
		s[6][1] = 7;
		s[6][2] = 10;

		s[7][0] = 6;
		s[7][1] = 8;
		s[7][2] = 9;

// This is the S_CAPTURE state;
// if we get here, the behavior
// is currently undefined.
//		s[8][0] = 7;
//		s[8][1] = ;
//		s[8][2] = ;

		s[9][0] = 8;
		s[9][1] = 4;
		s[9][2] = 4;

		s[10][0] = 2;
		s[10][1] = 0;
		s[10][2] = 4;

		s[11][0] = 9;
		s[11][1] = 12;
		s[11][2] = 12;

		s[12][0] = 5;
		s[12][1] = 13;
		s[12][2] = 16;

		s[13][0] = 6;
		s[13][1] = 14;
		s[13][2] = 15;

// This is the H_GO_HOME state;
// if we get here, the behavior
// is currently undefined.
//
//		s[14][0] = 7;
//		s[14][1] = ;
//		s[14][2] = ;

		s[15][0] = 8;
		s[15][1] = 11;
		s[15][2] = 11;

		s[16][0] = 2;
		s[16][1] = 0;
		s[16][2] = 11;

		// free and clear to navigate!
		//
		Sound.twoBeeps();
		
		int state = 0;

		long start = System.currentTimeMillis();
		
		while ( RobotState.poll() ) {

			switch(state) {

				case  0: LCD.drawString("Q_QUIESCE_______",0,0); break;
				case  1: LCD.drawString("Q_WAIT_STOP_UP__",0,0); break;
				case  2: LCD.drawString("Q_WAIT_START_DN_",0,0); break;
				case  3: LCD.drawString("Q_WAIT_START_UP_",0,0); break;
				case  4: LCD.drawString("S_STRAIGHT______",0,0); break;
				case  5: LCD.drawString("S_BOUNDARY_CHECK",0,0); break;
				case  6: LCD.drawString("S_DETECT_BALL___",0,0); break;
				case  7: LCD.drawString("S_CHECK_COLOR___",0,0); break;
				case  8: LCD.drawString("S_CAPTURE_______",0,0); break;
				case  9: LCD.drawString("S_AVOID_________",0,0); break;
				case 10: LCD.drawString("S_CHECK_STOP_DN_",0,0); break;
				case 11: LCD.drawString("P_SEARCH_PATTERN",0,0); break;
				case 12: LCD.drawString("P_DETECT_BALL___",0,0); break;
				case 13: LCD.drawString("P_CHECK_COLOR___",0,0); break;
				case 14: LCD.drawString("P_CAPTURE_______",0,0); break;
				case 15: LCD.drawString("P_AVOID_________",0,0); break;
				case 16: LCD.drawString("P_CHECK_STOP_DN_",0,0); break;

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