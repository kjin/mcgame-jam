package com.mcgamejam;

import java.awt.geom.RectangularShape;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class Robot implements PhysicalGameObject {
	private Texture robotTexture;
	private Vector2 position;
	
	// Physics stuff
	
	// Body that you can apply forces to and whatnot
	private Body body;
	
	Robot(Vector2 pos)
	{
		robotTexture = new Texture("badlogic.jpg");
		position = new Vector2();
		position.set(pos);
	}
	
	Robot(float x, float y)
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
		vertices[1] = new Vector2(robotTexture.getWidth() * GameState.PHYSICS_SCALE, 0);
		vertices[2] = new Vector2(robotTexture.getWidth() * GameState.PHYSICS_SCALE, robotTexture.getHeight() * GameState.PHYSICS_SCALE);
		vertices[3] = new Vector2(0, robotTexture.getHeight() * GameState.PHYSICS_SCALE);
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
		batch.draw(robotTexture, position.x, position.y);
	}
}
