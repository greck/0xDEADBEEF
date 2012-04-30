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
		r[1] = new AdjustHeading();
		r[2] = new StartStraight();
		r[3] = new KeepStraight();
		r[4] = new BoundaryCheck();
		r[5] = new DetectBall();
		r[6] = new CheckColor();
		r[7] = new Avoid();
		r[8] = new IsTouched();
		r[9] = new HuntLeft();
		r[10] = new HuntRight();
		r[11] = new Straighten();
		r[12] = new Sweep();
		r[13] = new GoHome();
		r[14] = new IsNotTouched();

		int[][] s = new int[35][3];

//		     +----------+
//		     |          |
//		     v          v
		s[0][0] = 0; // recipe to execute
		s[0][1] = 0; // next state if recipe return true
		s[0][2] = 0; // next state if recipe returns false
//		  ^       ^
//		  |       |
//		  |       this is the state to transition to
//		  |		
//		  this is the current state

		s[1][0] = 1;
		s[1][1] = 2;
		s[1][2] = 2;

		s[2][0] = 2;
		s[2][1] = 3;
		s[2][2] = 3;

		s[3][0] = 3;
		s[3][1] = 4;
		s[3][2] = 9;

		s[4][0] = 4;
		s[4][1] = 20;
		s[4][2] = 5;

		s[5][0] = 5;
		s[5][1] = 6;
		s[5][2] = 8;

		s[6][0] = 6;
		s[6][1] = 25;
		s[6][2] = 7;

		s[7][0] = 7;
		s[7][1] = 1;
		s[7][2] = 1;

		s[8][0] = 8;
		s[8][1] = 0;
		s[8][2] = 3;

		s[9][0] = 9;
		s[9][1] = 10;
		s[9][2] = 10;

		s[10][0] = 5;
		s[10][1] = 11;
		s[10][2] = 13;

		s[11][0] = 6;
		s[11][1] = 25;
		s[11][2] = 12;

		s[12][0] = 7;
		s[12][1] = 13;
		s[12][2] = 13;

		s[13][0] = 8;
		s[13][1] = 0;
		s[13][2] = 14;

		s[14][0] = 10;
		s[14][1] = 15;
		s[14][2] = 15;

		s[15][0] = 5;
		s[15][1] = 16;
		s[15][2] = 18;

		s[16][0] = 6;
		s[16][1] = 25;
		s[16][2] = 17;

		s[17][0] = 7;
		s[17][1] = 18;
		s[17][2] = 18;

		s[18][0] = 8;
		s[18][1] = 0;
		s[18][2] = 19;

		s[19][0] = 11;
		s[19][1] = 1;
		s[19][2] = 1;

		s[20][0] = 12;
		s[20][1] = 21;
		s[20][2] = 21;

		s[21][0] = 5;
		s[21][1] = 22;
		s[21][2] = 24;

		s[22][0] = 6;
		s[22][1] = 25;
		s[22][2] = 23;

		s[23][0] = 7;
		s[23][1] = 20;
		s[23][2] = 20;

		s[24][0] = 8;
		s[24][1] = 0;
		s[24][2] = 1;

		s[25][0] = 13;
		s[25][1] = 0;
		s[25][2] = 0;

		s[26][0] = 1;
		s[26][1] = 27;
		s[26][2] = 27;

		s[27][0] = 2;
		s[27][1] = 28;
		s[27][2] = 28;

		s[28][0] = 3;
		s[28][1] = 29;
		s[28][2] = 26;

		s[29][0] = 4;
		s[29][1] = 1;
		s[29][2] = 30;

		s[30][0] = 8;
		s[30][1] = 31;
		s[30][2] = 26;

		s[31][0] = 0;
		s[31][1] = 32;
		s[31][2] = 32;

		s[32][0] = 14;
		s[32][1] = 33;
		s[32][2] = 32;

		s[33][0] = 8;
		s[33][1] = 34;
		s[33][2] = 33;

		s[34][0] = 14;
		s[34][1] = 26;
		s[34][2] = 34;
		
		// free and clear to navigate!
		//
		Sound.twoBeeps();
		
		int state = 31;

		long start = System.currentTimeMillis();
		
		while ( RobotState.poll() ) {

			switch(state) {

			case  0:
				LCD.drawString("A_VERY_EMERGENCY",0,0);
				if ( DEBUG ) { RConsole.println("A_VERY_EMERGENCY"); }
				break;
			case  1:
				LCD.drawString("B_ADJUST_HEADING",0,0);
				if ( DEBUG ) { RConsole.println("B_ADJUST_HEADING"); }
				break;
			case  2:
				LCD.drawString("B_START_STRAIGHT",0,0);
				if ( DEBUG ) { RConsole.println("B_START_STRAIGHT"); }
				break;
			case  3:
				LCD.drawString("B_KEEP_STRAIGHT_",0,0);
				if ( DEBUG ) { RConsole.println("B_KEEP_STRAIGHT_"); }
				break;
			case  4:
				LCD.drawString("B_BOUNDARY_CHECK",0,0);
				if ( DEBUG ) { RConsole.println("B_BOUNDARY_CHECK"); }
				break;
			case  5:
				LCD.drawString("B_DETECT_BALL___",0,0);
				if ( DEBUG ) { RConsole.println("B_DETECT_BALL___"); }
				break;
			case  6:
				LCD.drawString("B_CHECK_COLOR___",0,0);
				if ( DEBUG ) { RConsole.println("B_CHECK_COLOR___"); }
				break;
			case  7:
				LCD.drawString("B_AVOID_________",0,0);
				if ( DEBUG ) { RConsole.println("B_AVOID_________"); }
				break;
			case  8:
				LCD.drawString("B_CHECK_STOP_DN_",0,0);
				if ( DEBUG ) { RConsole.println("B_CHECK_STOP_DN_"); }
				break;
			case  9:
				LCD.drawString("C_HUNT__________",0,0);
				if ( DEBUG ) { RConsole.println("C_HUNT__________"); }
				break;
			case 10:
				LCD.drawString("C_DETECT_BALL___",0,0);
				if ( DEBUG ) { RConsole.println("C_DETECT_BALL___"); }
				break;
			case 11:
				LCD.drawString("C_CHECK_COLOR___",0,0);
				if ( DEBUG ) { RConsole.println("C_CHECK_COLOR___"); }
				break;
			case 12:
				LCD.drawString("C_AVOID_________",0,0);
				if ( DEBUG ) { RConsole.println("C_AVOID_________"); }
				break;
			case 13:
				LCD.drawString("C_CHECK_STOP_DN_",0,0);
				if ( DEBUG ) { RConsole.println("C_CHECK_STOP_DN_"); }
				break;
			case 14:
				LCD.drawString("D_HUNT__________",0,0);
				if ( DEBUG ) { RConsole.println("D_HUNT__________"); }
				break;
			case 15:
				LCD.drawString("D_DETECT_BALL___",0,0);
				if ( DEBUG ) { RConsole.println("D_DETECT_BALL___"); }
				break;
			case 16:
				LCD.drawString("D_CHECK_COLOR___",0,0);
				if ( DEBUG ) { RConsole.println("D_CHECK_COLOR___"); }
				break;
			case 17:
				LCD.drawString("D_AVOID_________",0,0);
				if ( DEBUG ) { RConsole.println("D_AVOID_________"); }
				break;
			case 18:
				LCD.drawString("D_CHECK_STOP_DN_",0,0);
				if ( DEBUG ) { RConsole.println("D_CHECK_STOP_DN_"); }
				break;
			case 19:
				LCD.drawString("D_STRAIGHTEN____",0,0);
				if ( DEBUG ) { RConsole.println("D_STRAIGHTEN____"); }
				break;
			case 20:
				LCD.drawString("E_SEARCH_PATTERN",0,0);
				if ( DEBUG ) { RConsole.println("E_SEARCH_PATTERN"); }
				break;
			case 21:
				LCD.drawString("E_DETECT_BALL___",0,0);
				if ( DEBUG ) { RConsole.println("E_DETECT_BALL___"); }
				break;
			case 22:
				LCD.drawString("E_CHECK_COLOR___",0,0);
				if ( DEBUG ) { RConsole.println("E_CHECK_COLOR___"); }
				break;
			case 23:
				LCD.drawString("E_AVOID_________",0,0);
				if ( DEBUG ) { RConsole.println("E_AVOID_________"); }
				break;
			case 24:
				LCD.drawString("E_CHECK_STOP_DN_",0,0);
				if ( DEBUG ) { RConsole.println("E_CHECK_STOP_DN_"); }
				break;
			case 25:
				LCD.drawString("F_GO_HOME_______",0,0);
				if ( DEBUG ) { RConsole.println("F_GO_HOME_______"); }
				break;
			case 26:
				LCD.drawString("G_ADJUST_HEADING",0,0);
				if ( DEBUG ) { RConsole.println("G_ADJUST_HEADING"); }
				break;
			case 27:
				LCD.drawString("G_START_STRAIGHT",0,0);
				if ( DEBUG ) { RConsole.println("G_START_STRAIGHT"); }
				break;
			case 28:
				LCD.drawString("G_KEEP_STRAIGHT_",0,0);
				if ( DEBUG ) { RConsole.println("G_KEEP_STRAIGHT_"); }
				break;
			case 29:
				LCD.drawString("G_BOUNDARY_CHECK",0,0);
				if ( DEBUG ) { RConsole.println("G_BOUNDARY_CHECK"); }
				break;
			case 30:
				LCD.drawString("G_CHECK_STOP_DN_",0,0);
				if ( DEBUG ) { RConsole.println("G_CHECK_STOP_DN_"); }
				break;
			case 31:
				LCD.drawString("H_QUIESCE_______",0,0);
				if ( DEBUG ) { RConsole.println("H_QUIESCE_______"); }
				break;
			case 32:
				LCD.drawString("H_WAIT_STOP_UP__",0,0);
				if ( DEBUG ) { RConsole.println("H_WAIT_STOP_UP__"); }
				break;
			case 33:
				LCD.drawString("H_WAIT_START_DN_",0,0);
				if ( DEBUG ) { RConsole.println("H_WAIT_START_DN_"); }
				break;
			case 34:
				LCD.drawString("H_WAIT_START_UP_",0,0);
				if ( DEBUG ) { RConsole.println("H_WAIT_START_UP_"); }
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
