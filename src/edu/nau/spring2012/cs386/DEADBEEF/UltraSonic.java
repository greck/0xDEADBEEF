package edu.nau.spring2012.cs386.DEADBEEF;
import lejos.nxt.*;

public class UltraSonic {
	private UltrasonicSensor sonic;
	
	public UltraSonic() throws Exception {
		sonic = new UltrasonicSensor(SensorPort.S3);
	}
	
	public String toString() {
		return "" + sonic.getDistance();
	}
}
