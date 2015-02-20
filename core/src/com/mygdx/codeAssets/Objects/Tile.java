package com.mygdx.codeAssets.Objects;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.Editor.GameParameters;

public class Tile {
	
	private Texture texture; 
	private Texture sideTexture;
	private Texture overlay;
	private int textureID;
	private int sideTextureID;
	private ArrayList<CollisionRect> collision_boxes;
	private static int tileSize = GameParameters.tileSize;
	
	
	
	
	// Constructors
	public Tile(int a_txtID, int a_sideTxtID, ArrayList<CollisionRect> a_collision_boxes){
		textureID = a_txtID;
		sideTextureID = a_sideTxtID;
		overlay = null;
		ArrayList<String> tmpTxtList = GameParameters.GetIdToTxt();
		try {
			texture = new Texture(tmpTxtList.get(a_txtID));
			tmpTxtList = GameParameters.GetIdToSideTxt();
			sideTexture = new Texture(tmpTxtList.get(a_sideTxtID));
		} catch (Exception e) {
			System.out.println("catched");
			texture = new Texture("missingtxt.png");
			sideTexture = new Texture("missingtxt.png");
		}
		collision_boxes = a_collision_boxes;
	}
	

	public Tile(int a_txtID, int a_sideTxtID, boolean a_isSolid){
		textureID = a_txtID;
		sideTextureID = a_sideTxtID;
		overlay = null;
		ArrayList<String> tmpTxtList = GameParameters.GetIdToTxt();
		try {
			texture = new Texture(tmpTxtList.get(a_txtID));
			tmpTxtList = GameParameters.GetIdToSideTxt();
			sideTexture = new Texture(tmpTxtList.get(a_sideTxtID));
		} catch (Exception e) {
			System.out.println("catched");
			texture = new Texture("missingtxt.png");
			sideTexture = new Texture("missingtxt.png");
		}
		
		collision_boxes = new ArrayList<CollisionRect>();
		if (a_isSolid)
			collision_boxes.add(new CollisionRect(new Vector2(0, 0), new Vector2(tileSize, tileSize)));
	}
	
	public Tile(Tile a_tile) {
		texture = a_tile.getTexture();
		sideTexture = a_tile.getSideTexture();
		textureID = a_tile.getTextureID();
		sideTextureID = a_tile.getSideTextureID();
		collision_boxes = a_tile.getCollision_boxes();
		overlay = null;
	}
	
	
	public Tile(){
		textureID = 0;
		sideTextureID = 0;
		overlay = null;
		texture = new Texture("missingtxt.png");
		
		sideTexture = new Texture("missingtxt.png");
		
		collision_boxes = new ArrayList<CollisionRect>();
		collision_boxes.add(new CollisionRect(new Vector2(0, 0), new Vector2(tileSize, tileSize)));
	}
	
	public ArrayList<CollisionRect> getCollision_boxes() {
		return collision_boxes;
	}
	
	public Texture getTexture(){
		return texture;
	}

	public Texture getSideTexture(){
		return sideTexture;
	}
	
	public static Vector3 convertTileSpaceToWorldSpace (Vector3 a_tileSpace){
		return new Vector3(	a_tileSpace.x * tileSize, 
				a_tileSpace.y * tileSize,
				a_tileSpace.z * tileSize);
	}
	
	public static int convertTileSpaceToWorldSpace(int a_tileSpace){
		return (a_tileSpace * tileSize);
	}
	
	public static Vector3 convertTileSpaceToWorldSpace(int a_tileSpaceDimX, int a_tileSpaceDimY, int a_tileSpaceDimZ ){
		return (new Vector3(a_tileSpaceDimX * tileSize, a_tileSpaceDimY * tileSize, a_tileSpaceDimZ * tileSize));
	}
	
	public static Vector3 convertWorldSpaceToTileSpace (Vector3 a_tileSpace){
		return new Vector3(	(int)a_tileSpace.x / tileSize, 
				(int)a_tileSpace.y / tileSize,
				(int)a_tileSpace.z / tileSize);
	}
	
	public static int convertWorldSpaceToTileSpace (int a_tileSpace){
		return (a_tileSpace / tileSize);
	}
	
	public static Vector3 convertWorldSpaceToTileSpace(int a_worldSpaceDimX, int a_worldSpaceDimY, int a_worldSpaceDimZ ){
		return (new Vector3((int)(a_worldSpaceDimX / tileSize), (int)(a_worldSpaceDimY / tileSize), (int)(a_worldSpaceDimZ / tileSize)));
	}
	
	public int getSideTextureID() {
		return sideTextureID;
	}
	
	public int getTextureID() {
		return textureID;
	}
	
	public void setOverlay(Texture a_overlay) {
		overlay = a_overlay;
	}
	
	public Texture getOverlay() {
		return overlay;
	}
	
	
	
}