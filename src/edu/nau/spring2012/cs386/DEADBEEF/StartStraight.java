package edu.nau.spring2012.cs386.DEADBEEF;

public class StartStraight implements Recipe {

	public boolean execute() {

		RobotState.pilot.travel(5,true);

		return true;
		
	}

}
