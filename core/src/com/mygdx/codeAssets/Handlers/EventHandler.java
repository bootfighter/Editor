package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;


public class EventHandler implements InputProcessor {

	MapHandler mapHandler;
	RenderHandler renderHandler;
	EditorHandler editorHandler;
	UIHandler uiHandler;
	
	Vector2 currentMousePos;
	
	public EventHandler(MapHandler a_mapHandler, UIHandler a_uiHandler, RenderHandler a_renderHandler, EditorHandler a_editorHandler){
		mapHandler = a_mapHandler;
		uiHandler = a_uiHandler;
		renderHandler = a_renderHandler;
//		editorHandler = new EditorHandler(renderHandler.orthoCamera, mapHandler);
		editorHandler = a_editorHandler;
		currentMousePos = new Vector2(0,0);
	}
	
	
	@Override
	public boolean scrolled(int amount) {
		
		if (!uiHandler.scrolled(amount, (int)currentMousePos.x, (int)currentMousePos.y)) {
			if(!renderHandler.zoom(amount))
				return false;
		}
		return true;
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

		if (!uiHandler.touchDown(screenX, screenY, button)) {
			editorHandler.touchDown(screenX, screenY, button);
		}
		return true;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		if (!uiHandler.touchUp(screenX, screenY, button)) {
			editorHandler.touchUp(screenX, screenY, button);
		}
		return true;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {

		editorHandler.touchDragged(screenX, screenY);

		return true;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		
		editorHandler.mouseMoved(screenX, screenY);
		
		currentMousePos.x = screenX;
		currentMousePos.y = screenY;
		return false;
	}
		
}
