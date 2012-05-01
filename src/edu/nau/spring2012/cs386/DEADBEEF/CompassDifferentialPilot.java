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

	public void debugHeadings(String tag, int curHeading, int poseHeading) {
		
		String buf;
		
		buf = tag;
		buf += "cur=";
		buf += curHeading;
		buf += ", pose=";
		buf += poseHeading;
		RConsole.println(buf);
		
	}
	
	public void adjustHeading() {
		
		Pose  curPose = poseProvider.getPose();
		int curHeading, poseHeading;

		do {

			curHeading  = Math.round(compass.getDegreesCartesian());
			poseHeading = Math.round(curPose.getHeading());
			
			if ( curHeading > 180 ) {
				curHeading -= 360;
			}
			
			if ( Robot.DEBUG ) { debugHeadings("adjH",curHeading,poseHeading); }
			
			int deviation = curHeading - poseHeading;
			
			if ( deviation > 0 ) {
				
				super.rotate(-1,false);
				
			} else {
				
				super.rotate(1,false);
				
			}
			
		} while ( Math.abs( curHeading - poseHeading ) > 0 );

		poseProvider.setPose(curPose);
		
	}

	public Pose getPose() {
	
		return poseProvider.getPose();
	
	}
	
	public void arc(double radius, double angle, boolean immediateReturn) {
		
		Pose  curPose = poseProvider.getPose();
		int heading = Math.round(curPose.getHeading());

		heading += angle;

		if ( heading < -180 ) {
			heading += 360;
		}

		if ( heading >= 180 ) {
			heading -= 360;
		}
		
		super.arc(radius,angle,immediateReturn);

		curPose = poseProvider.getPose();
		curPose.setHeading(heading);
		poseProvider.setPose(curPose);
		
	}

	public void rotate(double angle, boolean immediateReturn) {

		Pose  curPose = poseProvider.getPose();
		int heading = Math.round(curPose.getHeading());

		heading += angle;

		if ( heading < -180 ) {
			heading += 360;
		}

		if ( heading >= 180 ) {
			heading -= 360;
		}
		
		super.rotate(angle,immediateReturn);

		curPose = poseProvider.getPose();
		curPose.setHeading(heading);
		poseProvider.setPose(curPose);
		
	}
	
}
