package com.mygdx.codeAssets.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class UIElement {
	
	protected Vector2 position;
	protected int width;
	protected int height;
	protected Texture elementBackground;
	
	public UIElement(Vector2 a_position, Texture a_background) {
		
		position = a_position;
		elementBackground = a_background;
		
		height = elementBackground.getHeight();
		width  = elementBackground.getWidth();
		
	}
	
	public void setCenter(Vector2 a_center){
		position.x = a_center.x - width / 2;
		position.y = a_center.y - height / 2;
	}
	
	public void setPosition(Vector2 a_position){
		position = new Vector2(a_position);
	}

	
	public abstract void touchDown(int a_screenX, int a_screenY, int a_button);
	
	public abstract void draw(SpriteBatch a_batch);
	
	public abstract void scrolled(int a_amount, int a_screenX, int a_screenY);
	
	protected boolean isInBounds(int a_screenX, int a_screenY){
		a_screenY = Gdx.graphics.getHeight() - a_screenY;
		if (a_screenX > position.x + width || a_screenX < position.x ||
				a_screenY > position.y + height || a_screenY < position.y) {
			return false;
		}
		return true;
	}
	
}
