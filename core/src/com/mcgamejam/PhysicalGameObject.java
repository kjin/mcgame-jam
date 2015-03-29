package com.mcgamejam;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public abstract class PhysicalGameObject {
	protected boolean isSelected;
	
	public abstract void initializePhysics(World physicsWorld);
	
	public void update(GameState gameState)
	{
		
	}
	
	public abstract void render(SpriteBatch batch);
}
