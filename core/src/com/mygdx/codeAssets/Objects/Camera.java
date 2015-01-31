package com.mygdx.codeAssets.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Camera {
	
	public Vector3 camPosition;
	private Vector2 moveDir;
	
	boolean isMoving = false;
	
	int moveSpeed = 16;
	
	public Camera() {
		camPosition = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
		moveDir = new Vector2(0, 0);
	}
	
	public Vector3 getPosition() {
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
	
	public void update(int a_screenX, int a_screenY, float a_zoom) {
		camPosition.x += a_screenX * a_zoom / 2; 
		camPosition.y -= a_screenY * a_zoom / 2;
	}
	
	
	
	
}
