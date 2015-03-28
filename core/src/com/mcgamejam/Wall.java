package com.mcgamejam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public class Wall implements PhysicalGameObject {
	private Texture wallTexture;
	private Vector2 position;
	private int height;
	private int width;
	
	public Wall(String texture, Vector2 pos, int height, int width) {
		wallTexture = new Texture(texture);
		position = pos;
		this.height = height;
		this.width = width;
	}

	@Override
	public void initializePhysics(World physicsWorld) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameState gameState) {
		// NOP
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}
	
	public float getX() {
		return position.x;
	}
	
	public float getY() {
		return position.y;
	}
	
	public Texture getTexture() {
		return wallTexture;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public boolean isDeadly() {
		return false;
	}
	
	public boolean isExit() {
		return false;
	}
}
