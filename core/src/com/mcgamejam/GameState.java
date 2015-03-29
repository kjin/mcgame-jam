package com.mcgamejam;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

public class GameState {
	private Vector2 dimensions;
	
	// Models
	private SpikeRobot spikeBot;
	private StaircaseRobot stairBot;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private ArrayList<Light> lights = new ArrayList<Light>();
	private Exit exit;
	
	// Box2D world
	private World physicsWorld;
	
	// The gameTime
	private float gameTime;
	
	final static float DELTA_TIME = 1.0f/60.0f; //assuming 60FPS
	final static Vector2 GRAVITY_VECTOR = new Vector2(0.0f, -10.0f);
	
	// We need this because the scale is too large for the physics world otherwise.
	// See usage in Robot.java
	public final static float PHYSICS_SCALE = 1.0f/40.0f;
	
	GameState(Vector2 dimensions)
	{
		this.dimensions = dimensions;
		initLevel(1);
	}
	
	void initLevel(int levelNum) {
		Level level;
		if (levelNum == 2)
		{
			level = new LevelTwo();
		}
		else if (levelNum == 3)
		{
			level = new LevelThree();
		}
		else
		{
			level = new LevelOne();
		}
		// init things here
		stairBot = level.getStairRobot();
		spikeBot = level.getSpikeRobot();
		walls.addAll(level.getWalls());
		// add walls on all sides
		walls.add(new Wall(null, new Vector2(-10, 0), 10, (int)dimensions.y));
		walls.add(new Wall(null, new Vector2(dimensions.x, 0), 10, (int)dimensions.y));
		walls.add(new Wall(null, new Vector2(0, -10), (int)dimensions.x, 10));
		walls.add(new Wall(null, new Vector2(0, dimensions.y), (int)dimensions.x, 10));
		obstacles.addAll(level.getObstacles());
		lights.addAll(level.getLights());
		exit = level.getExit();
		initializePhysics();
	}
	
	private void initializePhysics()
	{
		Box2D.init();
		physicsWorld = new World(GRAVITY_VECTOR, false);
		spikeBot.initializePhysics(physicsWorld);
		stairBot.initializePhysics(physicsWorld);
		for (Wall wall : walls)
		{
			wall.initializePhysics(physicsWorld);
		}
		for (Obstacle obstacle : obstacles)
		{
			obstacle.initializePhysics(physicsWorld);
		}
		for (Light light : lights)
		{
			light.initializePhysics(physicsWorld);
		}
		exit.initializePhysics(physicsWorld);
	}
	
	void update()
	{
		physicsWorld.step(DELTA_TIME, 10, 8);
		
		spikeBot.update(this);
		//update the stairBot
		stairBot.update(this);
		
		//update environment
		for (Wall wall : walls)
		{
			wall.update(this);
		}
		for (Obstacle obstacle : obstacles)
		{
			obstacle.update(this);
		}
		for (Light light : lights)
		{
			light.update(this);
		}
		exit.update(this);
		
		gameTime += DELTA_TIME;
	}
	
	void render(SpriteBatch batch)
	{
		// Put your rendering logic here.
		// This method is called every frame.
		for(Wall wall: walls) {
			wall.render(batch);
		}
		for(Obstacle obstacle: obstacles)
		{
			obstacle.render(batch);
		}
		for (Light light : lights)
		{
			light.render(batch);
		}
		spikeBot.render(batch);
		stairBot.render(batch);
		batch.draw(exit.getTexture(), exit.getX(), exit.getY(), exit.getWidth(), exit.getHeight());
	}
	
	//Getters//
	
	public float getGameTime()
	{
		return gameTime;
	}
	
	public ArrayList<Wall> getWalls()
	{
		return walls;
	}
	
	public ArrayList<Light> getLight()
	{
		return lights;
	}
	
	public Vector2 getDimensions()
	{
		return dimensions;
	}
}
