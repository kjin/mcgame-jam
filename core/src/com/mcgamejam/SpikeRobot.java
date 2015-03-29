package com.mcgamejam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpikeRobot extends Robot {
	boolean facingRight;
	
	protected SpikeRobot(float x, float y, boolean faceRight) {
		super(x, y);
		facingRight = faceRight;
		robotTexture = new Texture("electrician.png");
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(robotTexture, position.x, position.y, size.x, size.y);
	}
}
