package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;

public class HW1 {
	
	public static void main(String[] args) {
		
		LCD.clear();
		System.out.println("Please place the");
		System.out.println("robot so that it");
		System.out.println("can 'see' the");
		System.out.println("line, and then");
		System.out.println("press ENTER");
		System.out.println(" ");
		System.out.println(" ");
		System.out.println(" ");
		Button.ENTER.waitForPressAndRelease();

		LCD.drawString("CALIBRATING_____",0,0);
		for(int i=0; i<3; i++) {
			RobotState.poll();
			RobotState.lineLevel += RobotState.lightLevel;
			try { Thread.sleep(250); }
			  catch (InterruptedException e) { }
		}
		RobotState.lineLevel /= 3;

		LCD.clear();
		
		Recipe[] r = new Recipe[6];
		r[0] = new Quiesce();
		r[1] = new IsNotTouched();
		r[2] = new IsTouched();
		r[3] = new LineTest();
		r[4] = new Straight();
		r[5] = new Remediate();

		int[][] s = new int[8][3];

//           +----------+
//           |          |
//       	 v          v	
		s[0][0] = 0; // recipe to execute
		s[0][1] = 1; // next state if recipe returns true
		s[0][2] = 1; // next state if recipe returns false
//        ^       ^
//        |       |
//        |       this is the state to transition to
//        |		
//        this is the current state
		
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
		s[6][1] = 4;
		s[6][2] = 4;

		s[7][0] = 5;
		s[7][1] = 4;
		s[7][2] = 4;
		
		DrivingMacros.stop();
		
		int state = 0;
		
		while ( RobotState.poll() ) {

			switch(state) {
				case 0: LCD.drawString("QUIESCE_________",0,0); break;
				case 1: LCD.drawString("QTOUCHTESTUP1___",0,0); break;
				case 2: LCD.drawString("QTOUCHTESTDOWN__",0,0); break;
				case 3: LCD.drawString("QTOUCHTESTUP2___",0,0); break;
				case 4: LCD.drawString("TOUCHTESTDOWN___",0,0); break;
				case 5: LCD.drawString("LINETEST________",0,0); break;
				case 6: LCD.drawString("STRAIGHT________",0,0); break;
				case 7: LCD.drawString("REMEDIATE_______",0,0); break;
			}
			
			if( r[s[state][0]].execute() ) {
				state = s[state][1];
			} else {
				state = s[state][2];
			}
		
		}
		
	}

}