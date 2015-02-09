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
	private int scrollOffset;
	private BitmapFont font;
	private final int listElementOffset;
	private int currentYPos;
	private int currentID;
	
	public UITextureField(Vector2 a_position, Texture a_Background, BitmapFont a_font, ArrayList<String> a_stringList) {
		super(a_position, a_Background);
		textureList = a_stringList;
		startPoint = new Vector2(10, height - 10);
		currentTexture = new Texture("missingtxt.png");
		font = a_font;
		scrollOffset = 0;
		currentYPos = 0;
		currentID = 0;
		listElementOffset = 20;
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
	
	public void touchDown(int a_screenX, int a_screenY, int a_button){
		
		switch (a_button) {
		case Buttons.LEFT:
			a_screenY = Gdx.graphics.getHeight() - a_screenY;
			if (a_screenX > position.x && a_screenX < position.x + width &&
					a_screenY > position.y && a_screenY < position.y + height) {
				 
				currentID = (int)((position.y + startPoint.y + scrollOffset - a_screenY) / listElementOffset);
				
				if (currentID > textureList.size() - 1 || currentID < 0) {
					currentID = -1;
				}
				
				System.out.println(currentID);
			}
			break;
		default:
			break;
		}
		
		
	}
	
	public int getCurrentID(){
		return currentID;
	}
	
	public void scrolled(int a_amount, int a_screenX, int a_screenY){
		if (isInBounds(a_screenX, a_screenY)) {
			scrollOffset -= a_amount * 5;
		}
	}
	
}
