package edu.nau.spring2012.cs386.DEADBEEF;

public class HitBoundary implements Recipe {

	public boolean execute() {

		RobotState.pilot.travel(-2*RobotState.trackWidth);	

		return true;
		
	}

}
