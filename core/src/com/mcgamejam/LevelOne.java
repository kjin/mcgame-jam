package com.mcgamejam;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class LevelOne {
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private Exit exit;
	
	public LevelOne() {
		Wall tester = new Wall("badlogic.jpg", new Vector2(0, 10), 5, 650);
		Wall tester2 = new Wall("badlogic.jpg", new Vector2(500, 100), 5, 20);
		Wall tester3 = new Wall("badlogic.jpg", new Vector2(400, 250), 5, 100);
		Wall tester4 = new Wall("badlogic.jpg", new Vector2(0, 100), 10, 25);

		exit = new Exit("badlogic.jpg", new Vector2(10, 15), 30, 30);
		walls.add(tester);
		walls.add(tester2);
		walls.add(tester3);
		walls.add(tester4);
	}	
	
	void render(SpriteBatch batch)
	{
		for(Wall w: walls) {
			batch.draw(w.getTexture(), w.getX(), w.getY(), w.getWidth(), w.getHeight());
		}
		for(Obstacle o: obstacles) {
			batch.draw(o.getTexture(), o.getX(), o.getY(), o.getWidth(), o.getHeight());
		}
		batch.draw(exit.getTexture(), exit.getX(), exit.getY(), exit.getWidth(), exit.getHeight());
	}
}
