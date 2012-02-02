package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.*;
import lejos.robotics.*;
import lejos.robotics.objectdetection.*;

public class HW1 {

	public static void main(String[] args) {

		DetectorListeners detectorListener = new DetectorListeners();

		Touch touch = new TouchSensor(SensorPort.S1);
		FeatureDetector startStop = new TouchFeatureDetector(touch);
		startStop.addListener(detectorListener);

		Button.ENTER.waitForPressAndRelease();

	}

}