package com.mcgamejam;

import com.badlogic.gdx.math.Vector2;

public class Obstacle extends Wall {
	
	public Obstacle(String texture, Vector2 pos, int height, int width) {
		super(texture, pos, height, width);
	}

	@Override
	public boolean isDeadly() {
		return true;
	}
}
