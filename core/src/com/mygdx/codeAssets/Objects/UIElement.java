package com.mygdx.codeAssets.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class UIElement {
	
	protected int height;
	protected int width;
	protected Vector2 position;
	protected boolean isPressed;
	protected boolean isHover;
	
	public UIElement() {
		height = 0;
		width = 0;
		position = new Vector2(0,0);
		isPressed = false;
		isHover = false;
	}
	
	public void setCenter(int a_width, int a_height){
		position.x = a_width - width / 2;
		position.y = a_height - height / 2;
	}
	
	public void setPosition(int a_width, int a_height){
		position.x = a_width;
		position.y = a_height;
	}
	
	public void addX(float a_amount){
		position.x += a_amount;
	}
	
	public void addY(float a_amount){
		position.y += a_amount;
	}

	public boolean isPressed() {
		return isPressed;
	}
	
	public boolean isHover() {
		return isHover;
	}
	
	public int getHeight(){
		return height;
	}

	public int getWidth(){
		return width;
	}
	
	public abstract boolean touchDown(int a_screenX, int a_screenY, int a_button);
	
	public abstract boolean touchUp(int a_screenX, int a_screenY, int a_button);
	
	public abstract boolean mouseMoved(int a_screenX, int a_screenY);
	
	public abstract void draw(SpriteBatch a_batch);
	
	public abstract void resize(int a_width, int a_height);
	
	public abstract boolean scrolled(int a_amount, int a_screenX, int a_screenY);
	
	protected boolean isInBounds(int a_screenX, int a_screenY){
		a_screenY = Gdx.graphics.getHeight() - a_screenY;
		if (a_screenX > position.x + width || a_screenX < position.x ||
				a_screenY > position.y + height || a_screenY < position.y) {
			return false;
		}
		return true;
	}
	
}
