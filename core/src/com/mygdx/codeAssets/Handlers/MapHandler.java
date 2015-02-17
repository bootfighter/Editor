package com.mygdx.codeAssets.Handlers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.Editor.GameParameters;
import com.mygdx.codeAssets.Objects.CollisionRect;
import com.mygdx.codeAssets.Objects.GameMap;
import com.mygdx.codeAssets.Objects.Tile;

public class MapHandler {
	
	GameMap currentMap;
	GameMap lastMap;
		
	public MapHandler() {
		currentMap = lastMap = new GameMap(GameParameters.mapSizeX, GameParameters.mapSizeY, GameParameters.mapSizeZ);
		
		currentMap.fillWithTile(new Tile("air.png", "air.png", false));
		
		currentMap.fillWithTile(new Tile("raster.png", "air.png", false), new Vector3(0, 0, 0), new Vector3(GameParameters.mapSizeX -1 ,GameParameters.mapSizeY - 1 ,0));
		
		//currentMap.fillWithTile(new Tile(new Texture("dirt.png"), false), new Vector3(0, 0, 0), new Vector3(2,2,0)); 
		
		currentMap.setTileAtPosition(new Tile("grass2.png", "dirt.png", false), new Vector3(2,2,1));
		
		ArrayList<CollisionRect> tempList = new ArrayList<CollisionRect>();
		tempList.add(new CollisionRect(new Vector2(4, 0), new Vector2(8, 16)));
		
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
	
	public void draw(SpriteBatch a_batch){
		currentMap.draw(a_batch);
	}
}
