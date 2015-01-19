package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.codeAssets.Objects.Camera;
import com.mygdx.codeAssets.Objects.GameMap;

public class RenderHandler {
	
	SpriteBatch batch;

	OrthographicCamera orthoCamera;
	
	MapHandler mapHandler;
	
	GameMap currentMap;
	
	Vector3 camPosition = new Vector3(0,0,0);
	
	Camera camera;
	
	boolean menu = false;
	
	private BitmapFont debugFont;
	SpriteBatch debugBatch;
	Matrix4 normalProjection;
	Vector2 debugPosition;
	
	public RenderHandler(MapHandler a_mapHandler, SpriteBatch a_batch, Camera a_cam){
		mapHandler = a_mapHandler;
		batch = a_batch;
		camera = a_cam;
		
		isDebug = false;
		debugFont = new BitmapFont();
		debugFont.setColor(Color.WHITE);
		debugBatch = new SpriteBatch();
		normalProjection = new Matrix4();
		debugPosition = new Vector2(10,0);
		
		orthoCamera = new OrthographicCamera(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		orthoCamera.position.set(new Vector3(0,0,0));
		orthoCamera.update();
	}
	
	
	public boolean isDebug;

	public void zoom(int a_deltaZoom) {
		if (orthoCamera.zoom + a_deltaZoom * 0.1f <= 1.3f && orthoCamera.zoom + a_deltaZoom * 0.1f >= 0.2f) {
			orthoCamera.zoom += a_deltaZoom * 0.1f;
		}
	}
	
	public void resize(int a_XSize, int a_YSize) {
		orthoCamera.viewportHeight = a_YSize;
		orthoCamera.viewportWidth = a_XSize;
	}
	
	
	
	public void draw() {
		
		

		//orthoCamera.position.set(new Vector2(camera.camPosition.x, camera.camPosition.y), 0f);
		orthoCamera.update();
		currentMap = mapHandler.getCurrentMap();
		
		batch.setProjectionMatrix(orthoCamera.combined);
		
		
		currentMap.draw(batch);

		if(!menu) {
			
			
			
		}
		
	}	
	
	

}
