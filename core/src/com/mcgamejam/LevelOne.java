package com.mcgamejam;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class LevelOne {
	Robot robot;
	ArrayList<Wall> walls = new ArrayList<Wall>();
	ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	Exit exit;
	
	public LevelOne() {
		robot = new Robot(300, 200);
		
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
}
