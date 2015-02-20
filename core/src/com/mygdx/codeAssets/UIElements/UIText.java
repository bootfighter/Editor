package com.mygdx.codeAssets.UIElements;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.codeAssets.Objects.UIElement;

public class UIText extends UIElement {

	private String text;
	private BitmapFont font;
	private BitmapFont.TextBounds bounds;
	private boolean isMultiLine;
	
	public UIText(String a_text, BitmapFont a_font, boolean a_isMultiLine) {
		super();
		text = a_text;
		font = a_font;
		isMultiLine = a_isMultiLine;
		if(isMultiLine)
			bounds = font.getMultiLineBounds(text);
		else
			bounds = font.getBounds(text);
		width = (int)bounds.width;
		height = (int)bounds.height;
	}
	
	
	@Override
	public void draw(SpriteBatch a_batch) {
		a_batch.begin();
		
		font.drawMultiLine(a_batch, text, position.x, position.y);
		
		a_batch.end();
	}
	
	@Override
	public boolean mouseMoved(int a_screenX, int a_screenY) {
		return false;
	}
	
	@Override
	public boolean touchDown(int a_screenX, int a_screenY, int a_button) {
		return false;
	}
	
	@Override
	public boolean touchUp(int a_screenX, int a_screenY, int a_button) {
		return false;
	}
	
	@Override
	public void resize(int a_width, int a_height) {
		
	}

	public void setText(String a_string){
		text = a_string;
	}

	@Override
	public boolean scrolled(int a_amount, int a_screenX, int a_screenY) {
		// TODO Auto-generated method stub
		return false;
	}
}
