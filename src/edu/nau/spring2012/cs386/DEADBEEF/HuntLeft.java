package edu.nau.spring2012.cs386.DEADBEEF;

public class HuntLeft implements Recipe {

	public boolean execute() {

		RobotState.pilot.rotate(15,false);
		
		return true;
		
	}

}
