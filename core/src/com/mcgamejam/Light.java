package com.mcgamejam;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Light extends PhysicalGameObject {
	private Texture texture;
	private TextureRegion textureRegion;
	private Affine2 transform = new Affine2();
	
	private Vector2 position = new Vector2();
	private Vector2 direction = new Vector2();
	private float angle = 0;
	
	private static final float LIGHT_RANGE = 300;
	private static final float FIELD_OF_VIEW = 0.35f; 
	private static final float PARALLEL_EPSILON = 0.00001f;
	private static final float FLT_EPSILON = 0.00001f;
	private static final float COINCIDENT_EPSILON = 0.00001f;
	private static final float ANGLE_EPSILON = 0.00001f;
	ArrayList<Vector2[]> occlusionPolygons = new ArrayList<Vector2[]>();
	ArrayList<Vector3> vertexArray = new ArrayList<Vector3>();

	private Vector2 rp = new Vector2(); // center

	// View
	private ShapeRenderer shape = new ShapeRenderer();
	private static CompareZ compareZ = new CompareZ();
	
	Light() {
		texture = new Texture("light.png");
		textureRegion = new TextureRegion(texture);
		textureRegion.setRegion(64, 0, 64, 96);
		
		vertexArray.add(new Vector3());
		vertexArray.add(new Vector3());
	}
	
	private static float calculateIntersectionParams(
		Vector2 rp,
		Vector2 rd,
		Vector2 sp,
		Vector2 sd,
		float t1Min)
	{
		// Make sure the lines aren't parallel.
		if (Math.abs(rd.crs(sd)) > PARALLEL_EPSILON)
		{
			float t2 = (rd.x*(sp.y - rp.y) + rd.y*(rp.x - sp.x)) / (sd.x*rd.y - sd.y*rd.x);
			float t1 = (sp.x + sd.x * t2 - rp.x) / rd.x;
			if (t1 >= -FLT_EPSILON && t2 >= -FLT_EPSILON && t2 <= 1 + FLT_EPSILON && t1 < t1Min)
			{
				t1Min = t1;
			}
		}
		return t1Min;
	}
	
	private static boolean triangleContains(Vector2 v0, Vector3 v1, Vector3 v2, Vector2 p)
	{
		float iDet = 1 / ((v1.y - v2.y) * (v0.x - v2.x) + (v2.x - v1.x) * (v0.y - v2.y));
		float a = ((v1.y - v2.y) * (p.x - v2.x) + (v2.x - v1.x) * (p.y - v2.y)) * iDet;
		float b = ((v2.y - v0.y) * (p.x - v2.x) + (v0.x - v2.x) * (p.y - v2.y)) * iDet;
		return a >= 0 && b >= 0 && a + b <= 1;
	}
	
	float calculateMinParams(Vector2 rd, float t1Min)
	{
		Vector2 sp = new Vector2();
		Vector2 sd = new Vector2();
		int numVertices;
		int i, j1, j2;
		float rdAngle = (float)Math.atan2(rd.y, rd.x);
		if (rd.len2() < COINCIDENT_EPSILON || distance(rdAngle, angle) > FIELD_OF_VIEW / 2 + ANGLE_EPSILON)
		{
			// short circuit if points are coincident
			t1Min = 0;
		}
		else
		{
			rd.nor();
			t1Min = LIGHT_RANGE;
			for (i = 0; i < occlusionPolygons.size(); i++)
			{
				Vector2[] currentPolygon = occlusionPolygons.get(i);
				numVertices = currentPolygon.length;
				for (j1 = 0; j1 < numVertices; j1++)
				{
					j2 = j1 + 1;
					if (j2 == numVertices) { j2 = 0; }
					sp.set(currentPolygon[j1]);
					sd.set(currentPolygon[j2]);
					if (rp.dst2(sp) < COINCIDENT_EPSILON || rp.dst2(sd) < COINCIDENT_EPSILON)
					{
						//exit for loop if this happens, since we've achieved the lowest possible t1
						t1Min = 0;
						i = occlusionPolygons.size();
						break;
					}
					else
					{
						sd.sub(sp);
						t1Min = calculateIntersectionParams(rp, rd, sp, sd, t1Min);
					}
				}
			}
		}
		return t1Min;
	}

	private static float distance(float rdAngle, float angle2) {
		float result = Math.abs(rdAngle - angle2);
		while (result > Math.PI)
		{
			result -= 2 * Math.PI;
		}
		return Math.abs(result);
	}

	private void castRays(Vector2 source)
	{
		// smooth transition
		rp.lerp(source, 0.5f);

		Vector2 rd = new Vector2();
		float t1Min = 0;
		int numVertices;
		int i, j, k = 0;
		int l;

		// Populate the vector of directions for later.
		for (i = 0; i < occlusionPolygons.size(); i++)
		{
			Vector2[] currentVertex = occlusionPolygons.get(i);
			numVertices = currentVertex.length;
			for (j = 0; j < numVertices; j++)
			{
				for (l = -1; l <= 1; l++)
				{
					rd.set(currentVertex[j]);
					rd.sub(rp);
					rd.rotateRad(ANGLE_EPSILON * l);
					Vector3 v = vertexArray.get(k);
					v.x = rd.x;
					v.y = rd.y;
					v.z = (float)Math.atan2(v.y, v.x);
					k++;
				}
			}
		}
		Vector3 v = vertexArray.get(k);
		v.set((float)Math.cos(angle - FIELD_OF_VIEW / 2), (float)Math.sin(angle - FIELD_OF_VIEW / 2), angle - FIELD_OF_VIEW / 2);
		k++;
		v = vertexArray.get(k);
		v.set((float)Math.cos(angle + FIELD_OF_VIEW / 2), (float)Math.sin(angle + FIELD_OF_VIEW / 2), angle + FIELD_OF_VIEW / 2);

		// Sort directions by z value (angle) to get them in a consistent angular order.
		Collections.sort(vertexArray, compareZ);

		for (i = 0; i < vertexArray.size(); i++)
		{
			v = vertexArray.get(i);
			rd.set(v.x, v.y);
			t1Min = calculateMinParams(rd, t1Min);
			v.x = rp.x + rd.x * t1Min;
			v.y = rp.y + rd.y * t1Min;
		}
	}

	boolean contains(Vector2 point)
	{
		int h = vertexArray.size() - 1;
		for (int i = 0; i < vertexArray.size(); i++)
		{
			Vector3 v1 = vertexArray.get(h);
			Vector3 v2 = vertexArray.get(i);
			if (triangleContains(rp, v1, v2, point))
			{
				return true;
			}
			h = i;
		}
		return false;
	}

	@Override
	public void initializePhysics(World physicsWorld) {
		// NOP
	}

	@Override
	public void update(GameState gameState) {
		// update position
		if (Gdx.input.justTouched())
		{
			position.set(Gdx.input.getX(), gameState.getDimensions().y - Gdx.input.getY());
		}
		direction.set(Gdx.input.getX() - position.x, gameState.getDimensions().y - Gdx.input.getY() - position.y);
		angle = (float)Math.atan2(direction.y, direction.x);
		
		ArrayList<PhysicalGameObject> walls = gameState.getNonSelectablePhysicalGameObjects();
		while (occlusionPolygons.size() < walls.size())
		{
			int i = occlusionPolygons.size();
			Wall wall = (Wall)walls.get(i); // bad code, but no time to fix atm
			Vector2[] vertices = new Vector2[4];
			vertices[0] = new Vector2(wall.getX(), wall.getY());
			vertices[1] = new Vector2(wall.getX() + wall.getWidth(), wall.getY());
			vertices[2] = new Vector2(wall.getX() + wall.getWidth(), wall.getY() + wall.getHeight());
			vertices[3] = new Vector2(wall.getX(), wall.getY() + wall.getHeight());
			occlusionPolygons.add(vertices);
			for (int j = 0; j < 12; j++)
			{
				vertexArray.add(new Vector3());
			}
		}
		castRays(position);
	}
	
	// helpers
	private Vector2 h1 = new Vector2();
	private Vector2 h2 = new Vector2();

	@Override
	public void render(SpriteBatch batch) {
		batch.end();
		Gdx.gl.glEnable(GL30.GL_BLEND);
	    Gdx.gl.glBlendFunc(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA);
		shape.begin(ShapeType.Filled);
		int h = vertexArray.size() - 1;
		for (int i = 0; i < vertexArray.size(); i++)
		{
			shape.setColor(1f, 1f, 1f, 0.1f);
			Vector3 v1 = vertexArray.get(h);
			Vector3 v2 = vertexArray.get(i);
			h1.set(v1.x, v1.y);
			h2.set(v2.x, v2.y);
			float dist = LIGHT_RANGE;
			if (rp.dst2(h1) > COINCIDENT_EPSILON && rp.dst2(h2) > COINCIDENT_EPSILON)
			{
				for (int j = 0; j < 10; j++)
				{
					if (rp.dst2(h1) > dist * dist)
					{
						h1.sub(rp);
						h1.nor();
						h1.scl(dist);
						h1.add(rp);
					}
					if (rp.dst2(h2) > dist * dist)
					{
						h2.sub(rp);
						h2.nor();
						h2.scl(dist);
						h2.add(rp);
					}
					shape.triangle(rp.x, rp.y, h1.x, h1.y, h2.x, h2.y);
					dist -= LIGHT_RANGE / 10.0f;
				}
			}
			//shape.line(rp.x, rp.y, v1.x, v1.y);
			h = i;
		}
		shape.end();
		Gdx.gl.glDisable(GL30.GL_BLEND);
		transform.setToRotationRad(angle);
		batch.begin();
		batch.draw(textureRegion, rp.x - 32, rp.y - 64, 32, 64, 64, 96, 1, 1, (float)Math.toDegrees(angle) + 90);
	}

}
