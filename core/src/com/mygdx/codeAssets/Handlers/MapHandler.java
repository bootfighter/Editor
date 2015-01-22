package com.mygdx.codeAssets.Handlers;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
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
		
		currentMap.fillWithTile(new Tile(new Texture("air.png"), new Texture("air.png"), false));
		
		currentMap.fillWithTile(new Tile(new Texture("raster.png"), new Texture("air.png"), false), new Vector3(0, 0, 0), new Vector3(GameParameters.mapSizeX -1 ,GameParameters.mapSizeY - 1 ,0));
		
		//currentMap.fillWithTile(new Tile(new Texture("dirt.png"), false), new Vector3(0, 0, 0), new Vector3(2,2,0)); 
		
		currentMap.setTileAtPosition(new Tile(new Texture("grass2.png"), new Texture("stonewall.png"), false), new Vector3(2,2,1));
		
		ArrayList<CollisionRect> tempList = new ArrayList<CollisionRect>();
		tempList.add(new CollisionRect(new Vector2(4, 0), new Vector2(8, 16)));
		
	}
	
	
	public void update() {
		
		//TODO:
		//loading of maps? or does the player decide what to load?
		
	}
	
	public Tile getTileFromPosition(Vector3 a_tilePosition) {
		return currentMap.getTileAtPosition(a_tilePosition);
	}
	
	public GameMap getCurrentMap() {
		return currentMap;
	}
	
}
