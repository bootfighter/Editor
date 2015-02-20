package com.mygdx.Editor;

import java.util.ArrayList;

public class GameParameters {
	public static final int tileSize = 16;
	public static final float collisionIteration = 1.0f;
	public static final int mapSizeX = 200;
	public static final int mapSizeY = 200;
	public static final int mapSizeZ = 5;

	public static final int tileHightOffset = 8;
	
	public static String mapFolderPath = "../core/assets/";
	
	public static ArrayList<String> GetIdToTxt() {
		ArrayList<String>  out = new ArrayList<String>();
		
		
		out.add("missingtxt.png"); //0
		out.add("air.png"); //1
		out.add("dirt.png"); //2
		out.add("grass.png"); //3
		out.add("stone1.png"); //4
		out.add("grass2.png"); //5
		out.add("raster.png"); //6
		return out;
	}
	
	
	public static ArrayList<String> GetIdToSideTxt(){
		ArrayList<String> out = new ArrayList<String>();
		
		out.add("missingtxt.png");//0
		out.add("stonewall.png");//1

		return out;
	}
	
	
}
