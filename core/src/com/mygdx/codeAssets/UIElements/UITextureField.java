package com.mygdx.codeAssets.UIElements;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Editor.GameParameters;
import com.mygdx.codeAssets.Objects.UIElement;

public class UITextureField extends UIElement {
	
	private ArrayList<String> textureList;
	private Vector2 startPoint;
	private Texture currentTexture;
	private Texture elementBackground;
	private int scrollOffset;
	private BitmapFont font;
	private final int listElementOffset;
	private int currentYPos;
	private int currentID;
	
	public UITextureField(Texture a_background, BitmapFont a_font, ArrayList<String> a_stringList) {
		super();
		textureList = a_stringList;
		elementBackground = a_background;
		width = elementBackground.getWidth();
		height = elementBackground.getHeight();
		
		currentTexture = new Texture("missingtxt.png");
		
		font = a_font;
		scrollOffset = 0;
		currentYPos = 0;
		currentID = 0;
		listElementOffset = 20;
	}
	
	@Override
	public void setPosition(int a_width, int a_height) {
		startPoint = new Vector2(10, height - 10);
		super.setPosition(a_width, a_height);
	}
	
	public void draw(SpriteBatch a_batch){
		a_batch.begin();
		
		a_batch.draw(elementBackground, position.x, position.y);
		
		for (int i = 0; i < textureList.size(); i++) {
			
			currentYPos = (int)(position.y + startPoint.y + scrollOffset - (i * listElementOffset));

			if ( currentYPos < position.y + startPoint.y + listElementOffset && currentYPos > position.y) {
				
				currentTexture = new Texture(textureList.get(i));
				a_batch.draw(currentTexture, position.x + startPoint.x, currentYPos - GameParameters.tileSize);
				font.draw(a_batch, textureList.get(i) , position.x + startPoint.x + 20, currentYPos);
			}
		}
		a_batch.end();
	}
	
	public boolean touchDown(int a_screenX, int a_screenY, int a_button){
		
		switch (a_button) {
		case Buttons.LEFT:
			if (isInBounds(a_screenX, a_screenY)) {
				return true;
			}
			break;
		default:
			return false;
		}
		return false;
	}
	

	@Override
	public boolean touchUp(int a_screenX, int a_screenY, int a_button) {
		switch (a_button) {
		case Buttons.LEFT:
			if (isInBounds(a_screenX, a_screenY)) {
				a_screenY = Gdx.graphics.getHeight() - a_screenY;

				currentID = (int)((position.y + startPoint.y + scrollOffset - a_screenY) / listElementOffset);
				
				if (currentID > textureList.size() - 1 || currentID < 0) {
					currentID = -1;
				}
				
				System.out.println(currentID);
				return true;
			}
			break;
		default:
			return false;
		}
		return false;
	}
	
	public int getCurrentID(){
		return currentID;
	}
	
	public boolean scrolled(int a_amount, int a_screenX, int a_screenY){
		if (isInBounds(a_screenX, a_screenY)) {
			scrollOffset -= a_amount * 15;
			return true;
		}
		return false;
	}




	@Override
	public boolean mouseMoved(int a_screenX, int a_screenY) {
		return false;
	}


	@Override
	public void resize(int a_width, int a_height) {
	}
	
}
