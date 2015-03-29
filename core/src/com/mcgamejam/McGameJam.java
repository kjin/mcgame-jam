package com.mcgamejam;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class McGameJam extends ApplicationAdapter {
	SpriteBatch batch;
	GameState gameState;
	
	@Override
	public void create () {
		Gdx.graphics.setDisplayMode(1280, 720, false);
		batch = new SpriteBatch();
		gameState = new GameState(new Vector2(1280, 720));
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
