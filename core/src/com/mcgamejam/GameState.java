package com.mcgamejam;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

public class GameState {
	// Initializer
	private LevelOne levelO;
	
	// Models
	private Robot robot;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private Exit exit;
	
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
		levelO = new LevelOne();
		robot = levelO.robot;
		walls.addAll(levelO.walls);
		obstacles.addAll(levelO.obstacles);
		exit = levelO.exit;
		
		initializePhysics();
		
	}
	
	void update()
	{
		physicsWorld.step(DELTA_TIME, 10, 8);
		
		// put your update logic here, even if it doesn't do anything
		robot.update(this);
		for (Wall wall : walls)
		{
			wall.update(this);
		}
		for (Obstacle obstacle : obstacles)
		{
			obstacle.update(this);
		}
		exit.update(this);
		
		gameTime += DELTA_TIME;
	}
	
	void render(SpriteBatch batch)
	{
		// put your rendering logic here
		robot.render(batch);
		for(Wall w: walls) {
			batch.draw(w.getTexture(), w.getX(), w.getY(), w.getWidth(), w.getHeight());
		}
		for(Obstacle o: obstacles) {
			batch.draw(o.getTexture(), o.getX(), o.getY(), o.getWidth(), o.getHeight());
		}
		batch.draw(exit.getTexture(), exit.getX(), exit.getY(), exit.getWidth(), exit.getHeight());
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
		for (Wall wall : walls)
		{
			wall.initializePhysics(physicsWorld);
		}
		for (Obstacle obstacle : obstacles)
		{
			obstacle.initializePhysics(physicsWorld);
		}
		exit = levelO.exit;
	}
}
