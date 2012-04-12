package edu.nau.spring2012.cs386.DEADBEEF;

public class CheckColor implements Recipe {

	public boolean execute() {
		
		if ( RobotState.redLevel > RobotState.blueLevel ) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}

}
