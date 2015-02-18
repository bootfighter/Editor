package com.mygdx.fileManagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import com.mygdx.Editor.GameParameters;
import com.mygdx.codeAssets.Objects.GameMap;
import com.mygdx.codeAssets.Objects.Tile;


public abstract class FileManager {
	
	
	public static void SaveMapToFile(GameMap a_toSave, String a_path) throws IOException {
		
		FileOutputStream out = new FileOutputStream(a_path);
		Tile[][][] tileList = a_toSave.getTiles();
		
		ArrayList<String> textureList = GameParameters.GetIdToTxt();
		ArrayList<String> sideTextureList = GameParameters.GetIdToSideTxt();
		
		
		ArrayList<int[]> idList = new ArrayList<int[]>();
		int checkId[] = new int[2];
		
		
		int dimX = a_toSave.getDimensionX();
		int dimY = a_toSave.getDimensionY();
		int dimZ = a_toSave.getDimensionZ();
		int bufferArray[][][] = new int[dimX][dimY][dimZ];
		Tile currentTile;
		
		//writing dimensions
		out.write(ByteBuffer.allocate(4).putInt(dimX).array());
		out.write(ByteBuffer.allocate(4).putInt(dimY).array());
		out.write(ByteBuffer.allocate(4).putInt(dimZ).array());
		
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
		
		//writing ID list size
		out.write(intToArray(idList.size()));
		
		//writing ID list
		for(int[] a: idList) {
			out.write(ByteBuffer.allocate(4).putInt(a[0]).array());
			out.write(ByteBuffer.allocate(4).putInt(a[1]).array());
		}
		
		
		//writing actual map
		for(int iX = 0; iX < dimX; iX++) {
			for(int iY = 0; iY < dimY; iY++){
				for(int iZ = 0; iZ < dimZ; iZ++) {
					out.write(intToArray(bufferArray[iX][iY][iZ]));
				}
			}
		}
		
		
		out.close();
		
	}

	private static int CheckId(int a_checkId[], ArrayList<int[]> a_idList) {
		for(int i = 0; i < a_idList.size(); i++) {
			if(a_checkId[0] == a_idList.get(i)[0] && 
			   a_checkId[1] == a_idList.get(i)[1]) {
				return i;
			}	
		}
		a_idList.add(a_checkId);
		return a_idList.size() - 1;
	}
	
	
	
	
	public static GameMap loadMapFromFile(String a_path) throws IOException {
		GameMap map = null;
		
		
		
		
		FileInputStream in = new FileInputStream(a_path);
		
		ArrayList<String> idList = new ArrayList<String>();
		
		String bufferString;
		byte staticBuffer[] = new byte[2];
		byte buffer[] = new byte[12];
		
		
		in.read(buffer, 0, 12);
		int dimX = arrayToInt(buffer, 0, 4);
		int dimY = arrayToInt(buffer, 4, 4);
		int dimZ = arrayToInt(buffer, 8, 4);
		
		
		in.read(staticBuffer, 0 , 2);
		
		for(int iId = 0; iId < arrayToInt(staticBuffer , 0 , 2); iId++) {
			in.read(staticBuffer, 0, 2);
			buffer = new byte[arrayToInt(staticBuffer, 0, 2)];
			
			bufferString = new String(buffer, "UTF-8");
			
			idList.add(bufferString);
		}
		
		
		for(int iX = 0; iX < dimX; iX++) {
			for(int iY = 0; iY < dimY; iY++){
				for(int iZ = 0; iZ < dimZ; iZ++) {
					
				}
			}
		}
		
		in.close();
		
		return map;
	}
	
	private static byte[] intToArray(int a_in) {
		byte tmpA[] = new byte[2];
		tmpA[0] = (byte) (a_in >> 4);
		tmpA[1] = (byte) (a_in); 		
		
		return tmpA;
	}
	
	private static int arrayToInt(byte[] a_ar, int a_offset, int a_length) {
		if(a_offset + a_length > a_ar.length)
			return -1;
		
		int out = 0;
		for(int i = a_offset; i < a_offset + a_length; i++) {
			out = out << 4;
			out = out | a_ar[i];			
		}
		return out;
	}

	private static int getTextureID(ArrayList<String> a_list, String a_string){
		
		for (String string : a_list) {
			if (string.equals(a_string)) {
				return a_list.indexOf(string);
			}
		}
		return -1;
	}
}
