package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;


public class EventHandler implements InputProcessor {

	MapHandler mapHandler;
	RenderHandler renderHandler;
	EditorHandler editorHandler;
	UIHandler uiHandler;
	TileHandler tileHandler;
	
	Vector2 currentMousePos;
	
	public EventHandler(MapHandler a_mapHandler, UIHandler a_uiHandler, RenderHandler a_renderHandler){
		mapHandler = a_mapHandler;
		uiHandler = a_uiHandler;
		renderHandler = a_renderHandler;
		editorHandler = new EditorHandler(renderHandler.orthoCamera, mapHandler);
		currentMousePos = new Vector2(0,0);
	}
	
	
	@Override
	public boolean scrolled(int amount) {
		
		if (uiHandler.isInBounds((int)currentMousePos.x, (int)currentMousePos.y)) {
			uiHandler.scrolled(amount, (int)currentMousePos.x, (int)currentMousePos.y);
		}else{
			renderHandler.zoom(amount);
		}
		
		
		return false;
	}


	@Override
	public boolean keyDown(int keycode) {
		
		return false;
	}


	@Override
	public boolean keyUp(int keycode) {
		
		return false;
	}


	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		editorHandler.touchDown(screenX, screenY, button);
		uiHandler.touchDown(screenX, screenY, button);

		return true;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		editorHandler.touchUp(screenX, screenY, button);

		return true;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		editorHandler.touchDragged(screenX, screenY);

		return true;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		currentMousePos.x = screenX;
		currentMousePos.y = screenY;
		return false;
	}
		
}
