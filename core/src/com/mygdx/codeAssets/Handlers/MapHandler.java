package com.mygdx.codeAssets.Handlers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.Editor.GameParameters;
import com.mygdx.codeAssets.Objects.GameMap;
import com.mygdx.codeAssets.Objects.Tile;

public class MapHandler {
	
	GameMap currentMap;
	GameMap lastMap;
		
	public MapHandler() {
		currentMap = lastMap = new GameMap(GameParameters.mapSizeX, GameParameters.mapSizeY, GameParameters.mapSizeZ);
		
		Tile airTile = new Tile("air.png", "air.png", false);
		Tile rasterTile = new Tile("raster.png", "air.png", false);
		Tile grassTile = new Tile("grass2.png", "dirt.png", false);
		
		currentMap.fillWithTile(airTile);
		
		currentMap.fillWithTile(rasterTile, new Vector3(0, 0, 0), new Vector3(GameParameters.mapSizeX -1 ,GameParameters.mapSizeY - 1 ,0));
		//currentMap.fillWithTile(new Tile(new Texture("dirt.png"), false), new Vector3(0, 0, 0), new Vector3(2,2,0)); 
		
		currentMap.setTileAtPosition(grassTile, new Vector3(2,2,1));
		
	}
	
	
	public void update() {
		
		//TODO:
		//loading of maps? or does the player decide what to load?
		
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
