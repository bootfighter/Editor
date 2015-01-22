package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.codeAssets.Objects.Camera;
import com.mygdx.codeAssets.Objects.Tile;


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

			float currentZoomFactor = renderHandler.orthoCamera.zoom;
			float camPosX = camera.camPosition.x - (Gdx.graphics.getWidth() * currentZoomFactor / 2) ;
			float camPosY = camera.camPosition.y - (Gdx.graphics.getHeight() * currentZoomFactor / 2) ;
			
			float downPosY = Gdx.graphics.getHeight() - paintTouchY;
			float upPosY = Gdx.graphics.getHeight() - screenY;

			Vector3 tilePosition = new Vector3(screenX * currentZoomFactor + camPosX, upPosY * currentZoomFactor + camPosY, 0);
			Vector3 startPosition = new Vector3(paintTouchX * currentZoomFactor + camPosX, downPosY * currentZoomFactor + camPosY, 0);
			
			startPosition = Tile.convertWorldSpaceToTileSpace(startPosition);
			tilePosition = Tile.convertWorldSpaceToTileSpace(tilePosition);
			
			tilePosition.x++;
			tilePosition.y++;
			
			mapHandler.getCurrentMap().fillWithTile(tileHandler.getSelectedTile(), startPosition, tilePosition);
					
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
			camera.update((moveTouchX - screenX)*2, (moveTouchY - screenY)*2, renderHandler.orthoCamera.zoom);
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
