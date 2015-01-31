package com.mygdx.Editor;

import java.io.File;
import java.io.IOException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.codeAssets.Handlers.EventHandler;
import com.mygdx.codeAssets.Handlers.MapHandler;
import com.mygdx.codeAssets.Handlers.RenderHandler;
import com.mygdx.codeAssets.Handlers.TileHandler;
import com.mygdx.codeAssets.Objects.Camera;
import com.mygdx.fileManagement.FileManager;


public class Editor extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	MapHandler mapHandler;
	RenderHandler renderHandler;
	EventHandler eventHandler;
	TileHandler tileHandler;
	Camera camera;
	Skin skin;
	
	@Override
	public void create () {
		
		batch = new SpriteBatch();
		camera = new Camera();		
		
		try {
			tileHandler = new TileHandler(new File("../core/assets/tiles.txt"));
		} catch (IOException e) {
			System.out.println("No Tiles.txt found!");
			e.printStackTrace();
			return;
		}
		
		mapHandler = new MapHandler();
		renderHandler = new RenderHandler(mapHandler, batch, camera);
		eventHandler = new EventHandler(mapHandler, renderHandler, camera, tileHandler);
		
		Gdx.input.setInputProcessor(eventHandler);
		
		try {
			FileManager.SaveMapToFile(mapHandler.getCurrentMap(), "../core/assets/test.txt");
			FileManager.loadMapFromFile("../core/assets/test.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.6f, 0.7f, 1.f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		renderHandler.draw();
		mapHandler.update();
	}
	
	@Override
	public void resize(int width, int height) {
		renderHandler.resize(width, height);
	}
	
}
