package com.mcgamejam;

import com.badlogic.gdx.graphics.Texture;

public class SpikeRobot extends Robot {
	boolean facingRight;
	
	protected SpikeRobot(float x, float y, boolean faceRight) {
		super(x, y);
		facingRight = faceRight;
		robotTexture = new Texture("treadmill.png");
	}
}
