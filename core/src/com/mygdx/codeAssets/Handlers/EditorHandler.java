package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.codeAssets.Objects.Tile;

public class EditorHandler {
	
	
	float currentZoomFactor;
	float camPosX;
	float camPosY;

	float upPosY;
	int moveTouchX;
	int moveTouchY;

	boolean moveDraging;
	boolean paintDraging = false;
	Vector3 endPosition;
	Vector3 startPosition;
	Vector3 drawPoint1;
	Vector3 drawPoint2;
	Vector3 currentMousePosition;
	Tile[][][] currentTileSubsection;
	Tile currentTile;
	
	OrthographicCamera camera;
	MapHandler mapHandler;
	UIHandler uiHandler;
	
	public EditorHandler(MapHandler a_mapHandler, UIHandler a_UIHandler) {
		currentZoomFactor = 0;
		camPosX = 0;
		upPosY = 0;
		uiHandler = a_UIHandler;
		mapHandler = a_mapHandler;
		currentTileSubsection = new Tile[0][0][0];
		
		endPosition = new Vector3(0, 0, 0);
		startPosition = new Vector3(0, 0, 0);
		currentMousePosition = new Vector3(0, 0, 0);
		drawPoint1 = new Vector3(0, 0, 0);
		drawPoint2 = new Vector3(0, 0, 0);
		
	}
	
	public void setOrthoCamera(OrthographicCamera a_camera){
		camera = a_camera;
	}
	
	public void touchDown(int screenX, int screenY, int button){
		
		switch(button) {

		case Buttons.LEFT:
			paintDraging = true;
			startPosition.set(currentMousePosition);			
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
			
			endPosition.set(currentMousePosition);
			
			if (startPosition.x > endPosition.x) 
				startPosition.x++;
			else 
				endPosition.x++;
			if (startPosition.y > endPosition.y) 
				startPosition.y++;
			else 
				endPosition.y++;
			
			currentTileSubsection = mapHandler.getTileSubsection(startPosition, endPosition);
			currentTile = new Tile(uiHandler.getCurrentSelectedTextureID(), uiHandler.getCurrentSelectedSideTextureID(), true);
			mapHandler.getCurrentMap().fillWithTile(currentTile, startPosition, endPosition);
			
			break;
		case Buttons.RIGHT:
			moveDraging = false;
			moveTouchX = a_screenX;
			moveTouchY = a_screenY;
			break;
		default:
			break;
		}
		
	}

	public boolean touchDragged(int a_screenX, int a_screenY) {
		
		mouseMoved(a_screenX, a_screenY);
		
		if(moveDraging) {
			camera.translate((moveTouchX - a_screenX) * camera.zoom, - (moveTouchY - a_screenY) * camera.zoom);
			moveTouchX = a_screenX;
			moveTouchY = a_screenY;

		} else if(paintDraging) {
			currentTileSubsection = mapHandler.getTileSubsection(startPosition, currentMousePosition);
			
		}
		return true;
	}

	public void mouseMoved(int a_screenX, int a_screenY){
		
		currentZoomFactor = camera.zoom;
		camPosX = camera.position.x - (Gdx.graphics.getWidth() * currentZoomFactor / 2) ;
		camPosY = camera.position.y - (Gdx.graphics.getHeight() * currentZoomFactor / 2) ;
		
		upPosY = Gdx.graphics.getHeight() - a_screenY;
		
		currentMousePosition = Tile.convertWorldSpaceToTileSpace((int)(a_screenX * currentZoomFactor + camPosX), (int)(upPosY * currentZoomFactor + camPosY), 0);
		
		currentMousePosition = mapHandler.getCurrentMap().convertToInbounds(currentMousePosition);	
	}
	
	public void draw(SpriteBatch a_batch){
		
		drawPoint1.set(startPosition);
		drawPoint2.set(currentMousePosition);
		mapHandler.getCurrentMap().sortPoints(drawPoint1, drawPoint2);
		drawPoint2.x++;
		drawPoint2.y++;
		mapHandler.getCurrentMap().convertToInbounds(drawPoint2);
		
		if (paintDraging) {
			a_batch.begin();
			
			a_batch.setColor(1, 1, 1, 0.7f);
			
			currentTile = new Tile(uiHandler.getCurrentSelectedTextureID(), uiHandler.getCurrentSelectedSideTextureID(), true);
			
			for (int posX = 0; posX < drawPoint2.x - drawPoint1.x; posX++) {
				for (int posY = 0; posY < drawPoint2.y - drawPoint1.y; posY++) {
					
					a_batch.draw(currentTile.getTexture(), Tile.convertTileSpaceToWorldSpace(posX + (int)drawPoint1.x),
							Tile.convertTileSpaceToWorldSpace(posY + (int)drawPoint1.y));
					
				}
			}

			a_batch.setColor(1, 1, 1, 1);
			
			a_batch.end();
		}
	}
	
}
