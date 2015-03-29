package com.mcgamejam;

import java.awt.geom.RectangularShape;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Robot implements PhysicalGameObject {
	protected Texture robotTexture;
	protected Vector2 position;
	protected Vector2 size = new Vector2(10, 20); // temp - we'll remove this when we have the actual texture
	
	// Body that you can apply forces to and whatnot
	protected Body body;
	
	protected Robot(Vector2 pos)
	{
		robotTexture = new Texture("electrician.png");
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
	}
	
	public void render(SpriteBatch batch)
	{
		batch.draw(robotTexture, position.x, position.y, size.x, size.y);
	}
	
	public void render(SpriteBatch batch, Texture rTexture)
	{
		batch.draw(rTexture, position.x, position.y, size.x, size.y);
	}
}
