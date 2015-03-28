package com.mcgamejam;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameState {
	private Robot robot;
	private float gameTime;
	
	final static float DELTA_TIME = 1.0f/60.0f; //assuming 60FPS
	
	GameState()
	{
		// init things here
		robot = new Robot();
	}
	
	void update()
	{
		// put your update logic here
		robot.update(this);
		gameTime += DELTA_TIME;
	}
	
	void render(SpriteBatch batch)
	{
		// put your rendering logic here
		robot.render(batch);
	}
	
	float getGameTime()
	{
		return gameTime;
	}
}
