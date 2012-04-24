package edu.nau.spring2012.cs386.DEADBEEF;

import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorSensorHT;
import lejos.nxt.comm.RConsole;

public class ColorDriver {

	public static void main(String[] args) {

		RConsole.open();

		ColorSensorHT color = new ColorSensorHT(SensorPort.S4);
		int redLevel, blueLevel;
		String buf;

		while ( true ) {
			
			try { Thread.sleep(250); } catch ( Exception e ) { }
			
			redLevel = color.getColor().getRed();
			blueLevel = color.getColor().getBlue();

			buf  = "red=";
			buf += redLevel;
			buf += ", blue=";
			buf += blueLevel;
	
			RConsole.println(buf);

		}

	}

}
