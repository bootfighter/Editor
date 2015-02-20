package com.mygdx.fileManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mygdx.Editor.GameParameters;
import com.mygdx.codeAssets.Objects.GameMap;
import com.mygdx.codeAssets.Objects.Tile;


public abstract class FileManager {
	
	
	public static void SaveMapToFile(GameMap a_toSave, String a_path) throws IOException {
		
		FileOutputStream out = new FileOutputStream(GameParameters.mapFolderPath + a_path);
		Tile[][][] tileList = a_toSave.getTiles();
		
		ArrayList<int[]> idList = new ArrayList<int[]>();
		int checkId[] = new int[2];
		
		
		int dimX = a_toSave.getDimensionX();
		int dimY = a_toSave.getDimensionY();
		int dimZ = a_toSave.getDimensionZ();
		int bufferArray[][][] = new int[dimX][dimY][dimZ];
		byte byteArray[];
		Tile currentTile;
		

		
		//creating ID list
		for(int iX = 0; iX < dimX; iX++) {
			for(int iY = 0; iY < dimY; iY++){
				for(int iZ = 0; iZ < dimZ; iZ++) {
					currentTile = tileList[iX][iY][iZ];
							
					checkId[0] = currentTile.getTextureID();
					checkId[1] = currentTile.getSideTextureID();

					bufferArray[iX][iY][iZ] = CheckId(checkId, idList);
				}
			}
		}
		
		int currentByteArrayIndex = 0;
		
		//dimX * dimY * dimZ * 2 = size for actual map
		//2 = size of idList.size
		//12 = size of Dimensions
		//idList.size * 4 = size of idList 
		byteArray = new byte[dimX * dimY * dimZ * 2 + 2 + 12 + idList.size() * 4];
		
		
		//writing dimensions
		add4ByteIntAtIndex(byteArray, currentByteArrayIndex, dimX);
		currentByteArrayIndex += 4;
		add4ByteIntAtIndex(byteArray, currentByteArrayIndex, dimY);
		currentByteArrayIndex += 4;
		add4ByteIntAtIndex(byteArray, currentByteArrayIndex, dimZ);
		currentByteArrayIndex += 4;
		
		
		//writing ID list size
		add2ByteIntAtIndex(byteArray, currentByteArrayIndex, idList.size());
		currentByteArrayIndex += 2;
		
		//writing ID list into the byte array
		for(int[] currentArray : idList) {
	
			add2ByteIntAtIndex(byteArray, currentByteArrayIndex, currentArray[0]);
			currentByteArrayIndex += 2;
			add2ByteIntAtIndex(byteArray, currentByteArrayIndex, currentArray[1]);
			currentByteArrayIndex += 2;

		}
		
		
		//writing actual map into the byte array
		for(int iX = 0; iX < dimX; iX++) {
			for(int iY = 0; iY < dimY; iY++){
				for(int iZ = 0; iZ < dimZ; iZ++) {
					
					add2ByteIntAtIndex(byteArray, currentByteArrayIndex, bufferArray[iX][iY][iZ]);
					currentByteArrayIndex += 2;
					
				}
			}
		}
		
		//writes out to file
		out.write(byteArray);
		
		out.close();
		
	
	}

	public static GameMap loadMapFromFile(String a_path) throws IOException {
		
		GameMap map = null;
		FileInputStream in = new FileInputStream(GameParameters.mapFolderPath + a_path);
		
		ArrayList<Tile> tileList = new ArrayList<Tile>();
	
		int currentByteIndex = 0;
		int fileSize = (int)new File(GameParameters.mapFolderPath + a_path).length();
		int currentID = -1;
		int dimX = 0;
		int dimY = 0;
		int dimZ = 0;
		int idListLength = 0;
		int[] currentIdCombination;
		
		byte[] byteArray = new byte[fileSize];
		
		
		in.read(byteArray);
		
		in.close();
	
		//reads dimensions from the first 12 bytes
		dimX = arrayToInt(byteArray, currentByteIndex, 4);
		currentByteIndex += 4;
		dimY = arrayToInt(byteArray, currentByteIndex, 4);
		currentByteIndex += 4;
		dimZ = arrayToInt(byteArray, currentByteIndex, 4);
		currentByteIndex += 4;
		
		//creates new GameMap instance with the read dimensions
		map = new GameMap(dimX, dimY, dimZ);
		
		System.out.println(dimX + " | " + dimY + " | " + dimZ);
		
		//reads the ID list length (how many possible combinations are  within the current GameMap)
		idListLength = arrayToInt(byteArray, currentByteIndex, 2);
		currentByteIndex += 2;
		
		
		
		//for every entry in the ID list it creates a new entry in the tile list
		for (int i = 0; i < idListLength; i++) {	
			currentIdCombination = new int[2];
			currentIdCombination[0] = arrayToInt(byteArray, currentByteIndex, 2);
			currentByteIndex += 2;
			currentIdCombination[1] = arrayToInt(byteArray, currentByteIndex, 2);
			currentByteIndex += 2;			
			
			tileList.add(new Tile(currentIdCombination[0], currentIdCombination[1], false));
		}
		
	
		//from the read ID it sets the current tile
		for(int iX = 0; iX < dimX; iX++) {
			for(int iY = 0; iY < dimY; iY++){
				for(int iZ = 0; iZ < dimZ; iZ++) {
					currentID = arrayToInt(byteArray, currentByteIndex, 2);
					currentByteIndex += 2;
					map.setTileAtPosition(new Tile(tileList.get(currentID)), iX, iY, iZ);
				}
			}
		}

		return map;
	}
		
	
	private static int CheckId(int a_checkId[], ArrayList<int[]> a_idList) {
		for(int i = 0; i < a_idList.size(); i++) {
			if(a_checkId[0] == a_idList.get(i)[0] &&  a_checkId[1] == a_idList.get(i)[1]) {
				return i;
			}	
		}
		int[] arr = {a_checkId[0], a_checkId[1]};
		a_idList.add(arr);
		return a_idList.size() - 1;
	}
	
	private static void add2ByteIntAtIndex(byte[] a_byteArray, int a_index, int a_value) {
		
		a_byteArray[a_index] = (byte) (a_value >> 8);
		a_byteArray[a_index + 1] = (byte) (a_value); 	
		
	}
	
	private static void add4ByteIntAtIndex(byte[] a_byteArray, int a_index, int a_value) {
		
		a_byteArray[a_index + 3] = (byte) (a_value );
		a_byteArray[a_index + 2] = (byte) (a_value >> 8); 
		a_byteArray[a_index + 1] = (byte) (a_value >> 16); 	
		a_byteArray[a_index ] = (byte) (a_value >> 24);
		
	}
	
	private static int arrayToInt(byte[] a_ar, int a_offset, int a_length) {
		if(a_offset + a_length > a_ar.length)
			return -1;
		
		int out = 0;
		for(int i = a_offset; i < a_offset + a_length; i++) {
			out = out << 8;
			out = out | (a_ar[i] & 0xff);
		}
		return out;
	}

}
