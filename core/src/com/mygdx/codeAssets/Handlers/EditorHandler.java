package com.mygdx.codeAssets.Handlers;

import java.io.File;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.codeAssets.Objects.Tile;

public class EditorHandler {
	
	
	float currentZoomFactor;
	float camPosX;
	float camPosY;
	float downPosY;
	float upPosY;
	int moveTouchX;
	int moveTouchY;
	int paintTouchX;
	int paintTouchY;
	int paintEndX;
	int paintEndY;
	boolean moveDraging;
	
	boolean paintDraging = false;
	TileHandler tileHandler;
	OrthographicCamera camera;
	MapHandler mapHandler;
	
	public EditorHandler(OrthographicCamera a_camera, MapHandler a_mapHandler) {
		currentZoomFactor = 0;
		camPosX = 0;
		camPosY = 0;
		downPosY = 0;
		upPosY = 0;
		camera = a_camera;
		mapHandler = a_mapHandler;
		try {
			tileHandler = new TileHandler(new File("../core/assets/tiles.txt"));
		} catch (IOException e) {
			System.out.println("No Tiles.txt found!");
			e.printStackTrace();
			return;
		}
	}
	
	public void touchDown(int screenX, int screenY, int button){
		
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
		
	}
	
	public void touchUp(int a_screenX, int a_screenY, int a_button) {
		
		switch (a_button) {
		case Buttons.LEFT:
			paintDraging = false;
			
			currentZoomFactor = camera.zoom;
			camPosX = camera.position.x - (Gdx.graphics.getWidth() * currentZoomFactor / 2) ;
			camPosY = camera.position.y - (Gdx.graphics.getHeight() * currentZoomFactor / 2) ;
			
			downPosY = Gdx.graphics.getHeight() - paintTouchY;
			upPosY = Gdx.graphics.getHeight() - a_screenY;

			Vector3 tilePosition = new Vector3(a_screenX * currentZoomFactor + camPosX, upPosY * currentZoomFactor + camPosY, 0);
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
		default:
			break;
		}
		
	}

	public boolean touchDragged(int a_screenX, int a_screenY) {

		if(moveDraging) {
			camera.translate((moveTouchX - a_screenX) * camera.zoom, - (moveTouchY - a_screenY) * camera.zoom);
			moveTouchX = a_screenX;
			moveTouchY = a_screenY;

		} else if(paintDraging) {
			paintEndX = a_screenX;
			paintEndY = a_screenY;
		}
		return true;
	}

	
	
}
