package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.Motor;
import lejos.robotics.objectdetection.*;

public class DetectorListeners implements FeatureListener {
	
	public void featureDetected(Feature feature, FeatureDetector detector) {

		if(!Motor.A.isMoving()) {

			Motor.A.forward();
			Motor.B.forward();
				
		} else {

			Motor.B.stop();
			Motor.A.stop();

		}

	}
		
}
