package com.mygdx.fileManagement;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.Editor.GameParameters;

public class TextureManager {

	
	private static ArrayList<Texture> tileTextureList = new ArrayList<Texture>();
	private static ArrayList<Pixmap> tilePixmapList = new ArrayList<Pixmap>();
	private static ArrayList<Texture> tileSideTextureList = new ArrayList<Texture>();
	private static ArrayList<Pixmap> overlayPixmapList = new ArrayList<Pixmap>();

	// ====================== String lists ======================
	public static ArrayList<String> getTileTextureStringList() {
		
		ArrayList<String>  tileTextureList = new ArrayList<String>();
		
		tileTextureList.add("missingtxt.png"); //0
		tileTextureList.add("air.png"); //1
		tileTextureList.add("dirt.png"); //2
		tileTextureList.add("grass.png"); //3
		tileTextureList.add("stone1.png"); //4
		tileTextureList.add("grass2.png"); //5
		tileTextureList.add("raster.png"); //6
		return tileTextureList;
	}
	
	
	public static ArrayList<String> getTileSideTextureStringList(){
		ArrayList<String> tileSideTextureList = new ArrayList<String>();
		
		tileSideTextureList.add("missingtxt.png");//0
		tileSideTextureList.add("stonewall.png");//1

		return tileSideTextureList;
	}
	
	public static ArrayList<String> getOverlayTextureStringList(){
		ArrayList<String> overlayTextureList= new ArrayList<String>();

		overlayTextureList.add("overlayNorth.png");
		overlayTextureList.add("overlayEast.png");
		overlayTextureList.add("overlaySouth.png");
		overlayTextureList.add("overlayWest.png");
		
		return overlayTextureList;
	}
	
	
	// ====================== Tile Texture Getter ======================
	
	private static void initTileTextureList(){
		ArrayList<String> TileTextureStringList = getTileTextureStringList();
		for (String string : TileTextureStringList) {
			tileTextureList.add(new Texture(GameParameters.tileFolderPath + string));
		}
	}
	
	public static Texture getTileTexture(int a_tileID){
		if (tileTextureList.size() == 0) 
			initTileTextureList();
		return tileTextureList.get(a_tileID);		
	}
	
	public static ArrayList<Texture> getTileTextureList() {
		if (tileTextureList.size() == 0) 
			initTileTextureList();
		return tileTextureList;
	}
	
	// ====================== Tile Side Texture Getter ======================
	private static void initTileSideTextureList(){
		ArrayList<String> TileSideTextureStringList = getTileSideTextureStringList();
		for (String string : TileSideTextureStringList) {
			tileSideTextureList.add(new Texture(GameParameters.sideTextureFolderPath + string));
		}
	}
	
	public static Texture getTileSideTexture(int a_tileSideTextureID){
		if (tileSideTextureList.size() == 0)
			initTileSideTextureList();
		return tileSideTextureList.get(a_tileSideTextureID);		
	}

	public static ArrayList<Texture> getTileSideTextureList() {
		if (tileSideTextureList.size() == 0)
			initTileSideTextureList();
		return tileSideTextureList;
	}
	
	// ====================== Overlay Pixmap Getter ======================
	private static void initOverlayPixmapList(){
		ArrayList<String> overlayTextureStringList = getOverlayTextureStringList();
		for (String string : overlayTextureStringList) {
			overlayPixmapList.add(new Pixmap(Gdx.files.internal(GameParameters.overlayFolderPath + string)));
		}
	}
	
	public static Pixmap getOverlayPixmap(int a_overlayTextureID){
		if (overlayPixmapList.size() == 0) 
			initOverlayPixmapList();
		return overlayPixmapList.get(a_overlayTextureID);		
	}
	
	// ====================== Texture Pixmap Getter ======================
	
	private static void initTilePixmapList(){
		ArrayList<String> tileTextureStringList = getTileTextureStringList();
		
		for (String string : tileTextureStringList) {
			tilePixmapList.add(new Pixmap(Gdx.files.internal(GameParameters.tileFolderPath + string)));
		}
	}
	
	public static Pixmap getTilePixmap(int a_tilePixmapID){
		if (tilePixmapList.size() == 0)
			initTilePixmapList();
		return tilePixmapList.get(a_tilePixmapID);
	}
	
	
}
