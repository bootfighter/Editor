package com.mygdx.codeAssets.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Camera {
	
	public Vector3 camPosition;
	private Vector2 moveDir;
	int moveSpeed = 16;
	
	public Camera() {
		camPosition = new Vector3(0, 0, 0);
		moveDir = new Vector2(0, 0);
	}
	
	public Vector3 getPosition() {
		//camPosition.x = moveDir.x * moveSpeed * Gdx.graphics.getDeltaTime();
		//camPosition.y = moveDir.y * moveSpeed * Gdx.graphics.getDeltaTime();
		return camPosition;
	}
	
	public void setPosition(Vector3 a_newPosition) {
		camPosition = a_newPosition;
	}
	
	public void setX(int a_x) {
		moveDir.x = a_x;
	}
	
	public void setY(int a_y) {
		moveDir.y = a_y;
	}
	
	public void update() {
		
	}
	
	
	
	
}
