package com.mygdx.Editor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.codeAssets.Handlers.EventHandler;
import com.mygdx.codeAssets.Handlers.MapHandler;
import com.mygdx.codeAssets.Handlers.RenderHandler;
import com.mygdx.codeAssets.Objects.Camera;


public class Editor extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	MapHandler mapHandler;
	RenderHandler renderHandler;
	EventHandler eventHandler;
	Camera camera;
	
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
				
		mapHandler = new MapHandler();
		renderHandler = new RenderHandler(mapHandler, batch, camera);
		eventHandler = new EventHandler(mapHandler, renderHandler, camera);
		
		Gdx.input.setInputProcessor(eventHandler);
		
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.6f, 0.7f, 1.f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		renderHandler.draw();
		mapHandler.update();
	}
}
