package edu.nau.spring2012.cs386.DEADBEEF;

public class KeepMoving implements Recipe {

	public boolean execute() {

		if ( RobotState.pilot.isMoving() ) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}

}
