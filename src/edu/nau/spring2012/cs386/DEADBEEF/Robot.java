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
		
		Recipe[] r = new Recipe[15];
		r[0] = new Quiesce();
		r[1] = new IsNotTouched();
		r[2] = new IsTouched();
		r[3] = new AdjustHeading();
		r[4] = new StartStraight();
		r[5] = new KeepStraight();
		r[6] = new BoundaryCheck();
		r[7] = new DetectBall();
		r[8] = new CheckColor();
		r[9] = new Avoid();
		r[10] = new HuntLeft();
		r[11] = new HuntRight();
		r[12] = new Straighten();
		r[13] = new Sweep();
		r[14] = new GoHome();

		int[][] s = new int[29][3];

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
		s[5][1] = 6;
		s[5][2] = 6;

		s[6][0] = 5;
		s[6][1] = 7;
		s[6][2] = 12;

		s[7][0] = 6;
		s[7][1] = 23;
		s[7][2] = 8;

		s[8][0] = 7;
		s[8][1] = 9;
		s[8][2] = 11;

		s[9][0] = 8;
		s[9][1] = 28;
		s[9][2] = 10;

		s[10][0] = 9;
		s[10][1] = 4;
		s[10][2] = 4;

		s[11][0] = 2;
		s[11][1] = 0;
		s[11][2] = 6;

		s[12][0] = 10;
		s[12][1] = 13;
		s[12][2] = 13;

		s[13][0] = 7;
		s[13][1] = 14;
		s[13][2] = 16;

		s[14][0] = 8;
		s[14][1] = 28;
		s[14][2] = 15;

		s[15][0] = 9;
		s[15][1] = 16;
		s[15][2] = 16;

		s[16][0] = 2;
		s[16][1] = 0;
		s[16][2] = 17;

		s[17][0] = 11;
		s[17][1] = 18;
		s[17][2] = 18;

		s[18][0] = 7;
		s[18][1] = 19;
		s[18][2] = 21;

		s[19][0] = 8;
		s[19][1] = 28;
		s[19][2] = 20;

		s[20][0] = 9;
		s[20][1] = 21;
		s[20][2] = 21;

		s[21][0] = 2;
		s[21][1] = 0;
		s[21][2] = 22;

		s[22][0] = 12;
		s[22][1] = 4;
		s[22][2] = 4;

		s[23][0] = 13;
		s[23][1] = 24;
		s[23][2] = 24;

		s[24][0] = 7;
		s[24][1] = 25;
		s[24][2] = 27;

		s[25][0] = 8;
		s[25][1] = 28;
		s[25][2] = 26;

		s[26][0] = 9;
		s[26][1] = 23;
		s[26][2] = 23;

		s[27][0] = 2;
		s[27][1] = 0;
		s[27][2] = 4;

		s[28][0] = 14;
		s[28][1] = 0;
		s[28][2] = 0;
		
		// free and clear to navigate!
		//
		Sound.twoBeeps();
		
		int state = 0;

		long start = System.currentTimeMillis();
		
		while ( RobotState.poll() ) {

			switch(state) {

			case  0:
				LCD.drawString("Q_QUIESCE_______",0,0);
				if ( DEBUG ) { RConsole.println("Q_QUIESCE_______"); }
				break;
			case  1:
				LCD.drawString("Q_WAIT_STOP_UP__",0,0);
				if ( DEBUG ) { RConsole.println("Q_WAIT_STOP_UP__"); }
				break;
			case  2:
				LCD.drawString("Q_WAIT_START_DN_",0,0);
				if ( DEBUG ) { RConsole.println("Q_WAIT_START_DN_"); }
				break;
			case  3:
				LCD.drawString("Q_WAIT_START_UP_",0,0);
				if ( DEBUG ) { RConsole.println("Q_WAIT_START_UP_"); }
				break;
			case  4:
				LCD.drawString("S_ADJUST_HEADING",0,0);
				if ( DEBUG ) { RConsole.println("S_ADJUST_HEADING"); }
				break;
			case  5:
				LCD.drawString("S_START_STRAIGHT",0,0);
				if ( DEBUG ) { RConsole.println("S_START_STRAIGHT"); }
				break;
			case  6:
				LCD.drawString("S_KEEP_STRAIGHT_",0,0);
				if ( DEBUG ) { RConsole.println("S_KEEP_STRAIGHT_"); }
				break;
			case  7:
				LCD.drawString("S_BOUNDARY_CHECK",0,0);
				if ( DEBUG ) { RConsole.println("S_BOUNDARY_CHECK"); }
				break;
			case  8:
				LCD.drawString("S_DETECT_BALL___",0,0);
				if ( DEBUG ) { RConsole.println("S_DETECT_BALL___"); }
				break;
			case  9:
				LCD.drawString("S_CHECK_COLOR___",0,0);
				if ( DEBUG ) { RConsole.println("S_CHECK_COLOR___"); }
				break;
			case 10:
				LCD.drawString("S_AVOID_________",0,0);
				if ( DEBUG ) { RConsole.println("S_AVOID_________"); }
				break;
			case 11:
				LCD.drawString("S_CHECK_STOP_DN_",0,0);
				if ( DEBUG ) { RConsole.println("S_CHECK_STOP_DN_"); }
				break;
			case 12:
				LCD.drawString("L_HUNT__________",0,0);
				if ( DEBUG ) { RConsole.println("L_HUNT__________"); }
				break;
			case 13:
				LCD.drawString("L_DETECT_BALL___",0,0);
				if ( DEBUG ) { RConsole.println("L_DETECT_BALL___"); }
				break;
			case 14:
				LCD.drawString("L_CHECK_COLOR___",0,0);
				if ( DEBUG ) { RConsole.println("L_CHECK_COLOR___"); }
				break;
			case 15:
				LCD.drawString("L_AVOID_________",0,0);
				if ( DEBUG ) { RConsole.println("L_AVOID_________"); }
				break;
			case 16:
				LCD.drawString("L_CHECK_STOP_DN_",0,0);
				if ( DEBUG ) { RConsole.println("L_CHECK_STOP_DN_"); }
				break;
			case 17:
				LCD.drawString("R_HUNT__________",0,0);
				if ( DEBUG ) { RConsole.println("R_HUNT__________"); }
				break;
			case 18:
				LCD.drawString("R_DETECT_BALL___",0,0);
				if ( DEBUG ) { RConsole.println("R_DETECT_BALL___"); }
				break;
			case 19:
				LCD.drawString("R_CHECK_COLOR___",0,0);
				if ( DEBUG ) { RConsole.println("R_CHECK_COLOR___"); }
				break;
			case 20:
				LCD.drawString("R_AVOID_________",0,0);
				if ( DEBUG ) { RConsole.println("R_AVOID_________"); }
				break;
			case 21:
				LCD.drawString("R_CHECK_STOP_DN_",0,0);
				if ( DEBUG ) { RConsole.println("R_CHECK_STOP_DN_"); }
				break;
			case 22:
				LCD.drawString("R_STRAIGHTEN____",0,0);
				if ( DEBUG ) { RConsole.println("R_STRAIGHTEN____"); }
				break;
			case 23:
				LCD.drawString("P_SEARCH_PATTERN",0,0);
				if ( DEBUG ) { RConsole.println("P_SEARCH_PATTERN"); }
				break;
			case 24:
				LCD.drawString("P_DETECT_BALL___",0,0);
				if ( DEBUG ) { RConsole.println("P_DETECT_BALL___"); }
				break;
			case 25:
				LCD.drawString("P_CHECK_COLOR___",0,0);
				if ( DEBUG ) { RConsole.println("P_CHECK_COLOR___"); }
				break;
			case 26:
				LCD.drawString("P_AVOID_________",0,0);
				if ( DEBUG ) { RConsole.println("P_AVOID_________"); }
				break;
			case 27:
				LCD.drawString("P_CHECK_STOP_DN_",0,0);
				if ( DEBUG ) { RConsole.println("P_CHECK_STOP_DN_"); }
				break;
			case 28:
				LCD.drawString("H_GO_HOME_______",0,0);
				if ( DEBUG ) { RConsole.println("H_GO_HOME_______"); }
				break;
				
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
