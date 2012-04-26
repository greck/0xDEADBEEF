package edu.nau.spring2012.cs386.DEADBEEF;

public class AdjustHeading implements Recipe {

	public boolean execute() {

		RobotState.pilot.adjustHeading();

		return true;
		
	}

}
