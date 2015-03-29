package com.mcgamejam;

import java.util.ArrayList;

public abstract class Level {
	protected StaircaseRobot stairbot;
	protected SpikeRobot spikebot;
	protected ArrayList<Wall> walls = new ArrayList<Wall>();
	protected ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	protected ArrayList<Light> lights = new ArrayList<Light>();
	protected Exit exit;
	
	public StaircaseRobot getStairRobot() {
		return stairbot;
	}
	
	public SpikeRobot getSpikeRobot() {
		return spikebot;
	}
	
	public ArrayList<Wall> getWalls() {
		return walls;
	}
	
	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}
	
	public ArrayList<Light> getLights() {
		return lights;
	}
	
	public Exit getExit() {
		return exit;
	}
}
