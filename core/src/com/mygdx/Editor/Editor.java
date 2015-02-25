package com.mygdx.Editor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.mygdx.codeAssets.Handlers.EditorHandler;
import com.mygdx.codeAssets.Handlers.EventHandler;
import com.mygdx.codeAssets.Handlers.MapHandler;
import com.mygdx.codeAssets.Handlers.RenderHandler;
import com.mygdx.codeAssets.Handlers.UIHandler;


public class Editor extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	MapHandler mapHandler;
	RenderHandler renderHandler;
	EventHandler eventHandler;
	EditorHandler editorHandler;
	UIHandler uiHandler;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch(2000);
		
		mapHandler = new MapHandler();
		uiHandler = new UIHandler();
		editorHandler = new EditorHandler(mapHandler, uiHandler);
		renderHandler = new RenderHandler(mapHandler, uiHandler, editorHandler, batch);
		eventHandler = new EventHandler(mapHandler, uiHandler, renderHandler, editorHandler);
	
		editorHandler.setOrthoCamera(renderHandler.getOrthoCamera());
		

		
		Gdx.input.setInputProcessor(eventHandler);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.6f, 0.7f, 1.f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		renderHandler.draw();
		uiHandler.update();
		
		System.out.println(batch.renderCalls);
	}
	
	@Override
	public void resize(int width, int height) {
		renderHandler.resize(width, height);
	}
	
	@Override
	public void dispose() {
		
		super.dispose();
	}
	
}
