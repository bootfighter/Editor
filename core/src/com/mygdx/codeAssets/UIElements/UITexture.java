package com.mygdx.codeAssets.UIElements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.codeAssets.Objects.UIElement;

public class UITexture extends UIElement{

	private Texture texture;
	
	public UITexture(Texture a_texture) {
		super();
		texture = a_texture;
		height = texture.getHeight();
		width = texture.getWidth();
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
	public boolean mouseMoved(int a_screenX, int a_screenY) {
		return false;
	}

	@Override
	public void draw(SpriteBatch a_batch) {
		a_batch.begin();
		
		a_batch.draw(texture, position.x, position.y);
		
		a_batch.end();
	}

	@Override
	public void resize(int a_width, int a_height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean scrolled(int a_amount, int a_screenX, int a_screenY) {
		// TODO Auto-generated method stub
		return false;
	}
}
