package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.mygdx.Editor.GameParameters;
import com.mygdx.codeAssets.Objects.UIElement;
import com.mygdx.codeAssets.UIElements.UIText;
import com.mygdx.codeAssets.UIElements.UITextureField;

public class UIHandler {
	
	SpriteBatch batch;
	UIElement elementList[];
	BitmapFont font;
	Matrix4 normalProjection;
	
	public UIHandler() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		initiate();
	}
	
	private void initiate() {
		elementList = new UIElement[3];
		
		elementList[0] = new UITextureField(new Texture("background.png"), font, GameParameters.GetIdToTxt());
		elementList[1] = new UITextureField(new Texture("background.png"), font, GameParameters.GetIdToSideTxt());
		elementList[2] = new UIText("x | y | z", font, false);
		
		setElementPositions(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
	}
	
	private void setElementPositions(int a_width, int a_height){
		elementList[0].setPosition(0, a_height - 320);
		elementList[1].setPosition(0, a_height - 650);
		elementList[2].setPosition(a_width - elementList[2].getWidth() - 10 , elementList[2].getHeight() + 10);
	}
	
	public void draw(){
		for (UIElement uiElement : elementList) {
			uiElement.draw(batch);
		}
	}
	
	public boolean touchDown(int a_screenX, int a_screenY, int a_button){
		
		for (UIElement uiElement : elementList) {
			if(uiElement.touchDown(a_screenX, a_screenY, a_button))
				return true;
		}
		return false;
	}
	
	public boolean touchUp(int a_screenX, int a_screenY, int a_button){
		
		for (UIElement uiElement : elementList) {
			if(uiElement.touchUp(a_screenX, a_screenY, a_button))
				return true;
		}
		return false;
	}
	
	public boolean scrolled(int a_amount, int a_screenX, int a_screenY) {
		for (UIElement uiElement : elementList) {
			if(uiElement.scrolled(a_amount, a_screenX, a_screenY))
				return true;
		}
		return false;
	}
	
	public void update() {
		
		((UIText)elementList[2]).setText("x | y | z");
		
	}
	
	public void resize(int a_XSize, int a_YSize) {
		setElementPositions(a_XSize, a_YSize);
		normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
		batch.setProjectionMatrix(normalProjection);
	}
	
}
