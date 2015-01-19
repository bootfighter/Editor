package com.mygdx.codeAssets.Objects;

import com.badlogic.gdx.math.Vector2;

public class CollisionRect {
	
	public Vector2 point1;
	public Vector2 point2;
	
	
	// Constructors
	public CollisionRect(Vector2 a_point1, Vector2 a_point2) {
		
		point1 = a_point1;
		point2 = a_point2;
	
	}
	
	public CollisionRect() {
		point1 = new Vector2(0,0);
		point2 = new Vector2(0,0);
	}

}
