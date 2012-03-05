package edu.nau.spring2012.cs386.DEADBEEF;

public class DetectBall implements Recipe {

	public boolean execute() {

		if ( RobotState.range < 30 ) {
			return true;
		} else {
			return false;
		}
		
	}

}
