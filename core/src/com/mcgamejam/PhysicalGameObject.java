package com.mcgamejam;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public interface PhysicalGameObject {
	void initializePhysics(World physicsWorld);
	void update(GameState gameState);
	void render(SpriteBatch batch);
}
