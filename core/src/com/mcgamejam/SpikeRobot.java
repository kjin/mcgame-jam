package com.mcgamejam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class SpikeRobot extends Robot {
	protected Texture texture;
	protected TextureRegion textureRegion;
	private static int NUM_FRAMES = 8;
	
	protected SpikeRobot(float x, float y, boolean faceRight) {
		super(x, y);
		facingRight = faceRight;
		texture = new Texture("elecwalk.png");
		textureRegion = new TextureRegion(texture);
		size = new Vector2(64, 64);
	}
	
	@Override
	public void update(GameState gameState)
	{
		counter++;
		if (counter == frameChangeFrequency)
		{
			frame++;
			if (frame == NUM_FRAMES)
			{
				frame = 0;
			}
			textureRegion.setRegion(frame * 204, 0, 204, 227);
			counter = 0;
		}
		super.update(gameState);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(textureRegion, position.x, position.y, size.x, size.y);
	}
}
