package edu.nau.spring2012.cs386.DEADBEEF;

public class LineTest implements Recipe {

	// tunables
	//
	private int varianceAllowed = 100;
	
	public boolean execute() {

		if ( Math.abs(RobotState.lightLevel - RobotState.lineLevel) < varianceAllowed ) {
			return true;
		} else {
			return false;
		}
		
	}

}
