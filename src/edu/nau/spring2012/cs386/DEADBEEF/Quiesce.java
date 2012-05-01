package edu.nau.spring2012.cs386.DEADBEEF;

public class Quiesce implements Recipe {

	public boolean execute() {

		RobotState.pilot.stop();
		
		return true;
		
	}

}
