package com.mygdx.fileManagement;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.Editor.GameParameters;

public class TextureManager {

	
//	private static ArrayList<Texture> tileTextureList = new ArrayList<Texture>();
	private static ArrayList<Pixmap> tilePixmapList = new ArrayList<Pixmap>();
//	private static ArrayList<Texture> tileSideTextureList = new ArrayList<Texture>();
	private static ArrayList<Pixmap> overlayPixmapList = new ArrayList<Pixmap>();
	
	private static ArrayList<String> tileTextureStringList = initTileTextureStringList();
	private static ArrayList<String> tileSideTextureStringList = initTileSideTextureStringList();
	private static ArrayList<String> overlayTextureStringList = initOverlayTextureStringList();
	private static ArrayList<String> entityTextureStringList = initEntityTextureStringList();
	
	private static SpriteSheet tileTextureSpriteSheet = initTileTextureSpriteSheet();;
	private static SpriteSheet tileSideTextureSpriteSheet = initTileSideTextureSpriteSheet();;
	

			
			
	// ====================== String lists ======================
 	private static ArrayList<String> initTileTextureStringList() {
		
 		 ArrayList<String> list = new ArrayList<String>();
		
 		list.add("missingtxt.png"); //0
 		list.add("air.png"); //1
 		list.add("dirt.png"); //2
 		list.add("grass.png"); //3
 		list.add("stone1.png"); //4
 		list.add("grass2.png"); //5
 		list.add("raster.png"); //6
 		list.add("gravel.png"); //7
		
		return list;
	}
	
 	public static ArrayList<String> getTileTextureStringList(){
 		return tileTextureStringList;
 	}
 	
	
	private static ArrayList<String> initTileSideTextureStringList(){
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("missingtxt.png");//0
		list.add("stonewall.png");//1

		return list;
	}
	
	
	public static ArrayList<String> getTileSideTextureStringList(){
		return tileSideTextureStringList;
	}
	
	private static ArrayList<String> initOverlayTextureStringList(){
		ArrayList<String> list= new ArrayList<String>();

		list.add("overlayNorth.png"); //0
		list.add("overlayEast.png"); //1
		list.add("overlaySouth.png"); //2
		list.add("overlayWest.png"); //3
		
		
		return list;
	}
	
	public static ArrayList<String> getOverlayTextureStringList(){
		return overlayTextureStringList;
	}
	
	private static ArrayList<String> initEntityTextureStringList(){
		ArrayList<String> list= new ArrayList<String>();
		
		list.add("baum1.png"); //0
		
		return list;
	}
	
	public static ArrayList<String> getEntityTextureStringList(){
		return entityTextureStringList;
	}
	// ====================== Tile Texture Getter ======================
		
	private static SpriteSheet initTileTextureSpriteSheet(){
//		SpriteSheet spriteSheet = new SpriteSheet(new Texture(GameParameters.tileSpriteSheetFolderPath + "TileSpriteSheet.png"), 
//				numberOfTileTextures, GameParameters.tileSize, GameParameters.tileSize);
		
		return SpriteSheetPacker.packSpriteSheet(GameParameters.tileFolderPath, tileTextureStringList, GameParameters.tileSize, GameParameters.tileSize,
													512, 512);
	}
	
	
	public static TextureRegion getTileTexture(int a_tileID){
		return tileTextureSpriteSheet.getSpriteAtIndex(a_tileID);		
	}
	
	public static SpriteSheet getTileTextureSpriteSheet(){
		return tileTextureSpriteSheet;
	}
	
	public static int getNumberOfTileTextures(){
		return tileTextureSpriteSheet.getNumberOfSprites();
	}
	
	// ====================== Tile Side Texture Getter ======================

	private static SpriteSheet initTileSideTextureSpriteSheet(){
//		SpriteSheet spriteSheet = new SpriteSheet(new Texture(GameParameters.tileSpriteSheetFolderPath + "TileSpriteSheet.png"), 
//				numberOfTileSideTextures, GameParameters.tileSize, GameParameters.tileHightOffset);
		
		return SpriteSheetPacker.packSpriteSheet(GameParameters.sideTextureFolderPath, tileSideTextureStringList, GameParameters.tileSize, GameParameters.tileSideTextureHeight,
													512, 512);
	}
	
	
	public static TextureRegion getTileSideTexture(int a_tileSideID){
		return tileSideTextureSpriteSheet.getSpriteAtIndex(a_tileSideID);
	}
	
	public static SpriteSheet getTileSideTextureSpriteSheet(){
		return tileSideTextureSpriteSheet;
	}
	
	public static int getNumberofTileSideTextures(){
		return tileSideTextureSpriteSheet.getNumberOfSprites();
	}
	
	// ====================== Entity Texture Getter ======================
	

	
	
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
