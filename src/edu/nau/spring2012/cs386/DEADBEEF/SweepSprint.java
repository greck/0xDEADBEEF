package edu.nau.spring2012.cs386.DEADBEEF;

public class SweepSprint implements Recipe {
	//currently still blocking
	public boolean execute() {
		RobotState.pilot.travel(-1.5*RobotState.trackWidth);
		RobotState.pilot.arc(RobotState.trackWidth, 90);
		return true;
	}

}
