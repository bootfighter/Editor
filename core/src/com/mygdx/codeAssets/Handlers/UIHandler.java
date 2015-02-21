package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.codeAssets.Objects.UIElement;
import com.mygdx.codeAssets.UIElements.UIText;
import com.mygdx.codeAssets.UIElements.UITextField;
import com.mygdx.codeAssets.UIElements.UITexture;
import com.mygdx.codeAssets.UIElements.UITextureField;
import com.mygdx.fileManagement.TextureManager;

public class UIHandler {
	
	SpriteBatch batch;
	UIElement elementList[];
	BitmapFont font;
	Matrix4 normalProjection;
	
	private Texture xyzBackground;
	private Texture textFieldBackground;
	
	private int currentSelectedTextureID;
	private int currentSelectedSideTextureID;
	
	public UIHandler() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		currentSelectedTextureID = 0;
		currentSelectedSideTextureID = 0;
		
		Pixmap backgroundPixmap = new Pixmap(120, 30, Format.RGBA8888);
		backgroundPixmap.setColor(0.7f, 0.7f, 0.7f, 0.7f);
		backgroundPixmap.fill();
		xyzBackground = new Texture(backgroundPixmap);
				
		backgroundPixmap.dispose();
		
		backgroundPixmap = new Pixmap(200, 40, Format.RGBA8888);
		backgroundPixmap.setColor(0.5f, 0.5f, 0.7f, 0.7f);
		backgroundPixmap.fill();
		textFieldBackground = new Texture(backgroundPixmap);
				
		backgroundPixmap.dispose();
		
		
		initiate();
	}
	
	private void initiate() {
		elementList = new UIElement[5];
		
		elementList[0] = new UITextureField(new Texture("background.png"), font, TextureManager.getTileTextureStringList(),
				TextureManager.getTileTextureList());
		elementList[1] = new UITextureField(new Texture("background.png"), font, TextureManager.getTileSideTextureStringList(),
				TextureManager.getTileSideTextureList());
		elementList[2] = new UITexture(xyzBackground);
		elementList[3] = new UIText("x | y | z", font, false);

		elementList[4] = new UITextField(textFieldBackground, font);
		
		setElementPositions(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
	}
	
	private void setElementPositions(int a_width, int a_height){
		elementList[0].setPosition(0, a_height - 320);
		elementList[1].setPosition(0, a_height - 650);
		elementList[2].setPosition(a_width - elementList[2].getWidth(), 0);
		elementList[3].setPosition(a_width - elementList[3].getWidth() - 70 , elementList[3].getHeight() + 10);
		elementList[4].setPosition(a_width - elementList[4].getWidth(), 50);
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
	
	public boolean keyDown(int a_button){
		
		for (UIElement uiElement : elementList) {
			if(uiElement.keyDown(a_button))
				return true;
		}
		return false;
	}
	
	public boolean keyUp(int a_button){
		
		for (UIElement uiElement : elementList) {
			if(uiElement.keyUp(a_button))
				return true;
		}
		return false;
	}
	
	public void mouseMoved(int a_screenX, int a_screenY){
		
		for (UIElement uiElement : elementList) {
			uiElement.mouseMoved(a_screenX, a_screenY);
		}
	}

	public boolean keyTyped(char a_character){
		for (UIElement uiElement : elementList) {
			if(uiElement.keyTyped(a_character))
				return true;
		}
		return false;
	}
	
	public void update() {
		
		currentSelectedTextureID = ((UITextureField)elementList[0]).getCurrentID();
		currentSelectedSideTextureID = ((UITextureField)elementList[1]).getCurrentID();
		
	}

	public void setPositionCoordinates(Vector3 a_posCoords){
		((UIText)elementList[3]).setText((int)a_posCoords.x + " | " + (int)a_posCoords.y + " | " + (int)a_posCoords.z);
	}
	
	public void resize(int a_XSize, int a_YSize) {
		setElementPositions(a_XSize, a_YSize);
		normalProjection = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
		batch.setProjectionMatrix(normalProjection);
	}
	
	public int getCurrentSelectedTextureID() {
		return currentSelectedTextureID;
	}
	
	public int getCurrentSelectedSideTextureID() {
		return currentSelectedSideTextureID;
	}
	
}
