package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;

public class DrivingMacros {

	private static NXTRegulatedMotor leftMotor  = Motor.B;
	private static NXTRegulatedMotor rightMotor = Motor.A;

	public static int baseSpeed = 400;
	
	public static void start() {
		RobotState.moving = true;
		leftMotor.setSpeed(baseSpeed);
		leftMotor.forward();
		rightMotor.setSpeed(baseSpeed);
		rightMotor.forward();
	}
	
	public static void stop() {
		RobotState.moving = false;
		leftMotor.stop();
		rightMotor.stop();
	}
	
	public static void turnLeft() {
		leftMotor.setSpeed(2*baseSpeed/3);
		leftMotor.backward();
		rightMotor.setSpeed(2*baseSpeed/3);
		rightMotor.forward();
	}
	
	public static void turnRight() {
		leftMotor.setSpeed(2*baseSpeed/3);
		leftMotor.forward();
		rightMotor.setSpeed(2*baseSpeed/3);
		rightMotor.backward();
	}
}
