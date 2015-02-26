package com.mygdx.fileManagement;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheet{
	
	
	private TextureRegion spriteSheetTexture;
	private int width;
	private int height;
	private int numberOfSprites;
	private int tileSizeX;
	private int tileSizeY;
	private int tileDimensionX;
	private int tileDimensionY;
	
	
	public SpriteSheet(Texture a_spriteSheet, int a_numberOfSprites, int a_tileSizeX, int a_tileSizeY) {
		spriteSheetTexture = new TextureRegion(a_spriteSheet);
		width = spriteSheetTexture.getRegionWidth();
		height = spriteSheetTexture.getRegionHeight();
		numberOfSprites = a_numberOfSprites;
		tileSizeX = a_tileSizeX;
		tileSizeY = a_tileSizeY;
		tileDimensionX = width / tileSizeX;
		tileDimensionY = height / tileSizeY;
	}
	
	
	public TextureRegion getSpriteAtIndex(int a_index){
		spriteSheetTexture.setRegion((int)(a_index % tileDimensionX) * tileSizeX, (int)(a_index / tileDimensionY) * tileSizeY, tileSizeX, tileSizeY);
		return spriteSheetTexture;
	}
	
	public int getNumberOfSprites(){
		return numberOfSprites;
	}
	
	/**adds a texture to the existing texture. ONLY USE DURING RUNTIME!
	 * 
	 * 
	 * @param a_pixmap Pixmpa of the texture
	 */
	public void addSprite(Pixmap a_pixmap){
		
		
		
		if (tileDimensionX * tileDimensionY > numberOfSprites)
			return;
		
		//gets pixmap from existing spritesheet
		spriteSheetTexture.getTexture().getTextureData().prepare();
		Pixmap spriteSheetPixmap = spriteSheetTexture.getTexture().getTextureData().consumePixmap();
		
		//calculating position of new sprite
		int posX =  (numberOfSprites % tileDimensionX) * tileSizeX;
		int poxY =  (numberOfSprites / tileDimensionX) * tileSizeY;
		
		for (int dimX = 0; dimX < tileSizeX; dimX++) {
			for (int dimY = 0; dimY < tileSizeY; dimY++){
				
				//writes pixels from given pixmap onto the existing spritesheet
				spriteSheetPixmap.drawPixel(posX + dimX, poxY + dimY, a_pixmap.getPixel(dimX, dimY));
				
				
			}
		}
		
		numberOfSprites++;
		
		spriteSheetTexture.setTexture(new Texture(spriteSheetPixmap));
		
		spriteSheetPixmap.dispose();
	}
	
	
}
