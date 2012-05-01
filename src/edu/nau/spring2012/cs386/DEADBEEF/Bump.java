package edu.nau.spring2012.cs386.DEADBEEF;

public class Bump implements Recipe {

	public boolean execute() {

		RobotState.pilot.travel(2.5,false);

		return true;
		
	}

}
