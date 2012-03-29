package edu.nau.spring2012.cs386.DEADBEEF;

public class HuntRight implements Recipe {

	public boolean execute() {

		RobotState.pilot.rotate(-30,false);
		
		return true;
		
	}

}
