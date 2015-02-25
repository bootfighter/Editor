package com.mygdx.fileManagement;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteSheet{
	
	
	private TextureRegion spriteSheet;
	private int width;
	private int height;
	private int numberOfSprites;
	private int tileSizeX;
	private int tileSizeY;
	private int tileDimensionX;
	private int tileDimensionY;
	
	public SpriteSheet(Texture a_spriteSheet, int a_numberOfSprites, int a_tileSizeX, int a_tileSizeY) {
		spriteSheet = new TextureRegion(a_spriteSheet);
		width = spriteSheet.getRegionWidth();
		height = spriteSheet.getRegionHeight();
		numberOfSprites = a_numberOfSprites;
		tileSizeX = a_tileSizeX;
		tileSizeY = a_tileSizeY;
		tileDimensionX = width / tileSizeX;
		tileDimensionY = height / tileSizeY;
	}
	
	public TextureRegion getSpriteAtIndex(int a_index){
		spriteSheet.setRegion(tileDimensionX % a_index, tileDimensionX / a_index, tileSizeX, tileSizeY);
		return spriteSheet;
	}
	
	public void addSprite(Pixmap a_pixmap){
		
		if (tileDimensionX * tileDimensionY > numberOfSprites)
			return;
		
		//gets pixmap from existing spritesheet
		spriteSheet.getTexture().getTextureData().prepare();
		Pixmap spriteSheetPixmap = spriteSheet.getTexture().getTextureData().consumePixmap();
		
		//calculating position of new sprite
		int posX = tileDimensionX % numberOfSprites;
		int poxY = tileDimensionX / numberOfSprites;
		
		for (int dimX = 0; dimX < tileSizeX; dimX++) {
			for (int dimY = 0; dimY < tileSizeY; dimY++){
				
				//writes pixels from given pixmap onto the existing spritesheet
				spriteSheetPixmap.drawPixel(posX + dimX, poxY + dimY, a_pixmap.getPixel(dimX, dimY));
				
				
			}
		}
		
		spriteSheet.setTexture(new Texture(spriteSheetPixmap));
		
		spriteSheetPixmap.dispose();
	}

}
