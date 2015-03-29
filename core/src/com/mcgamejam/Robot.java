package com.mcgamejam;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public abstract class Robot implements PhysicalGameObject {
	protected Vector2 position;
	protected Vector2 size; // temp - we'll remove this when we have the actual texture
	protected boolean inLight = false;
	protected boolean facingRight = true;
	
	//animation
	protected int frame = 0;
	protected int counter = 0;
	protected int frameChangeFrequency = 6;
	
	// Body that you can apply forces to and whatnot
	protected Body body;
	
	protected Robot(Vector2 pos)
	{
		position = new Vector2();
		position.set(pos);
	}
	
	protected Robot(float x, float y)
	{
		this(new Vector2(x, y));
	}
	
	public void initializePhysics(World physicsWorld)
	{
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(position);
		bodyDef.position.scl(GameState.PHYSICS_SCALE);
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		body = physicsWorld.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		Vector2[] vertices = new Vector2[4];
		vertices[0] = new Vector2(0, 0);
		vertices[1] = new Vector2(size.x * GameState.PHYSICS_SCALE, 0);
		vertices[2] = new Vector2(size.x * GameState.PHYSICS_SCALE, size.y * GameState.PHYSICS_SCALE);
		vertices[3] = new Vector2(0, size.y * GameState.PHYSICS_SCALE);
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		fixtureDef.shape = shape;
		body.createFixture(fixtureDef);
	}
	
	public void update(GameState gameState)
	{
		position.set(body.getPosition());
		position.scl(1.0f / GameState.PHYSICS_SCALE);
		ArrayList<Light> lights = gameState.getLight();
		for (Light l : lights)
		{
			inLight = l.contains(new Vector2(0.5f * size.x + position.x, 0.5f * size.y + position.y));
		}
	}
	
	public abstract void render(SpriteBatch batch);
}
