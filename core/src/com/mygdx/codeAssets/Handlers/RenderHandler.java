package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class RenderHandler {
	
	SpriteBatch batch;
	OrthographicCamera orthoCamera;
	
	MapHandler mapHandler;
	UIHandler uiHandler;
	EditorHandler editorHandler;
		
	Vector3 camPosition = new Vector3(0,0,0);
	
	
	public RenderHandler(MapHandler a_mapHandler, UIHandler a_uiHandler, EditorHandler a_editorHandler, SpriteBatch a_batch){
		mapHandler = a_mapHandler;
		uiHandler = a_uiHandler;
		editorHandler = a_editorHandler;
		batch = a_batch;
		
		
		orthoCamera = new OrthographicCamera(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		orthoCamera.position.set(new Vector3(0,0,0));
		orthoCamera.update();
	}

	public OrthographicCamera getOrthoCamera(){
		return orthoCamera;
	}

	public boolean zoom(int a_deltaZoom) {
		if (orthoCamera.zoom + a_deltaZoom * 0.1f <= 4.0f && orthoCamera.zoom + a_deltaZoom * 0.1f >= 0.2f) {
			orthoCamera.zoom += a_deltaZoom * 0.1f;
			return true;
		}
		return false;
	}
	
	public void resize(int a_XSize, int a_YSize) {
		uiHandler.resize(a_XSize, a_YSize);
		orthoCamera.viewportHeight = a_YSize;
		orthoCamera.viewportWidth = a_XSize;
	}
		
	public void draw() {
			
		orthoCamera.update();
		batch.setProjectionMatrix(orthoCamera.combined);
	
		mapHandler.draw(batch);
		
		editorHandler.draw(batch);
		
		uiHandler.draw();
		
		
		
	}	
	
	

}
