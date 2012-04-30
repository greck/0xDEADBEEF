package edu.nau.spring2012.cs386.DEADBEEF;

public class BoundaryCheck implements Recipe {

	// tunables
	//
	private int varianceAllowed = 3 * Math.abs(RobotState.nonLineLevel - RobotState.lineLevel) / 10;
	
	public boolean execute() {

		if ( RobotState.lineLevelErr < varianceAllowed ) {

			RobotState.pilot.travel(-1.5*RobotState.trackWidth);    

			return true;

		} else {

			return false;

		}
		
	}

}
