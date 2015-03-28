package com.mcgamejam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Robot {
	private Texture robotTexture;
	private Vector2 position;
	
	Robot()
	{
		robotTexture = new Texture("badlogic.jpg");
		position = new Vector2();
	}
	
	void update(GameState gameState)
	{
		position.x = 300 + 100 * (float)Math.cos(gameState.getGameTime());
		position.y = 300 + 100 * (float)Math.sin(gameState.getGameTime());
	}
	
	void render(SpriteBatch batch)
	{
		batch.draw(robotTexture, position.x, position.y);
	}
}
