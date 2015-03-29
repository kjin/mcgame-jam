package com.mcgamejam;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;

public class GameState {
	private Vector2 dimensions;
	private int levelNum;
	
	// Models to update
	private ArrayList<PhysicalGameObject> nonSelectableModels = new ArrayList<PhysicalGameObject>();
	private ArrayList<PhysicalGameObject> selectableModels = new ArrayList<PhysicalGameObject>();
	
	private ArrayList<Light> lightModels = new ArrayList<Light>();
	private Exit exit;
	
	// Backgrounds
	private Texture[] backgrounds;
	
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
		backgrounds = new Texture[3];
		for (int i = 0; i < 3; i++)
		{
			backgrounds[i] = new Texture("background" + (i + 1) + ".png");
		}
		
		initLevel(0);
	}
	
	void initLevel(int levelNum) {
		this.levelNum = levelNum;
		Level level;
		if (levelNum == 1)
		{
			level = new LevelTwo();
		}
		else if (levelNum == 2)
		{
			level = new LevelThree();
		}
		else
		{
			level = new LevelOne();
		}
		// init things here
		selectableModels.clear();
		selectableModels.add(level.getStairRobot());
		selectableModels.add(level.getSpikeRobot());
		selectableModels.addAll(level.getLights());
		// just so we can keep track of them separately
		lightModels.clear();
		lightModels.addAll(level.getLights());
		nonSelectableModels.clear();
		nonSelectableModels.addAll(level.getWalls());
		nonSelectableModels.addAll(level.getObstacles());
		nonSelectableModels.add(level.getExit());
		// the borders
		nonSelectableModels.add(new Wall(null, new Vector2(-10, 0), 10, (int)dimensions.y));
		nonSelectableModels.add(new Wall(null, new Vector2(dimensions.x, 0), 10, (int)dimensions.y));
		nonSelectableModels.add(new Wall(null, new Vector2(0, -10), (int)dimensions.x, 10));
		nonSelectableModels.add(new Wall(null, new Vector2(0, dimensions.y), (int)dimensions.x, 10));
		initializePhysics();
	}
	
	private void initializePhysics()
	{
		Box2D.init();
		physicsWorld = new World(GRAVITY_VECTOR, false);
		for (PhysicalGameObject model : selectableModels)
		{
			model.initializePhysics(physicsWorld);
		}
		for (PhysicalGameObject model : nonSelectableModels)
		{
			model.initializePhysics(physicsWorld);
		}
	}
	
	void update()
	{
		physicsWorld.step(DELTA_TIME, 10, 8);

		for (PhysicalGameObject model : selectableModels)
		{
			model.update(this);
		}
		for (PhysicalGameObject model : nonSelectableModels)
		{
			model.update(this);
		}
		
		gameTime += DELTA_TIME;
	}
	
	void render(SpriteBatch batch)
	{
		// Draw background.
		batch.draw(backgrounds[levelNum], 0, 0);
		
		// This method is called every frame.

		for (PhysicalGameObject model : nonSelectableModels)
		{
			model.render(batch);
		}
		for (PhysicalGameObject model : selectableModels)
		{
			model.render(batch);
		}
	}
	
	//Getters//
	
	public float getGameTime()
	{
		return gameTime;
	}
	
	public ArrayList<PhysicalGameObject> getNonSelectablePhysicalGameObjects()
	{
		return nonSelectableModels;
	}
	
	public ArrayList<PhysicalGameObject> getSelectablePhysicalGameObjects()
	{
		return selectableModels;
	}
	
	public ArrayList<Light> getLight()
	{
		return lightModels;
	}
	
	public Vector2 getDimensions()
	{
		return dimensions;
	}
}
