package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;

public class DrivingMacros {

	private static NXTRegulatedMotor leftMotor  = Motor.B;
	private static NXTRegulatedMotor rightMotor = Motor.A;

	public static int baseSpeed = 250;
	
	public static void start() {
		RobotState.moving = true;
		leftMotor.setSpeed(baseSpeed);
		leftMotor.forward();
		rightMotor.setSpeed(baseSpeed);
		rightMotor.forward();
	}
	
	public static void stop() {
		RobotState.moving = false;
		leftMotor.stop(true);
		rightMotor.stop(true);
	}
	
	public static void turnLeft() {
		leftMotor.setSpeed(baseSpeed/2);
		leftMotor.backward();
		rightMotor.setSpeed(baseSpeed/2);
		rightMotor.forward();
	}
	
	public static void turnRight() {
		leftMotor.setSpeed(baseSpeed/2);
		leftMotor.forward();
		rightMotor.setSpeed(baseSpeed/2);
		rightMotor.backward();
	}
}
