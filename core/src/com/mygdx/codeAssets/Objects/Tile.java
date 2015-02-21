package com.mygdx.codeAssets.Objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.Editor.GameParameters;
import com.mygdx.fileManagement.TextureManager;

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
		try {
			texture = TextureManager.getTileTexture(a_txtID);
			sideTexture = TextureManager.getTileSideTexture(a_sideTxtID);
		} catch (Exception e) {
			System.out.println("catched");
			texture = TextureManager.getTileTexture(0);
			sideTexture = TextureManager.getTileTexture(0);
		}
		collision_boxes = a_collision_boxes;
	}
	

	public Tile(int a_txtID, int a_sideTxtID, boolean a_isSolid){
		this(a_txtID,a_sideTxtID,new ArrayList<CollisionRect>());
		if(a_isSolid)
			collision_boxes.add(new CollisionRect(0,0,tileSize,tileSize));
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
		this(0, 0, new ArrayList<CollisionRect>());
		collision_boxes.add(new CollisionRect(0,0,tileSize,tileSize));
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
	
	// ================ Static Methods ================
	
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
	
	public static Vector3 convertWorldSpaceToTileSpace (Vector3 a_worldSpace){
		return new Vector3(	(int)a_worldSpace.x / tileSize, 
				(int)a_worldSpace.y / tileSize,
				(int)a_worldSpace.z / tileSize);
	}
	
	public static int convertWorldSpaceToTileSpace (int a_worldSpace){
		return (a_worldSpace / tileSize);
	}
	
	public static Vector3 convertWorldSpaceToTileSpace(int a_worldSpaceDimX, int a_worldSpaceDimY, int a_worldSpaceDimZ ){
		return (new Vector3((int)(a_worldSpaceDimX / tileSize), (int)(a_worldSpaceDimY / tileSize), (int)(a_worldSpaceDimZ / tileSize)));
	}
	
}