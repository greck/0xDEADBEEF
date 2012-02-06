package edu.nau.spring2012.cs386.DEADBEEF;

public class LineTest implements Recipe {

	// tunables
	//
	private int varianceAllowed = 50;
	
	public boolean execute() {

		if ( RobotState.lineLevelErr < varianceAllowed ) {
			return true;
		} else {
			return false;
		}
		
	}

}
