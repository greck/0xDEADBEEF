package edu.nau.spring2012.cs386.DEADBEEF;

public class BoundaryCheck implements Recipe {

	// tunables
	//
	private int varianceAllowed = 3 * Math.abs(RobotState.nonLineLevel - RobotState.lineLevel) / 10;
	
	public boolean execute() {

		if ( RobotState.lineLevelErr < varianceAllowed ) {

			return true;

		} else {

			return false;

		}
		
	}

}
