package com.mcgamejam;

import com.badlogic.gdx.math.Vector2;

public class Exit extends Wall{
	final boolean isExit = true;
	
	public Exit(String texture, Vector2 pos, int height, int width) {
		super(texture, pos, height, width);
	}
	
	@Override
	public boolean isExit() {
		return isExit;
	}
}
