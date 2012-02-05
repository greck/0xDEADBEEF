package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;

public class DrivingMacros {

	private static NXTRegulatedMotor leftMotor  = Motor.A;
	private static NXTRegulatedMotor rightMotor = Motor.B;
	
	private static int minSpeed      =  10;
	private static int maxSpeed      = 100;
	private static int fullIncrement =  10;
	private static int halfIncrement =   5;

	public static void setMotors(NXTRegulatedMotor left, NXTRegulatedMotor right) {
		
		leftMotor  = left;
		rightMotor = right;

	}
	
	public static void straightenUp() {
		
		int leftSpeed  = leftMotor.getSpeed();
		int rightSpeed = rightMotor.getSpeed();
		
		if ( leftSpeed > rightSpeed ) {
			rightMotor.setSpeed(leftSpeed);			
		} else {
			leftMotor.setSpeed(rightSpeed);
		}
		
	}

	public static boolean slowDown() {

		int leftSpeed  = leftMotor.getSpeed();
		int rightSpeed = rightMotor.getSpeed();
		
		if ( leftSpeed >= minSpeed + fullIncrement &&
				rightSpeed >= minSpeed + fullIncrement ) {
			
			leftMotor.setSpeed(leftSpeed - fullIncrement);
			leftMotor.setSpeed(leftSpeed - fullIncrement);
			return true;
			
		} else {
			
			return false;
			
		}
		
	}

	public static boolean speedUp() {

		int leftSpeed  = leftMotor.getSpeed();
		int rightSpeed = rightMotor.getSpeed();
		
		if ( leftSpeed <= maxSpeed - fullIncrement &&
				rightSpeed <= maxSpeed - fullIncrement ) {
			
			leftMotor.setSpeed(leftSpeed + fullIncrement);
			leftMotor.setSpeed(leftSpeed + fullIncrement);
			return true;
			
		} else {
			
			return false;

		}
		
	}

	public static void turnLeft() {

		int leftSpeed  = leftMotor.getSpeed();
		int rightSpeed = rightMotor.getSpeed();

		leftMotor.setSpeed(leftSpeed - halfIncrement);
		rightMotor.setSpeed(rightSpeed + halfIncrement);
		
	}

	public static void turnRight() {

		int leftSpeed  = leftMotor.getSpeed();
		int rightSpeed = rightMotor.getSpeed();

		leftMotor.setSpeed(leftSpeed + halfIncrement);
		rightMotor.setSpeed(rightSpeed - halfIncrement);
		
	}

	public static void stopImmediately() {
		
		leftMotor.stop();
		rightMotor.stop();
		
	}
	
	public static void stopGently() {

		while(slowDown()) {
			Thread.yield();
		}
		stopImmediately();
		
	}
	
}
