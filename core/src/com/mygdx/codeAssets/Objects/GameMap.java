package com.mygdx.codeAssets.Objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.Editor.GameParameters;

public class GameMap {

	private Tile[][][] tileList;
	private int dimensionX;
	private int dimensionY;
	private int dimensionZ;

	// Constructors
	public GameMap(int a_dimensionX, int a_dimensionY, int a_dimensionZ) {

		dimensionX = a_dimensionX;
		dimensionY = a_dimensionY;
		dimensionZ = a_dimensionZ;
		if(isPositiv(a_dimensionX, a_dimensionY, a_dimensionZ))
			tileList = new Tile[dimensionX][dimensionY][dimensionZ];
		else{
			dimensionX = 1;
			dimensionY = 1;
			dimensionZ = 1;
			tileList = new Tile[dimensionX][dimensionY][dimensionZ];
		}
		fillWithTile(new Tile());
	}

	public GameMap() {

		dimensionX = 1;
		dimensionY = 1;
		dimensionZ = 1;
		tileList = new Tile[dimensionX][dimensionY][dimensionZ];

		fillWithTile(new Tile());

	}

	// Setters
	public boolean setTileAtPosition(Tile a_tile, int a_dimensionX, int a_dimensionY,
			int a_dimensionZ) {

		// check for out of bounds
		if(!isInbounds(a_dimensionX, a_dimensionY, a_dimensionZ))
			return false;

		tileList[a_dimensionX][a_dimensionY][a_dimensionZ] = a_tile;

		return true;
	}
	
	public boolean setTileAtPosition(Tile a_tile, Vector3 a_point) {

		// check for out of bounds
		if(!isInbounds(a_point))
			return false;

		tileList[(int)a_point.x][(int)a_point.y][(int)a_point.z] = a_tile;

		return true;
	}

		
	// Getters
	public Tile getTileAtPosition(Vector3 a_tilePosition) {
		if(!isInbounds(a_tilePosition))
			return null;
		return tileList[(int)a_tilePosition.x][(int)a_tilePosition.y][(int)a_tilePosition.z];
	}
	
	
	//TODO: add to Game!!
	//-----------------------------------------------------------------------------
	public Tile getTileAtPosition(int a_xCoord, int a_yCoord, int a_zCoord) {
		if(!isInbounds(a_xCoord, a_yCoord, a_zCoord))
			return null;
		return tileList[a_xCoord][a_yCoord][a_zCoord];
	}
	//-----------------------------------------------------------------------------

	public int getDimensionX() {
		return dimensionX;
	}
	
	public int getDimensionY() {
		return dimensionY;
	}
	
	public int getDimensionZ() {
		return dimensionZ;
	}

	public Tile[][][] getTileSubsection(Vector3 a_point1, Vector3 a_point2)
	{
		Vector3 point1 = new Vector3(a_point1);
		Vector3 point2 = new Vector3(a_point2);
		sortPoints(point1, point2);
		point1 = convertToInbounds(point1);
		point2 = convertToInbounds(point2);
		
		int dimX = (int) (point2.x - point1.x);
		int dimY = (int) (point2.y - point1.y);
		int dimZ = (int) (point2.z - point1.z);
				
		Tile[][][] tileSubsection = new Tile[dimX+1][dimY+1][dimZ+1];
		
		for (int x = 0; x <= dimX; x++) {
			
			for (int y = 0; y <= dimY; y++) {

				for (int z = 0; z <= dimZ; z++) {
					
					tileSubsection[x][y][z] = tileList[(int)point1.x + x][(int)point1.y + y][(int)point1.z + z];
					
				}	
			}
		}
		
		return tileSubsection;
	}
	
	public void fillWithTile(Tile a_tile) {

		for (int i = 0; i < dimensionX; i++) {

			for (int j = 0; j < dimensionY; j++) {

				for (int k = 0; k < dimensionZ; k++) {
					tileList[i][j][k] = a_tile;
				}
			}
		}
	}

