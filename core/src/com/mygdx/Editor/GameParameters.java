package com.mygdx.Editor;

import java.util.ArrayList;

public class GameParameters {
	public static final int tileSize = 16;
	public static final float collisionIteration = 1.0f;
	public static final int mapSizeX = 640;
	public static final int mapSizeY = 640;
	public static final int mapSizeZ = 1;
	public static final int tileHightOffset = 8;
	
	
	public static ArrayList<String> GetIdToTxt() {
		ArrayList<String>  out = new ArrayList<String>();
			
		out.add("dirt.png");
		out.add("grass.png");
		out.add("missingtxt.png");
		out.add("stone1.png");
		out.add("grass2.png");
		out.add("air.png");
		out.add("raster.png");
		return out;
	}
	
	
	public static ArrayList<String> GetIdToSideTxt(){
		ArrayList<String> out = new ArrayList<String>();
		
		out.add("stonewall.png");

		return out;
	}
	
	
}
