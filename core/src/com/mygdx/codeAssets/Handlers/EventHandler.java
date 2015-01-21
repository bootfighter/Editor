package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.Editor.GameParameters;
import com.mygdx.codeAssets.Objects.Camera;


public class EventHandler implements InputProcessor {

	MapHandler mapHandler;
	RenderHandler renderHandler;
	TileHandler tileHandler;
	Camera camera;
	int moveTouchX;
	int moveTouchY;
	int paintTouchX;
	int paintTouchY;
	int paintEndX;
	int paintEndY;
	boolean moveDraging;
	boolean paintDraging = false;
	
	public EventHandler(MapHandler a_mapHandler, RenderHandler a_renderHandler, Camera a_cam , TileHandler a_tileHandler){
		mapHandler = a_mapHandler;
		renderHandler = a_renderHandler;
		camera = a_cam;
		tileHandler = a_tileHandler;
	}
	
	
	@Override
	public boolean scrolled(int amount) {
		renderHandler.zoom(amount);
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
		
		switch(button) {
		
		case Buttons.LEFT:
			paintDraging = true;
			paintTouchX = screenX;
			paintTouchY = screenY;
			break;
			
		case Buttons.RIGHT:
			moveDraging = true;
			moveTouchX = screenX;
			moveTouchY = screenY;
			
			break;
		}
			System.out.println("hallo du da " + screenX + " " + screenY + " " + pointer );
		
		
		
		return true;
	}


	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		
		switch(button) {
		
		case Buttons.LEFT:
			paintDraging = false;
			
			float tileZoomfact = (1 / GameParameters.tileSize) * renderHandler.orthoCamera.zoom;
			float camPosX = camera.camPosition.x - Gdx.graphics.getWidth()  / 2;
			float camPosY = camera.camPosition.y - Gdx.graphics.getHeight() / 2;
			float upPosY = Gdx.graphics.getHeight() - screenY;
			float downPosY = Gdx.graphics.getHeight() - paintTouchY;
			
			mapHandler.getCurrentMap().setTileAtPosition(tileHandler.getSelectedTile(), (int)((camPosX + screenX)/GameParameters.tileSize), (int)((camPosY + upPosY)/GameParameters.tileSize), 0);
			/*mapHandler.getCurrentMap().fillWithTile(tileHandler.getSelectedTile(), 
					new Vector3( (int) ((camPosX + screenX) * tileZoomfact), (int) ((camPosY + upPosY) * tileZoomfact), 0),
					new Vector3( (int) ((camPosX + paintTouchX) * tileZoomfact), (int) ((camPosY + downPosY) *tileZoomfact), 0));
					*/
			System.out.println(tileZoomfact);
			System.out.println((int)((camPosX + screenX) * tileZoomfact)  + ":" + (int)((camPosY + upPosY) * tileZoomfact) + ":" + 0);
			System.out.println((int)((camPosX + paintTouchX) * tileZoomfact)  + ":" + (int)(camPosY + downPosY) *tileZoomfact + ":" + 0);
			
			break;
			
		case Buttons.RIGHT:
			moveDraging = false;
			
			
			break;
		}
		
		
		return true;
	}


	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		System.out.println(screenX + " " + screenY);
		
		if(moveDraging) {
			camera.update(moveTouchX - screenX, moveTouchY - screenY, renderHandler.orthoCamera.zoom);
			moveTouchX = screenX;
			moveTouchY = screenY;
			
		} else if(paintDraging) {
			paintEndX = screenX;
			paintEndY = screenY;
		}
		return true;
	}


	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
		
}
