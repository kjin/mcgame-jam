package com.mcgamejam;

import com.badlogic.gdx.math.Vector2;

public class LevelThree extends Level {
	public LevelThree() {
		stairbot = new StaircaseRobot(400, 400, true);
		spikebot = new SpikeRobot(100, 150, true);
		
		Wall tester = new Wall("Platform.png", new Vector2(0, 10), 5, 650);
		Wall tester2 = new Wall("Platform.png", new Vector2(500, 100), 5, 20);
		Wall tester3 = new Wall("Platform.png", new Vector2(400, 250), 5, 100);
		Wall tester4 = new Wall("Platform.png", new Vector2(0, 100), 10, 25);
		walls.add(tester);
		walls.add(tester2);
		walls.add(tester3);
		walls.add(tester4);
		
		Obstacle test = new Obstacle("Platform.png", new Vector2(45, 15), 10, 20);
		obstacles.add(test);

		exit = new Exit("badlogic.jpg", new Vector2(10, 15), 30, 30);
	}
}
