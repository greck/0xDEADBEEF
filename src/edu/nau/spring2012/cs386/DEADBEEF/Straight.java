package edu.nau.spring2012.cs386.DEADBEEF;

public class Straight implements Recipe {

	public boolean execute() {

		RobotState.itrsLost = 0;
		
		if ( !RobotState.hunt && !RobotState.pilot.isMoving() ) {

			RobotState.pilot.travel(5);
			
		} else {
			
			RobotState.hunt = true;
			
		}
		
		return !RobotState.hunt;
		
	}

}
