package com.mygdx.codeAssets.UIElements;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Editor.GameParameters;
import com.mygdx.fileManagement.SpriteSheet;

public class UITextureField extends UIElementClickable {
	
	private ArrayList<String> textureStringList;
	private Vector2 startPoint;
	private SpriteSheet spriteSheet;
	private Texture elementBackground;
	private int scrollOffset;
	private BitmapFont font;
	private final int listElementOffset;
	private int currentYPos;
	private int currentID;
	
	public UITextureField(Texture a_background, BitmapFont a_font, ArrayList<String> a_stringList, SpriteSheet a_spriteSheet) {
		super();
		textureStringList = a_stringList;
		spriteSheet = a_spriteSheet;
		elementBackground = a_background;
		width = elementBackground.getWidth();
		height = elementBackground.getHeight();
				
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
		
		for (int i = 0; i < textureStringList.size(); i++) {
			
			currentYPos = (int)(position.y + startPoint.y + scrollOffset - (i * listElementOffset));

			if ( currentYPos < position.y + startPoint.y + listElementOffset && currentYPos > position.y) {
				a_batch.draw(spriteSheet.getSpriteAtIndex(i), position.x + startPoint.x, currentYPos - GameParameters.tileSize);
				font.draw(a_batch, textureStringList.get(i) , position.x + startPoint.x + 20, currentYPos);
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

				if((int)((position.y + startPoint.y + scrollOffset - a_screenY) / listElementOffset) <  textureStringList.size()  && 
						(int)((position.y + startPoint.y + scrollOffset - a_screenY) / listElementOffset) >=  0)
					currentID = (int)((position.y + startPoint.y + scrollOffset - a_screenY) / listElementOffset);
				
//				if (currentID > textureStringList.size() - 1 || currentID < 0) {
//					currentID = -1;
//				}
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


	@Override
	public boolean keyDown(int a_button) {
		return false;
	}


	@Override
	public boolean keyUp(int a_button) {
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		return false;
	}
	
}
