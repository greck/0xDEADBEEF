package edu.nau.spring2012.cs386.DEADBEEF;

import java.lang.String;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.CompassSensor;
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

	private void adjustHeading() {
		
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
			System.out.println(buf);

			if ( curHeading - curPose.getHeading() > 0 ) {
				
				super.rotate(-1,false);
				
			} else {
				
				super.rotate(1,false);
				
			}

		} while ( Math.abs( curHeading - curPose.getHeading() ) > 1.5 );

		poseProvider.setPose(curPose);
		
	}

	public Pose getPose() {
	
		return poseProvider.getPose();
	
	}
	
	public void arc(double radius, double angle, boolean immediateReturn) {
		
		super.arc(radius,angle,immediateReturn);
		adjustHeading();
		
	}

	public void rotate(double angle, boolean immediateReturn) {
		
		super.rotate(angle,immediateReturn);
		adjustHeading();
		
	}
	
	public void travel(double distance, boolean immediateReturn) {
		
		super.travel(distance,immediateReturn);
		adjustHeading();
		
	}
	
}
