package edu.nau.spring2012.cs386.DEADBEEF;

import java.lang.String;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.CompassSensor;
import lejos.nxt.comm.RConsole;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Pose;

public class CompassDifferentialPilot extends DifferentialPilot {

	private CompassSensor compass = new CompassSensor(SensorPort.S1);

	private OdometryPoseProvider poseProvider = new OdometryPoseProvider(this);

	public CompassDifferentialPilot(double wheelDiameter, double trackWidth,
			RegulatedMotor leftMotor, RegulatedMotor rightMotor) {
		super(wheelDiameter, trackWidth, leftMotor, rightMotor);
		// TODO Auto-generated constructor stub
	}

	public CompassDifferentialPilot(double wheelDiameter, double trackWidth,
			RegulatedMotor leftMotor, RegulatedMotor rightMotor, boolean reverse) {
		super(wheelDiameter, trackWidth, leftMotor, rightMotor, reverse);
		// TODO Auto-generated constructor stub
	}

	public CompassDifferentialPilot(double leftWheelDiameter,
			double rightWheelDiameter, double trackWidth,
			RegulatedMotor leftMotor, RegulatedMotor rightMotor, boolean reverse) {
		super(leftWheelDiameter, rightWheelDiameter, trackWidth, leftMotor,
				rightMotor, reverse);
		// TODO Auto-generated constructor stub
	}

	public void zeroCompass() {
		
		compass.resetCartesianZero();
		
	}

	public void adjustHeading() {
		
		if ( Robot.DEBUG ) {
			RConsole.println("adjustHeading()");
		}

		Pose  curPose = poseProvider.getPose();
		float curHeading;
		String buf;

		do {

			curHeading = compass.getDegreesCartesian();

			if ( curHeading > 180 ) {
				curHeading -= 360;
			}
			
			buf = "cur=";
			buf += (int)curHeading;
			buf += ", pose=";
			buf += (int)curPose.getHeading();
			RConsole.println(buf);

			try { Thread.sleep(250); } catch( Exception e) { }

			if ( curHeading - curPose.getHeading() > 0 ) {
				
				super.rotate(-1,false);
				
			} else {
				
				super.rotate(1,false);
				
			}

		} while ( Math.abs( (int)curHeading - (int)curPose.getHeading() ) > 0 );

		poseProvider.setPose(curPose);
		
	}

	public Pose getPose() {
	
		return poseProvider.getPose();
	
	}
	
	public void arc(double radius, double angle, boolean immediateReturn) {
		
		// assert no immediate return
		//
		if ( immediateReturn ) { System.exit(-1); }

		if ( Robot.DEBUG ) {
			RConsole.println("arc()");
		}
		
		super.arc(radius,angle,immediateReturn);
		adjustHeading();
		
	}

	public void rotate(double angle, boolean immediateReturn) {

		// assert no immediate return
		//
		if ( immediateReturn ) { System.exit(-1); }
		
		if ( Robot.DEBUG ) {
			RConsole.println("rotate()");
		}

		Pose  curPose = poseProvider.getPose();
		float heading = curPose.getHeading();

		heading += angle;

		if ( heading < -180 ) {
			heading += 360;
		}

		if ( heading > 180 ) {
			heading -= 360;
		}
		
		super.rotate(angle,immediateReturn);

		curPose = poseProvider.getPose();
		curPose.setHeading(heading);
		poseProvider.setPose(curPose);
		
		adjustHeading();
		
	}
	
}
