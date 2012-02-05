package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;

public class DrivingMacros {

	private static NXTRegulatedMotor leftMotor  = Motor.B;
	private static NXTRegulatedMotor rightMotor = Motor.A;

	public static void start() {
		RobotState.moving = true;
		leftMotor.setSpeed(200);
		leftMotor.forward();
		rightMotor.setSpeed(200);
		rightMotor.forward();
	}
	
	public static void stop() {
		RobotState.moving = false;
		leftMotor.stop();
		rightMotor.stop();
	}
	
	public static void turnLeft() {
		leftMotor.setSpeed(100);
		leftMotor.backward();
		rightMotor.setSpeed(100);
		rightMotor.forward();
	}
	
	public static void turnRight() {
		leftMotor.setSpeed(100);
		leftMotor.forward();
		rightMotor.setSpeed(100);
		rightMotor.backward();
	}
}
