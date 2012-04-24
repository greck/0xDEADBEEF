package edu.nau.spring2012.cs386.DEADBEEF;

public class HuntLeft implements Recipe {

	public boolean execute() {

		RobotState.pilot.rotate(20,false);
		
		return true;
		
	}

}
