package com.mcgamejam;

import java.util.Comparator;

import com.badlogic.gdx.math.Vector3;

public class CompareZ implements Comparator<Vector3> {

	@Override
	public int compare(Vector3 arg0, Vector3 arg1) {
		if (arg0.z == arg1.z) return 0;
		return arg0.z < arg1.z ? -1 : 1;
	}

}
