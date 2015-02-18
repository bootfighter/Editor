package com.mygdx.codeAssets.Handlers;

import java.io.IOException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.Editor.GameParameters;
import com.mygdx.codeAssets.Objects.GameMap;
import com.mygdx.codeAssets.Objects.Tile;
import com.mygdx.fileManagement.FileManager;

public class MapHandler {
	
	GameMap currentMap;
	GameMap lastMap;
	GameMap cityMap;
		
	public MapHandler() {
		long time = System.currentTimeMillis();
		currentMap = new GameMap(GameParameters.mapSizeX, GameParameters.mapSizeY, GameParameters.mapSizeZ);
		System.out.println("CreationTime: " + (System.currentTimeMillis() - time));

		
		Tile rasterTile = new Tile(1, 0, false);
		
		
		
		currentMap.fillWithTile(rasterTile);
		currentMap.fillWithTile(new Tile(2, 1, true), new Vector3(0,0,0), new Vector3(GameParameters.mapSizeX, GameParameters.mapSizeY, 0));
		
		
		//currentMap.fillWithTile(new Tile(new Texture("dirt.png"), false), new Vector3(0, 0, 0), new Vector3(2,2,0)); 
		
		
	}
	
	
	public void update() {
		
		//TODO:
		//loading of maps? or does the player decide what to load?
		
	}
	
	public void setCurrentMap(GameMap a_map){
		currentMap = a_map;
	}
	
	public void saveMap(String a_name){
		
		try {
			long time = System.currentTimeMillis();
			FileManager.SaveMapToFile(currentMap, a_name);
			System.out.println("SaveTime: " + (System.currentTimeMillis() - time));
		} catch (IOException e) {
			System.out.println("failed to save");
			e.printStackTrace();
		}
		
	}
	
	
	public void loadMap(String a_name){
		
		try {
			long time = System.currentTimeMillis();
			currentMap = FileManager.loadMapFromFile(a_name);
			System.out.println("LoadTime: " + (System.currentTimeMillis() - time));
			
		} catch (IOException e) {
			System.out.println("failed to load");
			e.printStackTrace();
		}
		
	}
	
	public Tile getTile(Vector3 a_tilePosition) {
		return currentMap.getTileAtPosition(a_tilePosition);
	}
	
	public Tile[][][] getTileSubsection(Vector3 a_startPosition, Vector3 a_endPosition){
		return currentMap.getTileSubsection(a_startPosition, a_endPosition);	
	}
	
	public GameMap getCurrentMap() {
		return currentMap;
	}
	
	public void draw(SpriteBatch a_batch,  float a_zoom, Vector3 a_cameraPosition){
		currentMap.draw(a_batch, a_zoom, a_cameraPosition);
	}
}
