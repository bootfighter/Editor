package com.mygdx.codeAssets.UIElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.codeAssets.Objects.UIElement;

public abstract class UIElementClickable extends UIElement{

	
	public UIElementClickable() {
		super();
	}
	
	@Override
	public abstract void draw(SpriteBatch a_batch);
	
	@Override
	public void resize(int a_width, int a_height) {
		
	}
	
	@Override
	public boolean touchDown(int a_screenX, int a_screenY, int a_button) {
		if (!isHover) //mouse is not over element
			return false;
		isPressed = true;
		return true;
	}
	
	@Override
	public boolean touchUp(int a_screenX, int a_screenY, int a_button) {
		if (!isHover) //mouse is not over element
			return false;
		isPressed = false;
		return true;
	}
	
	@Override
	public boolean mouseMoved(int a_screenX, int a_screenY) {
		a_screenY = Gdx.graphics.getHeight() - a_screenY;
		if (a_screenX < position.x || a_screenX > position.x + width ||
				a_screenY < position.y || a_screenY > position.y + height){
			isHover = false;
			return false;
		}
		
		isHover = true;		
		return true;
	}
}
