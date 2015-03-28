package com.mcgamejam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Wall {
	private Texture robotTexture;
	private Vector2 position;
	private int height;
	
	public Wall(String texture, Vector2 pos, int height) {
		robotTexture = new Texture(texture);
		position = pos;
		this.height = height;
	}
	
	void render(SpriteBatch batch) {
		batch.draw(robotTexture, position.x, position.y);
	}
}
