package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.codeAssets.Objects.Camera;


public class EventHandler implements InputProcessor {

	MapHandler mapHandler;
	RenderHandler renderHandler;
	Camera camera;
	Vector2 playerDirection;
	Vector2 facingDirection;
	
	public EventHandler(MapHandler a_mapHandler, RenderHandler a_renderHandler, Camera a_cam){
		mapHandler = a_mapHandler;
		renderHandler = a_renderHandler;
		camera = a_cam;
	}
	
	
	@Override
	public boolean scrolled(int amount) {
		renderHandler.zoom(amount);
		return false;
	}


	@Override
	public boolean keyDown(int keycode) {
	
	switch(keycode) {
		
	case Keys.W:
		if (Gdx.input.isKeyPressed(Keys.S))
			camera.setY(0);
		else
			camera.setY(1);
		break;
	case Keys.S:	
		if (Gdx.input.isKeyPressed(Keys.W))
			camera.setY(0);
		else
			camera.setY(-1);
		break;
	case Keys.D:
		if (Gdx.input.isKeyPressed(Keys.A))
			camera.setX(0);
		else
			camera.setX(1);
		break;
	case Keys.A:
		if (Gdx.input.isKeyPressed(Keys.D))
			camera.setX(0);
		else
			camera.setX(-1);
		break;
	}
		
		return true;
	}


	@Override
	public boolean keyUp(int keycode) {
		
		
		return true;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
		
}
