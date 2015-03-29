package com.mcgamejam;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class StaircaseRobot extends Robot{
	protected Vector2 dy, dx;
	protected boolean facingRight, isStairs, inLight;
	protected Texture stairTexture, robotTexture;
	protected Fixture stairFixture;
	protected float stairTimeStart;
	
	protected StaircaseRobot(float x, float y, boolean faceRight) {
		super(x, y);

		//right
		if(facingRight) {
			if((x + 150) < 1280) {
				dx = new Vector2(x + 150, y);
			}
			else {
				dx = new Vector2(1280, y);
			}
		}
		else {											//left
			if((x - 150) > 0) {
				 dx = new Vector2(x - 150, y);
			}
			else {
				dx = new Vector2(0, y);
			}
		}
		
		if((y + 100) < 720) {
			dy = new Vector2(x, y + 100);
		}
		else {
			dy = new Vector2(x, 720);
		}
		
		robotTexture = new Texture("electrician.png");
		stairTexture = new Texture("badlogic.jpg");
		stairTimeStart = 0;
		facingRight = faceRight;
		inLight = false;
	}

	public void ability() {
		FixtureDef fixtureDef = new FixtureDef();
		Vector2[] vertices = new Vector2[3];
		vertices[0] = new Vector2(0, 0);
		vertices[1] = new Vector2(150, 0);
		vertices[2] = new Vector2(0, 100);
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		fixtureDef.shape = shape;
		stairFixture = body.createFixture(fixtureDef);
		isStairs = true;
	}
	
	public void changeBack() {
		body.destroyFixture(stairFixture);
		isStairs = false;
		inLight = false;
		stairTimeStart = 0;
	}
	
	public void directionSwap() {
		if(facingRight) {
			if((position.x - 150) > 0) {
				 dx = new Vector2(position.x - 150, position.y);
			}
			else {
				dx = new Vector2(0, position.y);
			}
			facingRight = false;
		}
		else {
			if((position.x + 150) < 1280) {
				dx = new Vector2(position.x + 150, position.y);
			}
			else {
				dx = new Vector2(1280, position.y);
			}
			facingRight = true;
		}
	}
}
