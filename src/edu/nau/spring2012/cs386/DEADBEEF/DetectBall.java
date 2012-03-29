package edu.nau.spring2012.cs386.DEADBEEF;

public class DetectBall implements Recipe {

	public boolean execute() {

		if ( RobotState.range < 25 ) {

			RobotState.pilot.travel(15,false);
			return true;

		} else {

			return false;

		}
		
	}

}
