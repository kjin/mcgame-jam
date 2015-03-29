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
	private SpikeRobot spikeBot;
	private StaircaseRobot stairBot;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
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
	
	GameState()
	{
		// init things here
		levelO = new LevelOne();
		stairBot = levelO.getStairRobot();
		spikeBot = levelO.getSpikeRobot();
		walls.addAll(levelO.getWalls());
		obstacles.addAll(levelO.getObstacles());
		exit = levelO.getExit();
		
		initializePhysics();	
	}
	
	void update()
	{
		// Put your update logic here.
		// This method is called every frame.
		physicsWorld.step(DELTA_TIME, 10, 8);
		
		spikeBot.update(this);
		//update the stairBot
		if(stairBot.stairTimeStart == 0 && stairBot.inLight) {
			stairBot.ability();
			stairBot.stairTimeStart = gameTime;
			stairBot.inLight = false;
		}
		else if((gameTime - stairBot.stairTimeStart) >= 3 && stairBot.isStairs) {
			stairBot.changeBack();
		}
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
		exit.update(this);
		
		
		
		gameTime += DELTA_TIME;
	}
	
	void render(SpriteBatch batch)
	{
		// Put your rendering logic here.
		// This method is called every frame.
		spikeBot.render(batch);
		if(stairBot.isStairs) {
			stairBot.render(batch, stairBot.stairTexture);
		}
		else {
			stairBot.render(batch);
		}

		for(Wall wall: walls) {
			wall.render(batch);
		}
		for(Obstacle obstacle: obstacles) {
			obstacle.render(batch);
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
		exit.initializePhysics(physicsWorld);
	}
}
