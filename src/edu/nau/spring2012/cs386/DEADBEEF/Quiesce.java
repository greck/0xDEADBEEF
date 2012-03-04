package edu.nau.spring2012.cs386.DEADBEEF;

public class Quiesce implements Recipe {

	public boolean execute() {

		Robot.pilot.stop();
		return true;
		
	}

}
