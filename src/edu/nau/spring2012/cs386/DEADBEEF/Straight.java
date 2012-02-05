package edu.nau.spring2012.cs386.DEADBEEF;

public class Straight implements Recipe {

	public boolean execute() {

		RobotState.itrsLost = 0;
		
		DrivingMacros.straightenUp();
		DrivingMacros.speedUp();
		
		return true;
		
	}

}
