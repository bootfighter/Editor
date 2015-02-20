package com.mygdx.codeAssets.Objects;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.Editor.GameParameters;

public class GameMap {

	private Tile[][][] tileList;
	private int dimensionX;
	private int dimensionY;
	private int dimensionZ;
	private Vector2 drawPoint1;
	private Vector2 drawPoint2;
	
	private ArrayList<int[]> overlayIds;
	private ArrayList<Texture> overlays;
	

	// Constructors
	public GameMap(int a_dimensionX, int a_dimensionY, int a_dimensionZ) {

		dimensionX = a_dimensionX;
		dimensionY = a_dimensionY;
		dimensionZ = a_dimensionZ;
		
		drawPoint1 = new Vector2(0, 0);
		drawPoint2 = new Vector2(0, 0);
		
		if(isPositiv(a_dimensionX, a_dimensionY, a_dimensionZ))
			tileList = new Tile[dimensionX][dimensionY][dimensionZ];
		else{
			dimensionX = 1;
			dimensionY = 1;
			dimensionZ = 1;
			tileList = new Tile[dimensionX][dimensionY][dimensionZ];
		}
		overlayIds = new ArrayList<int[]>();
		overlays = new ArrayList<Texture>();

	}

	public GameMap() {

		dimensionX = 1;
		dimensionY = 1;
		dimensionZ = 1;
		tileList = new Tile[dimensionX][dimensionY][dimensionZ];

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
					tileList[i][j][k] = new Tile(a_tile);
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
		
		for (int i = (int) point1.x; i <= point2.x; i++) {

			for (int j = (int) point1.y; j <= point2.y; j++) {

				for (int k = (int) point1.z; k <= point2.z ; k++) {
					tileList[i][j][k] = new Tile(a_tile);
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
	
	
	public void draw(SpriteBatch a_batch, float a_zoom, Vector3 a_cameraPosition, int currentZLevel) {

		a_batch.begin();
		
		drawPoint1.x = (a_cameraPosition.x - ((float)Gdx.graphics.getWidth() / 2) * a_zoom);
		drawPoint1.y = (a_cameraPosition.y - ((float)Gdx.graphics.getHeight() / 2) * a_zoom);
		drawPoint2.x = (a_cameraPosition.x + ((float)Gdx.graphics.getWidth() / 2) * a_zoom);
		drawPoint2.y = (a_cameraPosition.y + ((float)Gdx.graphics.getHeight() / 2) * a_zoom);

		drawPoint1.x = Tile.convertWorldSpaceToTileSpace((int)drawPoint1.x) - 5;
		drawPoint1.y = Tile.convertWorldSpaceToTileSpace((int)drawPoint1.y) - 5;
		drawPoint2.x = Tile.convertWorldSpaceToTileSpace((int)drawPoint2.x) + 5;
		drawPoint2.y = Tile.convertWorldSpaceToTileSpace((int)drawPoint2.y) + 5;


		for (int dimZ = 0; dimZ < dimensionZ; dimZ++) {
			if(dimZ > currentZLevel)
				a_batch.setColor(1, 1, 1, 0.3f);
			for (int dimX = (int)drawPoint1.x; dimX < drawPoint2.x; dimX++) {

				for (int dimY = (int)drawPoint2.y; dimY > drawPoint1.y; dimY--) {



					if(isInbounds(dimX, dimY, dimZ)){

						if (tileList[dimX][dimY][dimZ].getTextureID() != 1) {

							//normal texture
							a_batch.draw(tileList[dimX][dimY][dimZ].getTexture(), dimX * GameParameters.tileSize, dimY * GameParameters.tileSize + 
									(dimZ - currentZLevel) * GameParameters.tileHightOffset);
							//overlay
							if(tileList[dimX][dimY][dimZ].getOverlay() != null)
								a_batch.draw(tileList[dimX][dimY][dimZ].getOverlay(), dimX * GameParameters.tileSize, dimY * GameParameters.tileSize);
							//side texture
							if (dimY > 0 && tileList[dimX][dimY - 1][dimZ].getTextureID() == 1) {
								a_batch.draw(tileList[dimX][dimY][dimZ].getSideTexture(), dimX * GameParameters.tileSize, dimY * GameParameters.tileSize + 
										(dimZ - 1 - currentZLevel) * GameParameters.tileHightOffset);
							}

						}

					}

				}
			}
		}
		a_batch.setColor(1, 1, 1, 1);
		a_batch.end();
	}
	
	public Tile[][][] getTiles() {
		return tileList;
	}
	
	
	public void calcTransitions() {
		
		boolean idNotUsed = false;
		int overlayId[] = new int[4];
		Texture overlay;
		
		
		for (int iX = 0; iX < dimensionX; iX++) {

			for (int iY = 0; iY < dimensionY; iY++) {

				for (int iZ = 0; iZ < dimensionZ ; iZ++) {
					
					if(idNotUsed)
						overlayId = new int[4];
					
					//-1 = no overlay
					overlayId[0] = -1; //north
					overlayId[1] = -1; //east
					overlayId[2] = -1; //south
					overlayId[3] = -1; //west
					
					if(iY + 1 < dimensionY) {
						overlayId[0] = tileList[iX][iY + 1][iZ].getTextureID();
						if(overlayId[0] == tileList[iX][iY][iZ].getTextureID())
							overlayId[0] = -1;
					}
					
					if(iX + 1 < dimensionX) {
						overlayId[1] = tileList[iX + 1][iY][iZ].getTextureID();
						if(overlayId[1] == tileList[iX][iY][iZ].getTextureID())
							overlayId[1] = -1;
					}
					
					if(iY - 1 >= 0) {
						overlayId[2] = tileList[iX][iY - 1][iZ].getTextureID();
						if(overlayId[2] == tileList[iX][iY][iZ].getTextureID())
							overlayId[2] = -1;
					}
				
					if(iX - 1 >= 0) { 
						overlayId[3] = tileList[iX - 1][iY][iZ].getTextureID();
						if(overlayId[3] == tileList[iX][iY][iZ].getTextureID())
							overlayId[3] = -1;
					}

					
					idNotUsed = true;
					
					
					for (int usedIdIndex = 0; usedIdIndex < overlayIds.size(); usedIdIndex++) {
						
						if(Arrays.equals(overlayIds.get(usedIdIndex), overlayId)) {
							
							tileList[iX][iY][iZ].setOverlay(overlays.get(usedIdIndex));
							idNotUsed = false;
							
							break;
						}
					}

					if(idNotUsed) {
						overlay = new Texture(calcOverlay(overlayId));
						tileList[iX][iY][iZ].setOverlay(overlay);
						
						overlayIds.add(overlayId);
						overlays.add(overlay);
					}
				}
			}
		}	
	}
	
	private Pixmap calcOverlay(int[] a_overlayId) {
		
		Pixmap overlay = new Pixmap(Gdx.files.internal("overlay0.png"));

		Pixmap overlayMask;
		Pixmap overlayCoulors;
		int pixelMask;
		int pixelColor;
		
		
		if(a_overlayId[0] != -1) {
			overlayMask = new Pixmap(Gdx.files.internal("overlayNorth.png"));
			overlayCoulors = new Pixmap(Gdx.files.internal(GameParameters.GetIdToTxt().get(a_overlayId[0])));
			
			for (int iX = 0; iX < GameParameters.tileSize; iX++) {
				for (int iY = 0; iY < GameParameters.tileSize; iY++) {
					pixelMask = overlayMask.getPixel(iX, iY);
					pixelColor = overlayCoulors.getPixel(iX, iY);
					
					overlay.drawPixel(iX, iY, ((pixelMask & 0x000000FF) + (pixelColor & 0xFFFFFF00)));
					

				}
			}
		}
		
		if(a_overlayId[1] != -1) {
			overlayMask = new Pixmap(Gdx.files.internal("overlayEast.png"));
			overlayCoulors = new Pixmap(Gdx.files.internal(GameParameters.GetIdToTxt().get(a_overlayId[1])));
			
			for (int iX = 0; iX < GameParameters.tileSize; iX++) {
				for (int iY = 0; iY < GameParameters.tileSize; iY++) {
					pixelMask = overlayMask.getPixel(iX, iY);
					pixelColor = overlayCoulors.getPixel(iX, iY);
					
					overlay.drawPixel(iX, iY, ((pixelMask & 0x000000FF) + (pixelColor & 0xFFFFFF00)));
					

				}
			}
		}
		
		if(a_overlayId[2] != -1) {
			overlayMask = new Pixmap(Gdx.files.internal("overlaySouth.png"));
			overlayCoulors = new Pixmap(Gdx.files.internal(GameParameters.GetIdToTxt().get(a_overlayId[2])));
			
			for (int iX = 0; iX < GameParameters.tileSize; iX++) {
				for (int iY = 0; iY < GameParameters.tileSize; iY++) {
					pixelMask = overlayMask.getPixel(iX, iY);
					pixelColor = overlayCoulors.getPixel(iX, iY);
					
					overlay.drawPixel(iX, iY, ((pixelMask & 0x000000FF) + (pixelColor & 0xFFFFFF00)));
					

				}
			}
		}
		
		if(a_overlayId[3] != -1) {
			overlayMask = new Pixmap(Gdx.files.internal("overlayWest.png"));
			overlayCoulors = new Pixmap(Gdx.files.internal(GameParameters.GetIdToTxt().get(a_overlayId[3])));
			
			for (int iX = 0; iX < GameParameters.tileSize; iX++) {
				for (int iY = 0; iY < GameParameters.tileSize; iY++) {
					pixelMask = overlayMask.getPixel(iX, iY);
					pixelColor = overlayCoulors.getPixel(iX, iY);
					
					overlay.drawPixel(iX, iY, ((pixelMask & 0x000000FF) + (pixelColor & 0xFFFFFF00)));
					

				}
			}
		}
		
	
		
		
		
		
		return overlay;
	}
	
}
