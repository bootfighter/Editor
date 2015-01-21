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
		int dimX = (int) (a_point2.x - a_point1.x);
		int dimY = (int) (a_point2.y - a_point1.y);
		int dimZ = (int) (a_point2.z - a_point1.z);
				
		Tile[][][] tileSubsection = new Tile[dimX+1][dimY+1][dimZ+1];
		
		for (int x = 0; x <= dimX; x++) {
			
			for (int y = 0; y <= dimY; y++) {

				for (int z = 0; z <= dimZ; z++) {
					
					tileSubsection[x][y][z] = tileList[(int)a_point1.x + x][(int)a_point1.y + y][(int)a_point1.z + z];
					
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

		if(!isInbounds(a_point2) || !isInbounds(a_point1))
			
			return;
		
		for (int i = (int) a_point1.x; i < a_point2.x; i++) {

			for (int j = (int) a_point1.y; j < a_point2.y; j++) {

				for (int k = (int) a_point1.z; k < a_point2.z + 1; k++) {
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
	
	private boolean isPositiv(Vector3 a_dimensions){
		if (a_dimensions.x < 0)
			return false;
		if (a_dimensions.y < 0)
			return false;
		if (a_dimensions.z < 0)
			return false;
		return true;
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
	
	public void draw(SpriteBatch a_batch) {

		a_batch.begin();

		for (int dimX = 0; dimX < dimensionX; dimX++) {

			for (int dimY = 0; dimY < dimensionY; dimY++) {

				for (int dimZ = 0; dimZ < dimensionZ; dimZ++) {

					a_batch.draw(tileList[dimX][dimY][dimZ].texture, dimX * GameParameters.tileSize, dimY * GameParameters.tileSize);

				}

			}

		}
		a_batch.end();
	}

}
