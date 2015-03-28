package com.mcgamejam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class McGameJam extends ApplicationAdapter {
	SpriteBatch batch;
	GameState gameState;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gameState = new GameState();
	}

	@Override
	public void render () {
		gameState.update();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		gameState.render(batch);
		batch.end();
	}
}
