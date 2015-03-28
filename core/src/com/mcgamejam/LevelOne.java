package com.mcgamejam;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class LevelOne {
	private StaircaseRobot stairbot;
	private SpikeRobot spikebot;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private Exit exit;
	
	public LevelOne() {
		stairbot = new StaircaseRobot(400, 400, true);
		spikebot = new SpikeRobot(100, 150, true);
		
		Wall tester = new Wall("badlogic.jpg", new Vector2(0, 10), 5, 650);
		Wall tester2 = new Wall("badlogic.jpg", new Vector2(500, 100), 5, 20);
		Wall tester3 = new Wall("badlogic.jpg", new Vector2(400, 250), 5, 100);
		Wall tester4 = new Wall("badlogic.jpg", new Vector2(0, 100), 10, 25);
		walls.add(tester);
		walls.add(tester2);
		walls.add(tester3);
		walls.add(tester4);

		exit = new Exit("badlogic.jpg", new Vector2(10, 15), 30, 30);
	}
	
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
	
	public Exit getExit() {
		return exit;
	}

}
