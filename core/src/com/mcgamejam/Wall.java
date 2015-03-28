package com.mcgamejam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Wall implements PhysicalGameObject {
	private Texture wallTexture;
	private Vector2 position;
	private int height;
	private int width;
	
	// Body that you can apply forces to and whatnot
	private Body body;
	
	public Wall(String texture, Vector2 pos, int height, int width) {
		wallTexture = new Texture(texture);
		position = pos;
		this.height = height;
		this.width = width;
	}

	@Override
	public void initializePhysics(World physicsWorld) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(position);
		bodyDef.position.scl(GameState.PHYSICS_SCALE);
		body = physicsWorld.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		Vector2[] vertices = new Vector2[4];
		vertices[0] = new Vector2(0, 0);
		vertices[1] = new Vector2(width * GameState.PHYSICS_SCALE, 0);
		vertices[2] = new Vector2(width * GameState.PHYSICS_SCALE, height * GameState.PHYSICS_SCALE);
		vertices[3] = new Vector2(0, height * GameState.PHYSICS_SCALE);
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);
	}

	@Override
	public void update(GameState gameState) {
		// NOP
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(wallTexture, position.x, position.y, width, height);
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
