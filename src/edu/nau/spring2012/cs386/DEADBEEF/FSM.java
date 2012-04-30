package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;
import lejos.nxt.comm.RConsole;

public class FSM {

	public static void bobbyHenderson() {
	
		Recipe[] r = new Recipe[17];
		r[0] = new Panic();
		r[1] = new Quiesce();
		r[2] = new AdjustHeading();
		r[3] = new StartStraight();
		r[4] = new KeepMoving();
		r[5] = new BoundaryCheck();
		r[6] = new DetectBall();
		r[7] = new CheckColor();
		r[8] = new Avoid();
		r[9] = new IsTouched();
		r[10] = new HuntLeft();
		r[11] = new HuntRight();
		r[12] = new Straighten();
		r[13] = new TurnLeft();
		r[14] = new GoHome();
		r[15] = new IsNotTouched();
		r[16] = new TurnRight();

		int[][] s = new int[109][3];

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
		s[1][1] = 1;
		s[1][2] = 1;

		s[2][0] = 2;
		s[2][1] = 3;
		s[2][2] = 3;

		s[3][0] = 3;
		s[3][1] = 4;
		s[3][2] = 4;

		s[4][0] = 4;
		s[4][1] = 5;
		s[4][2] = 10;

		s[5][0] = 5;
		s[5][1] = 58;
		s[5][2] = 6;

		s[6][0] = 6;
		s[6][1] = 7;
		s[6][2] = 9;

		s[7][0] = 7;
		s[7][1] = 29;
		s[7][2] = 8;

		s[8][0] = 8;
		s[8][1] = 2;
		s[8][2] = 2;

		s[9][0] = 9;
		s[9][1] = 1;
		s[9][2] = 4;

		s[10][0] = 10;
		s[10][1] = 11;
		s[10][2] = 11;

		s[11][0] = 6;
		s[11][1] = 12;
		s[11][2] = 14;

		s[12][0] = 7;
		s[12][1] = 29;
		s[12][2] = 13;

		s[13][0] = 8;
		s[13][1] = 14;
		s[13][2] = 14;

		s[14][0] = 9;
		s[14][1] = 1;
		s[14][2] = 15;

		s[15][0] = 11;
		s[15][1] = 16;
		s[15][2] = 16;

		s[16][0] = 6;
		s[16][1] = 17;
		s[16][2] = 19;

		s[17][0] = 7;
		s[17][1] = 29;
		s[17][2] = 18;

		s[18][0] = 8;
		s[18][1] = 19;
		s[18][2] = 19;

		s[19][0] = 9;
		s[19][1] = 1;
		s[19][2] = 20;

		s[20][0] = 12;
		s[20][1] = 2;
		s[20][2] = 2;

		s[21][0] = 2;
		s[21][1] = 22;
		s[21][2] = 22;

		s[22][0] = 13;
		s[22][1] = 23;
		s[22][2] = 23;

		s[23][0] = 4;
		s[23][1] = 24;
		s[23][2] = 2;

		s[24][0] = 5;
		s[24][1] = 0;
		s[24][2] = 25;

		s[25][0] = 6;
		s[25][1] = 26;
		s[25][2] = 28;

		s[26][0] = 7;
		s[26][1] = 29;
		s[26][2] = 27;

		s[27][0] = 8;
		s[27][1] = 21;
		s[27][2] = 21;

		s[28][0] = 9;
		s[28][1] = 1;
		s[28][2] = 23;

		s[29][0] = 14;
		s[29][1] = 1;
		s[29][2] = 1;

		s[30][0] = 2;
		s[30][1] = 31;
		s[30][2] = 31;

		s[31][0] = 3;
		s[31][1] = 32;
		s[31][2] = 32;

		s[32][0] = 4;
		s[32][1] = 33;
		s[32][2] = 30;

		s[33][0] = 5;
		s[33][1] = 39;
		s[33][2] = 34;

		s[34][0] = 9;
		s[34][1] = 35;
		s[34][2] = 30;

		s[35][0] = 1;
		s[35][1] = 36;
		s[35][2] = 36;

		s[36][0] = 15;
		s[36][1] = 37;
		s[36][2] = 36;

		s[37][0] = 9;
		s[37][1] = 38;
		s[37][2] = 37;

		s[38][0] = 15;
		s[38][1] = 30;
		s[38][2] = 38;

		s[39][0] = 2;
		s[39][1] = 40;
		s[39][2] = 40;

		s[40][0] = 3;
		s[40][1] = 41;
		s[40][2] = 41;

		s[41][0] = 4;
		s[41][1] = 42;
		s[41][2] = 47;

		s[42][0] = 5;
		s[42][1] = 21;
		s[42][2] = 43;

		s[43][0] = 6;
		s[43][1] = 44;
		s[43][2] = 46;

		s[44][0] = 7;
		s[44][1] = 29;
		s[44][2] = 45;

		s[45][0] = 8;
		s[45][1] = 39;
		s[45][2] = 39;

		s[46][0] = 9;
		s[46][1] = 1;
		s[46][2] = 41;

		s[47][0] = 10;
		s[47][1] = 48;
		s[47][2] = 48;

		s[48][0] = 6;
		s[48][1] = 49;
		s[48][2] = 51;

		s[49][0] = 7;
		s[49][1] = 29;
		s[49][2] = 50;

		s[50][0] = 8;
		s[50][1] = 51;
		s[50][2] = 51;

		s[51][0] = 9;
		s[51][1] = 1;
		s[51][2] = 52;

		s[52][0] = 11;
		s[52][1] = 53;
		s[52][2] = 53;

		s[53][0] = 6;
		s[53][1] = 54;
		s[53][2] = 56;

		s[54][0] = 7;
		s[54][1] = 29;
		s[54][2] = 55;

		s[55][0] = 8;
		s[55][1] = 56;
		s[55][2] = 56;

		s[56][0] = 9;
		s[56][1] = 1;
		s[56][2] = 57;

		s[57][0] = 12;
		s[57][1] = 39;
		s[57][2] = 39;

		s[58][0] = 2;
		s[58][1] = 59;
		s[58][2] = 59;

		s[59][0] = 13;
		s[59][1] = 60;
		s[59][2] = 60;

		s[60][0] = 4;
		s[60][1] = 61;
		s[60][2] = 66;

		s[61][0] = 5;
		s[61][1] = 0;
		s[61][2] = 62;

		s[62][0] = 6;
		s[62][1] = 63;
		s[62][2] = 65;

		s[63][0] = 7;
		s[63][1] = 29;
		s[63][2] = 64;

		s[64][0] = 8;
		s[64][1] = 58;
		s[64][2] = 58;

		s[65][0] = 9;
		s[65][1] = 1;
		s[65][2] = 60;

		s[66][0] = 2;
		s[66][1] = 67;
		s[66][2] = 67;

		s[67][0] = 13;
		s[67][1] = 68;
		s[67][2] = 68;

		s[68][0] = 4;
		s[68][1] = 69;
		s[68][2] = 74;

		s[69][0] = 5;
		s[69][1] = 0;
		s[69][2] = 70;

		s[70][0] = 6;
		s[70][1] = 71;
		s[70][2] = 73;

		s[71][0] = 7;
		s[71][1] = 29;
		s[71][2] = 72;

		s[72][0] = 8;
		s[72][1] = 66;
		s[72][2] = 66;

		s[73][0] = 9;
		s[73][1] = 1;
		s[73][2] = 68;

		s[74][0] = 2;
		s[74][1] = 75;
		s[74][2] = 75;

		s[75][0] = 3;
		s[75][1] = 76;
		s[75][2] = 76;

		s[76][0] = 4;
		s[76][1] = 77;
		s[76][2] = 82;

		s[77][0] = 5;
		s[77][1] = 93;
		s[77][2] = 78;

		s[78][0] = 6;
		s[78][1] = 79;
		s[78][2] = 81;

		s[79][0] = 7;
		s[79][1] = 29;
		s[79][2] = 80;

		s[80][0] = 8;
		s[80][1] = 74;
		s[80][2] = 74;

		s[81][0] = 9;
		s[81][1] = 1;
		s[81][2] = 76;

		s[82][0] = 10;
		s[82][1] = 83;
		s[82][2] = 83;

		s[83][0] = 6;
		s[83][1] = 84;
		s[83][2] = 86;

		s[84][0] = 7;
		s[84][1] = 29;
		s[84][2] = 85;

		s[85][0] = 8;
		s[85][1] = 86;
		s[85][2] = 86;

		s[86][0] = 9;
		s[86][1] = 1;
		s[86][2] = 87;

		s[87][0] = 11;
		s[87][1] = 88;
		s[87][2] = 88;

		s[88][0] = 6;
		s[88][1] = 89;
		s[88][2] = 91;

		s[89][0] = 7;
		s[89][1] = 29;
		s[89][2] = 90;

		s[90][0] = 8;
		s[90][1] = 91;
		s[90][2] = 91;

		s[91][0] = 9;
		s[91][1] = 1;
		s[91][2] = 92;

		s[92][0] = 12;
		s[92][1] = 74;
		s[92][2] = 74;

		s[93][0] = 2;
		s[93][1] = 94;
		s[93][2] = 94;

		s[94][0] = 16;
		s[94][1] = 95;
		s[94][2] = 95;

		s[95][0] = 4;
		s[95][1] = 96;
		s[95][2] = 101;

		s[96][0] = 5;
		s[96][1] = 0;
		s[96][2] = 97;

		s[97][0] = 6;
		s[97][1] = 98;
		s[97][2] = 100;

		s[98][0] = 7;
		s[98][1] = 29;
		s[98][2] = 99;

		s[99][0] = 8;
		s[99][1] = 93;
		s[99][2] = 93;

		s[100][0] = 9;
		s[100][1] = 1;
		s[100][2] = 95;

		s[101][0] = 2;
		s[101][1] = 102;
		s[101][2] = 102;

		s[102][0] = 16;
		s[102][1] = 103;
		s[102][2] = 103;

		s[103][0] = 4;
		s[103][1] = 104;
		s[103][2] = 2;

		s[104][0] = 5;
		s[104][1] = 0;
		s[104][2] = 105;

		s[105][0] = 6;
		s[105][1] = 106;
		s[105][2] = 108;

		s[106][0] = 7;
		s[106][1] = 29;
		s[106][2] = 107;

		s[107][0] = 8;
		s[107][1] = 101;
		s[107][2] = 101;

		s[108][0] = 9;
		s[108][1] = 1;
		s[108][2] = 103;
		
		// free and clear to navigate!
		//
		Sound.twoBeeps();

		// INITIAL STATE
		//
		int state = 35;

		while ( RobotState.poll() ) {

			switch(state) {

				case  0:
					LCD.drawString("A_PANIC_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("A_PANIC_________"); }
					break;
				case  1:
					LCD.drawString("A_VERY_EMERGENCY",0,0);
					if ( Robot.DEBUG ) { RConsole.println("A_VERY_EMERGENCY"); }
					break;
				case  2:
					LCD.drawString("B_ADJUST_HEADING",0,0);
					if ( Robot.DEBUG ) { RConsole.println("B_ADJUST_HEADING"); }
					break;
				case  3:
					LCD.drawString("B_START_STRAIGHT",0,0);
					if ( Robot.DEBUG ) { RConsole.println("B_START_STRAIGHT"); }
					break;
				case  4:
					LCD.drawString("B_KEEP_STRAIGHT_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("B_KEEP_STRAIGHT_"); }
					break;
				case  5:
					LCD.drawString("B_BOUNDARY_CHECK",0,0);
					if ( Robot.DEBUG ) { RConsole.println("B_BOUNDARY_CHECK"); }
					break;
				case  6:
					LCD.drawString("B_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("B_DETECT_BALL___"); }
					break;
				case  7:
					LCD.drawString("B_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("B_CHECK_COLOR___"); }
					break;
				case  8:
					LCD.drawString("B_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("B_AVOID_________"); }
					break;
				case  9:
					LCD.drawString("B_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("B_CHECK_STOP_DN_"); }
					break;
				case 10:
					LCD.drawString("C_HUNT__________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("C_HUNT__________"); }
					break;
				case 11:
					LCD.drawString("C_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("C_DETECT_BALL___"); }
					break;
				case 12:
					LCD.drawString("C_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("C_CHECK_COLOR___"); }
					break;
				case 13:
					LCD.drawString("C_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("C_AVOID_________"); }
					break;
				case 14:
					LCD.drawString("C_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("C_CHECK_STOP_DN_"); }
					break;
				case 15:
					LCD.drawString("D_HUNT__________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("D_HUNT__________"); }
					break;
				case 16:
					LCD.drawString("D_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("D_DETECT_BALL___"); }
					break;
				case 17:
					LCD.drawString("D_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("D_CHECK_COLOR___"); }
					break;
				case 18:
					LCD.drawString("D_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("D_AVOID_________"); }
					break;
				case 19:
					LCD.drawString("D_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("D_CHECK_STOP_DN_"); }
					break;
				case 20:
					LCD.drawString("D_STRAIGHTEN____",0,0);
					if ( Robot.DEBUG ) { RConsole.println("D_STRAIGHTEN____"); }
					break;
				case 21:
					LCD.drawString("E_ADJUST_HEADING",0,0);
					if ( Robot.DEBUG ) { RConsole.println("E_ADJUST_HEADING"); }
					break;
				case 22:
					LCD.drawString("E_START_TURN____",0,0);
					if ( Robot.DEBUG ) { RConsole.println("E_START_TURN____"); }
					break;
				case 23:
					LCD.drawString("E_KEEP_TURNING__",0,0);
					if ( Robot.DEBUG ) { RConsole.println("E_KEEP_TURNING__"); }
					break;
				case 24:
					LCD.drawString("E_BOUNDARY_CHECK",0,0);
					if ( Robot.DEBUG ) { RConsole.println("E_BOUNDARY_CHECK"); }
					break;
				case 25:
					LCD.drawString("E_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("E_DETECT_BALL___"); }
					break;
				case 26:
					LCD.drawString("E_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("E_CHECK_COLOR___"); }
					break;
				case 27:
					LCD.drawString("E_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("E_AVOID_________"); }
					break;
				case 28:
					LCD.drawString("E_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("E_CHECK_STOP_DN_"); }
					break;
				case 29:
					LCD.drawString("F_GO_HOME_______",0,0);
					if ( Robot.DEBUG ) { RConsole.println("F_GO_HOME_______"); }
					break;
				case 30:
					LCD.drawString("G_ADJUST_HEADING",0,0);
					if ( Robot.DEBUG ) { RConsole.println("G_ADJUST_HEADING"); }
					break;
				case 31:
					LCD.drawString("G_START_STRAIGHT",0,0);
					if ( Robot.DEBUG ) { RConsole.println("G_START_STRAIGHT"); }
					break;
				case 32:
					LCD.drawString("G_KEEP_STRAIGHT_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("G_KEEP_STRAIGHT_"); }
					break;
				case 33:
					LCD.drawString("G_BOUNDARY_CHECK",0,0);
					if ( Robot.DEBUG ) { RConsole.println("G_BOUNDARY_CHECK"); }
					break;
				case 34:
					LCD.drawString("G_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("G_CHECK_STOP_DN_"); }
					break;
				case 35:
					LCD.drawString("H_QUIESCE_______",0,0);
					if ( Robot.DEBUG ) { RConsole.println("H_QUIESCE_______"); }
					break;
				case 36:
					LCD.drawString("H_WAIT_STOP_UP__",0,0);
					if ( Robot.DEBUG ) { RConsole.println("H_WAIT_STOP_UP__"); }
					break;
				case 37:
					LCD.drawString("H_WAIT_START_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("H_WAIT_START_DN_"); }
					break;
				case 38:
					LCD.drawString("H_WAIT_START_UP_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("H_WAIT_START_UP_"); }
					break;
				case 39:
					LCD.drawString("I_ADJUST_HEADING",0,0);
					if ( Robot.DEBUG ) { RConsole.println("I_ADJUST_HEADING"); }
					break;
				case 40:
					LCD.drawString("I_START_STRAIGHT",0,0);
					if ( Robot.DEBUG ) { RConsole.println("I_START_STRAIGHT"); }
					break;
				case 41:
					LCD.drawString("I_KEEP_STRAIGHT_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("I_KEEP_STRAIGHT_"); }
					break;
				case 42:
					LCD.drawString("I_BOUNDARY_CHECK",0,0);
					if ( Robot.DEBUG ) { RConsole.println("I_BOUNDARY_CHECK"); }
					break;
				case 43:
					LCD.drawString("I_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("I_DETECT_BALL___"); }
					break;
				case 44:
					LCD.drawString("I_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("I_CHECK_COLOR___"); }
					break;
				case 45:
					LCD.drawString("I_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("I_AVOID_________"); }
					break;
				case 46:
					LCD.drawString("I_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("I_CHECK_STOP_DN_"); }
					break;
				case 47:
					LCD.drawString("J_HUNT__________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("J_HUNT__________"); }
					break;
				case 48:
					LCD.drawString("J_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("J_DETECT_BALL___"); }
					break;
				case 49:
					LCD.drawString("J_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("J_CHECK_COLOR___"); }
					break;
				case 50:
					LCD.drawString("J_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("J_AVOID_________"); }
					break;
				case 51:
					LCD.drawString("J_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("J_CHECK_STOP_DN_"); }
					break;
				case 52:
					LCD.drawString("K_HUNT__________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("K_HUNT__________"); }
					break;
				case 53:
					LCD.drawString("K_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("K_DETECT_BALL___"); }
					break;
				case 54:
					LCD.drawString("K_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("K_CHECK_COLOR___"); }
					break;
				case 55:
					LCD.drawString("K_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("K_AVOID_________"); }
					break;
				case 56:
					LCD.drawString("K_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("K_CHECK_STOP_DN_"); }
					break;
				case 57:
					LCD.drawString("K_STRAIGHTEN____",0,0);
					if ( Robot.DEBUG ) { RConsole.println("K_STRAIGHTEN____"); }
					break;
				case 58:
					LCD.drawString("L_ADJUST_HEADING",0,0);
					if ( Robot.DEBUG ) { RConsole.println("L_ADJUST_HEADING"); }
					break;
				case 59:
					LCD.drawString("L_START_TURN____",0,0);
					if ( Robot.DEBUG ) { RConsole.println("L_START_TURN____"); }
					break;
				case 60:
					LCD.drawString("L_KEEP_TURNING__",0,0);
					if ( Robot.DEBUG ) { RConsole.println("L_KEEP_TURNING__"); }
					break;
				case 61:
					LCD.drawString("L_BOUNDARY_CHECK",0,0);
					if ( Robot.DEBUG ) { RConsole.println("L_BOUNDARY_CHECK"); }
					break;
				case 62:
					LCD.drawString("L_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("L_DETECT_BALL___"); }
					break;
				case 63:
					LCD.drawString("L_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("L_CHECK_COLOR___"); }
					break;
				case 64:
					LCD.drawString("L_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("L_AVOID_________"); }
					break;
				case 65:
					LCD.drawString("L_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("L_CHECK_STOP_DN_"); }
					break;
				case 66:
					LCD.drawString("M_ADJUST_HEADING",0,0);
					if ( Robot.DEBUG ) { RConsole.println("M_ADJUST_HEADING"); }
					break;
				case 67:
					LCD.drawString("M_START_TURN____",0,0);
					if ( Robot.DEBUG ) { RConsole.println("M_START_TURN____"); }
					break;
				case 68:
					LCD.drawString("M_KEEP_TURNING__",0,0);
					if ( Robot.DEBUG ) { RConsole.println("M_KEEP_TURNING__"); }
					break;
				case 69:
					LCD.drawString("M_BOUNDARY_CHECK",0,0);
					if ( Robot.DEBUG ) { RConsole.println("M_BOUNDARY_CHECK"); }
					break;
				case 70:
					LCD.drawString("M_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("M_DETECT_BALL___"); }
					break;
				case 71:
					LCD.drawString("M_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("M_CHECK_COLOR___"); }
					break;
				case 72:
					LCD.drawString("M_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("M_AVOID_________"); }
					break;
				case 73:
					LCD.drawString("M_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("M_CHECK_STOP_DN_"); }
					break;
				case 74:
					LCD.drawString("N_ADJUST_HEADING",0,0);
					if ( Robot.DEBUG ) { RConsole.println("N_ADJUST_HEADING"); }
					break;
				case 75:
					LCD.drawString("N_START_STRAIGHT",0,0);
					if ( Robot.DEBUG ) { RConsole.println("N_START_STRAIGHT"); }
					break;
				case 76:
					LCD.drawString("N_KEEP_STRAIGHT_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("N_KEEP_STRAIGHT_"); }
					break;
				case 77:
					LCD.drawString("N_BOUNDARY_CHECK",0,0);
					if ( Robot.DEBUG ) { RConsole.println("N_BOUNDARY_CHECK"); }
					break;
				case 78:
					LCD.drawString("N_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("N_DETECT_BALL___"); }
					break;
				case 79:
					LCD.drawString("N_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("N_CHECK_COLOR___"); }
					break;
				case 80:
					LCD.drawString("N_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("N_AVOID_________"); }
					break;
				case 81:
					LCD.drawString("N_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("N_CHECK_STOP_DN_"); }
					break;
				case 82:
					LCD.drawString("O_HUNT__________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("O_HUNT__________"); }
					break;
				case 83:
					LCD.drawString("O_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("O_DETECT_BALL___"); }
					break;
				case 84:
					LCD.drawString("O_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("O_CHECK_COLOR___"); }
					break;
				case 85:
					LCD.drawString("O_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("O_AVOID_________"); }
					break;
				case 86:
					LCD.drawString("O_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("O_CHECK_STOP_DN_"); }
					break;
				case 87:
					LCD.drawString("P_HUNT__________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("P_HUNT__________"); }
					break;
				case 88:
					LCD.drawString("P_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("P_DETECT_BALL___"); }
					break;
				case 89:
					LCD.drawString("P_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("P_CHECK_COLOR___"); }
					break;
				case 90:
					LCD.drawString("P_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("P_AVOID_________"); }
					break;
				case 91:
					LCD.drawString("P_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("P_CHECK_STOP_DN_"); }
					break;
				case 92:
					LCD.drawString("P_STRAIGHTEN____",0,0);
					if ( Robot.DEBUG ) { RConsole.println("P_STRAIGHTEN____"); }
					break;
				case 93:
					LCD.drawString("Q_ADJUST_HEADING",0,0);
					if ( Robot.DEBUG ) { RConsole.println("Q_ADJUST_HEADING"); }
					break;
				case 94:
					LCD.drawString("Q_START_TURN____",0,0);
					if ( Robot.DEBUG ) { RConsole.println("Q_START_TURN____"); }
					break;
				case 95:
					LCD.drawString("Q_KEEP_TURNING__",0,0);
					if ( Robot.DEBUG ) { RConsole.println("Q_KEEP_TURNING__"); }
					break;
				case 96:
					LCD.drawString("Q_BOUNDARY_CHECK",0,0);
					if ( Robot.DEBUG ) { RConsole.println("Q_BOUNDARY_CHECK"); }
					break;
				case 97:
					LCD.drawString("Q_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("Q_DETECT_BALL___"); }
					break;
				case 98:
					LCD.drawString("Q_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("Q_CHECK_COLOR___"); }
					break;
				case 99:
					LCD.drawString("Q_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("Q_AVOID_________"); }
					break;
				case 100:
					LCD.drawString("Q_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("Q_CHECK_STOP_DN_"); }
					break;
				case 101:
					LCD.drawString("R_ADJUST_HEADING",0,0);
					if ( Robot.DEBUG ) { RConsole.println("R_ADJUST_HEADING"); }
					break;
				case 102:
					LCD.drawString("R_START_TURN____",0,0);
					if ( Robot.DEBUG ) { RConsole.println("R_START_TURN____"); }
					break;
				case 103:
					LCD.drawString("R_KEEP_TURNING__",0,0);
					if ( Robot.DEBUG ) { RConsole.println("R_KEEP_TURNING__"); }
					break;
				case 104:
					LCD.drawString("R_BOUNDARY_CHECK",0,0);
					if ( Robot.DEBUG ) { RConsole.println("R_BOUNDARY_CHECK"); }
					break;
				case 105:
					LCD.drawString("R_DETECT_BALL___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("R_DETECT_BALL___"); }
					break;
				case 106:
					LCD.drawString("R_CHECK_COLOR___",0,0);
					if ( Robot.DEBUG ) { RConsole.println("R_CHECK_COLOR___"); }
					break;
				case 107:
					LCD.drawString("R_AVOID_________",0,0);
					if ( Robot.DEBUG ) { RConsole.println("R_AVOID_________"); }
					break;
				case 108:
					LCD.drawString("R_CHECK_STOP_DN_",0,0);
					if ( Robot.DEBUG ) { RConsole.println("R_CHECK_STOP_DN_"); }
					break;
				
			}
			
			if( r[s[state][0]].execute() ) {
				state = s[state][1];
			} else {
				state = s[state][2];
			}

		}
	}
}
