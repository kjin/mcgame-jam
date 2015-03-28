package com.mcgamejam;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

public class GameState {
	
	// Models
	private Robot robot;
	
	// Box2D world
	private World physicsWorld;
	
	// The gameTime
	private float gameTime;
	
	final static float DELTA_TIME = 1.0f/60.0f; //assuming 60FPS
	final static Vector2 GRAVITY_VECTOR = new Vector2(0.0f, -10.0f);
	
	public final static float PHYSICS_SCALE = 1.0f/40.0f;
	
	GameState()
	{
		// init things here
		robot = new Robot(300, 200);
		
		initializePhysics();
	}
	
	void update()
	{
		physicsWorld.step(DELTA_TIME, 10, 8);
		
		// put your update logic here
		robot.update(this);
		gameTime += DELTA_TIME;
	}
	
	void render(SpriteBatch batch)
	{
		// put your rendering logic here
		robot.render(batch);
	}
	
	float getGameTime()
	{
		return gameTime;
	}
	
	private void initializePhysics()
	{
		Box2D.init();
		physicsWorld = new World(GRAVITY_VECTOR, false);
		robot.initializePhysics(physicsWorld);
	}
}
