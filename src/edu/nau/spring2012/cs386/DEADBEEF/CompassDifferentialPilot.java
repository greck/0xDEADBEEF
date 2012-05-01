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

	public void debugHeadings(String tag, int curHeading, int poseHeading, int deviation) {
		
		String buf;
		
		buf = tag;
		buf += "cur=";
		buf += curHeading;
		buf += ", pose=";
		buf += poseHeading;
		buf += ", deviation=";
		buf += deviation;
		RConsole.println(buf);
		
	}

	private void reduceHeading() {
		
		if ( RobotState.desiredHeading <= -180 ) {
			RobotState.desiredHeading += 360;
		}

		if ( RobotState.desiredHeading > 180 ) {
			RobotState.desiredHeading -= 360;
		}
		
	}
	
	public void adjustHeading() {
		
		int curHeading, deviation;
		Pose       curPose = poseProvider.getPose();
		int desiredHeading = RobotState.desiredHeading;
		
		do {

			curHeading = Math.round(compass.getDegreesCartesian());
			
			while ( curHeading > 180 ) {
				curHeading -= 360;
			}
			
			deviation = ( ( ( ( desiredHeading + 179 ) - ( curHeading + 179 ) ) + 360 ) % 360 );

			while ( deviation > 180 ) {
				deviation -= 360;
			}

			if ( Robot.DEBUG ) { debugHeadings("adjHeading:",curHeading,desiredHeading,deviation); }

			RobotState.pilot.rotate(deviation,false);
			RobotState.desiredHeading = desiredHeading;
			
		} while ( Math.abs( curHeading - RobotState.desiredHeading ) > 0 );

		poseProvider.setPose(curPose);
		
	}

	public Pose getPose() {
	
		return poseProvider.getPose();
	
	}
	
	public void arc(double radius, double angle, boolean immediateReturn) {
		
		RobotState.desiredHeading += angle;
		reduceHeading();
		
		super.arc(radius,angle,immediateReturn);

	}

	public void rotate(double angle, boolean immediateReturn) {

		RobotState.desiredHeading += angle;
		reduceHeading();
		
		super.rotate(angle,immediateReturn);
		
	}
	
}
