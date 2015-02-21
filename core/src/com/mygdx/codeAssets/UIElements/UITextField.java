package com.mygdx.codeAssets.UIElements;

import java.util.Scanner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UITextField extends UIElementClickable{

	Scanner keyboardScanner;
	private boolean isActive;
	private String text;
	private Texture texture;
	private BitmapFont font;
	private BitmapFont.TextBounds bounds;
	
	public UITextField(Texture a_texture, BitmapFont a_font) {
		super();
		keyboardScanner = new Scanner(System.in);
		isActive = false;
		texture = a_texture;
		height = texture.getHeight();
		width = texture.getWidth();
		text = "CharField";
		font = a_font;
		bounds = font.getBounds(text);
	}
	

	
	
	@Override
	public void draw(SpriteBatch a_batch) {

		a_batch.begin();
		
		a_batch.draw(texture, position.x, position.y);
		if (isActive) {
			a_batch.draw(texture, position.x, position.y);
		}
		
		font.draw(a_batch, text, position.x + 5, position.y + bounds.height + 5);
		
		a_batch.end();
	}

	@Override
	public boolean scrolled(int a_amount, int a_screenX, int a_screenY) {
		return false;
	}

	@Override
	public boolean touchDown(int a_screenX, int a_screenY, int a_button) {
		if(super.touchDown(a_screenX, a_screenY, a_button)){

			isActive = !isActive;
			return true;
		}
		isActive = false;
		return false;
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
	public boolean keyTyped(char a_character) {
		if (isActive) {

			switch (a_character) {
			case 13:
				System.out.println("Enter!");
				text = "";
				break;
			case 8:
				System.out.println("Back!");
				if (text.length() > 0) {
					text = text.substring(0, text.length() - 1);	
				}
				break;
			default:
				text += a_character;
				break;
			}
			return true;
		}	
		return false;
	}
}
