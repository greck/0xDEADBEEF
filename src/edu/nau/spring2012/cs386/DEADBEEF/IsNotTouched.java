package edu.nau.spring2012.cs386.DEADBEEF;

public class IsNotTouched implements Recipe {

	public boolean execute() {

		return !RobotState.touched;
		
	}

}