	public void fillWithTile(Tile a_tile, Vector3 a_point1, Vector3 a_point2) {

		
		Vector3 point1 = new Vector3(a_point1);
		Vector3 point2 = new Vector3(a_point2);
		sortPoints(point1, point2);
		point1 = convertToInbounds(point1);
		point2 = convertToInbounds(point2);
		System.out.println("pre: " + a_point1 + " " +a_point2);
		System.out.println("pos: " + point1 + " " +point2);
		
		for (int i = (int) point1.x; i < point2.x; i++) {

			for (int j = (int) point1.y; j < point2.y; j++) {

				for (int k = (int) point1.z; k < point2.z + 1; k++) {
					tileList[i][j][k] = a_tile;
				}
			}
		}
	}

	private boolean isInbounds(Vector3 a_index) {
		if(a_index.x < 0 || a_index.x >= dimensionX)
			return false;
		if(a_index.y < 0 || a_index.y >= dimensionY)
			return false;
		if(a_index.z < 0 || a_index.z >= dimensionZ)
			return false;
		return true;
	}
	
	private boolean isInbounds(int a_dimensionX, int a_dimensionY, int a_dimensionZ) {
		if(a_dimensionX < 0 || a_dimensionX >= dimensionX)
			return false;
		if(a_dimensionY < 0 || a_dimensionY >= dimensionY)
			return false;
		if(a_dimensionZ < 0 || a_dimensionZ >= dimensionZ)
			return false;
		return true;
	}
	
	public Vector3 convertToInbounds(Vector3 a_point){
		a_point.x = (a_point.x < 0) ? 0 : a_point.x;
		a_point.y = (a_point.y < 0) ? 0 : a_point.y;
		a_point.z = (a_point.z < 0) ? 0 : a_point.z;
		a_point.x = (a_point.x > dimensionX - 1) ? dimensionX - 1 : a_point.x;
		a_point.y = (a_point.y > dimensionY - 1) ? dimensionY - 1 : a_point.y;
		a_point.z = (a_point.z > dimensionZ - 1) ? dimensionZ - 1 : a_point.z;
		return a_point;
	}
	
	private boolean isPositiv(int a_dimensionX, int a_dimensionY, int a_dimensionZ){
		if(a_dimensionX < 0)
			return false;
		if(a_dimensionY < 0)
			return false;
		if(a_dimensionZ < 0)
			return false;
		return true;
	}
	
	public void sortPoints(Vector3 a_point1, Vector3 a_point2){
		//swaps the values so that lower values are in point1
		if (a_point1.x > a_point2.x) {
			float swapVar = a_point1.x;
			a_point1.x = a_point2.x;
			a_point2.x = swapVar;
		}
		if (a_point1.y > a_point2.y) {
			float swapVar = a_point1.y;
			a_point1.y = a_point2.y;
			a_point2.y = swapVar;
		}
		if (a_point1.z > a_point2.z) {
			float swapVar = a_point1.z;
			a_point1.z = a_point2.z;
			a_point2.z = swapVar;
		}
	}
	
	
	public void draw(SpriteBatch a_batch) {

		a_batch.begin();

		for (int dimX = 0; dimX < dimensionX; dimX++) {

			for (int dimY = dimensionY - 1; dimY >= 0; dimY--) {

				for (int dimZ = 0; dimZ < dimensionZ; dimZ++) {

					if(dimZ > 0){
						a_batch.draw(tileList[dimX][dimY][dimZ].getSideTexture(), dimX * GameParameters.tileSize, dimY * GameParameters.tileSize + (dimZ -1) * GameParameters.tileHightOffset);
					}
					a_batch.draw(tileList[dimX][dimY][dimZ].getTexture(), dimX * GameParameters.tileSize, dimY * GameParameters.tileSize + dimZ  * GameParameters.tileHightOffset);
				}

			}

		}
		a_batch.end();
	}
	
}
