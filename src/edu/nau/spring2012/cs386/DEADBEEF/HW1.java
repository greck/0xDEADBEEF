package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;

public class HW1 {

	public static void main(String[] args) {

		System.out.println("Press ENTER");
		System.out.println("to begin");
		System.out.println("driving demo.");
		Button.ENTER.waitForPressAndRelease();

		DrivingMacros.setMotors(Motor.A,Motor.B);

		DrivingMacros.speedUp();
		try { Thread.sleep(500); } catch (InterruptedException e) { };
		DrivingMacros.speedUp();
		try { Thread.sleep(500); } catch (InterruptedException e) { };
		DrivingMacros.speedUp();
		try { Thread.sleep(500); } catch (InterruptedException e) { };
		DrivingMacros.speedUp();
		try { Thread.sleep(500); } catch (InterruptedException e) { };
		DrivingMacros.speedUp();
		try { Thread.sleep(500); } catch (InterruptedException e) { };
		DrivingMacros.speedUp();
		try { Thread.sleep(500); } catch (InterruptedException e) { };
		DrivingMacros.speedUp();
		try { Thread.sleep(500); } catch (InterruptedException e) { };
		
		DrivingMacros.turnLeft();
		try { Thread.sleep(500); } catch (InterruptedException e) { };
		DrivingMacros.turnLeft();
		try { Thread.sleep(500); } catch (InterruptedException e) { };
		DrivingMacros.turnLeft();
		try { Thread.sleep(500); } catch (InterruptedException e) { };
		
	}

}