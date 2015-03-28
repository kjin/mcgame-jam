package com.mcgamejam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Floor {
	private Texture robotTexture;
	private Vector2 position;
	private int length;
	
	public Floor(String texture, Vector2 pos, int length) {
		robotTexture = new Texture(texture);
		position = pos;
		this.length = length;
	}
	
	void render(SpriteBatch batch) {
		batch.draw(robotTexture, position.x, position.y);
	}
	
}
