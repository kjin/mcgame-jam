package com.mcgamejam;

import java.util.ArrayList;
import java.util.Collection;

import com.badlogic.gdx.math.Vector2;

public class LevelTwo {
	private StaircaseRobot stairbot;
	private SpikeRobot spikebot;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private Exit exit;
	
	public LevelTwo() {
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
		
		Obstacle test = new Obstacle("badlogic.jpg", new Vector2(45, 15), 10, 20);
		obstacles.add(test);

		exit = new Exit("badlogic.jpg", new Vector2(10, 15), 30, 30);
	}

	public StaircaseRobot getStairRobot() {
		// TODO Auto-generated method stub
		return stairbot;
	}

	public SpikeRobot getSpikeRobot() {
		// TODO Auto-generated method stub
		return spikebot;
	}

	public ArrayList<Wall> getWalls() {
		// TODO Auto-generated method stub
		return walls;
	}

	public ArrayList<Obstacle> getObstacles() {
		// TODO Auto-generated method stub
		return obstacles;
	}

	public Exit getExit() {
		// TODO Auto-generated method stub
		return exit;
	}
}
