package com.mcgamejam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Exit {
	private Texture robotTexture;
	private Vector2 position;
	
	public Exit(String texture, Vector2 pos) {
		robotTexture = new Texture(texture);
		position = pos;
	}
	
	void render(SpriteBatch batch) {
		batch.draw(robotTexture, position.x, position.y);
	}

}
