package com.mygdx.fileManagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import com.mygdx.codeAssets.Objects.GameMap;
import com.mygdx.codeAssets.Objects.Tile;


public class FileManager {
	
	
	public static void SaveMapToFile(GameMap a_toSave, String a_path) throws IOException {
		
		FileOutputStream out = new FileOutputStream(a_path);
		
		ArrayList<String> idList = new ArrayList<String>();
		String idString;
		
		int dimX = a_toSave.getDimensionX();
		int dimY = a_toSave.getDimensionY();
		int dimZ = a_toSave.getDimensionZ();
		//int tileArray[] = new int[dimX * dimY * dimZ];
		Tile currentTile;
				
		out.write(ByteBuffer.allocate(4).putInt(dimX).array());
		out.write(ByteBuffer.allocate(4).putInt(dimY).array());
		out.write(ByteBuffer.allocate(4).putInt(dimZ).array());
		
		
		for(int iX = 0; iX < dimX; iX++) {
			for(int iY = 0; iY < dimY; iY++){
				for(int iZ = 0; iZ < dimZ; iZ++) {
					currentTile = a_toSave.getTileAtPosition(iX, iY, iZ);
					
					idString = currentTile.getTxtName();
					idString += " ";
					idString += currentTile.getSideTxtName();
					
					out.write(CheckId(idString, idList));
				}
			}
		}
		
		
		for(String s: idList) {
			out.write(s.length());
			out.write(s.getBytes());
		}
		
		
		
		out.close();
		
	}

	private static byte[] CheckId(String a_idString, ArrayList<String> a_idList) {
		System.out.println(a_idString);
		for(int i = 0; i < a_idList.size(); i++) {
			if(a_idString.equals(a_idList.get(i))) {
				return intToArray(i);
			}	
		}
		a_idList.add(a_idString);
		return intToArray(a_idList.size() - 1);
	}
	
	
	
	
	public static GameMap loadMapFromFile(String a_path) throws IOException {
		GameMap map = null;
		
		FileInputStream in = new FileInputStream(a_path);
		byte dimension[] = new byte[12];
		int dimX = arrayToInt(dimension, 0, 4);
		int dimY = arrayToInt(dimension, 4, 4);
		int dimZ = arrayToInt(dimension, 8, 4);
		
		System.out.println(dimX + " " + dimY + " " + dimZ);
		
		
		
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
			out += a_ar[i] << (3 - i) * 4;
			System.out.println(a_ar);
			
		}
		return out;
	}
	
	
	
}
