package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Editor.GameParameters;
import com.mygdx.codeAssets.Objects.CollisionRect;
import com.mygdx.codeAssets.Objects.UIElement;
import com.mygdx.codeAssets.UIElements.UITextureField;

public class UIHandler {
	
	SpriteBatch batch;
	UIElement elementList[];
	BitmapFont font;
	Matrix4 normalProjection;
	public final CollisionRect bounds;
	
	public UIHandler() {
		batch = new SpriteBatch();
		elementList = new UIElement[2];
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		bounds = new CollisionRect();
		initiate();
	}
	
	private void initiate() {
		
		elementList[0] = new UITextureField(new Vector2(0,Gdx.graphics.getHeight() - 320), 
				new Texture("background.png"), font, GameParameters.GetIdToTxt());
		elementList[1] = new UITextureField(new Vector2(0,Gdx.graphics.getHeight() - 650), 
				new Texture("background.png"), font, GameParameters.GetIdToSideTxt());
		
		bounds.point1 = new Vector2(0, Gdx.graphics.getHeight() - 650);
		bounds.point2 = new Vector2(160, Gdx.graphics.getHeight());
	}
	
	public void draw(){
		for (UIElement uiElement : elementList) {
			uiElement.draw(batch);
		}
	}
	
	public void touchDown(int a_screenX, int a_screenY, int a_button){
		
		for (UIElement uiElement : elementList) {
			uiElement.touchDown(a_screenX, a_screenY, a_button);
		}
	}
	
	public void scrolled(int a_amount, int a_screenX, int a_screenY) {
		for (UIElement uiElement : elementList) {
			uiElement.scrolled(a_amount, a_screenX, a_screenY);
		}
	}
	
	public void resize(int a_XSize, int a_YSize) {
		normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
		batch.setProjectionMatrix(normalProjection);
	}
	
	protected boolean isInBounds(int a_screenX, int a_screenY){
		a_screenY = Gdx.graphics.getHeight() - a_screenY;
		if (a_screenX > bounds.point2.x || a_screenX < bounds.point1.x  ||
				a_screenY > bounds.point2.y  || a_screenY <  bounds.point1.y ) {
			return false;
		}
		return true;
	}
	
}
