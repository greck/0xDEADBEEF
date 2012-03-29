package edu.nau.spring2012.cs386.DEADBEEF;

public class Straighten implements Recipe {

	public boolean execute() {

		RobotState.pilot.rotate(15,false);
		RobotState.hunt = false;
		
		return true;
		
	}

}
