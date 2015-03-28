package com.mcgamejam;

public class SpikeRobot extends Robot {
	boolean facingRight;
	
	protected SpikeRobot(float x, float y, boolean faceRight) {
		super(x, y);
		facingRight = faceRight;
	}
}
