package com.mygdx.fileManagement;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class SpriteSheetPacker {

	
	public static SpriteSheet packSpriteSheet(String a_directory, ArrayList<String> a_textures, int a_spriteSizeX, int a_spriteSizeY,
												int a_spriteSheetDimX,int a_spriteSheetDimY){
		
		int numberOfTextures = a_textures.size();
		int currentXPos = 0;
		int currentYPos = 0;
		Texture spriteSheetTexture;
		Pixmap spriteSheetPixmap = new Pixmap(a_spriteSheetDimX, a_spriteSheetDimX, Format.RGBA8888);
		Pixmap currentSpritePixmap = new Pixmap(a_spriteSizeX, a_spriteSheetDimY, Format.RGBA8888);
		
		
		for (int i = 0; i < numberOfTextures; i++) {
			
			currentXPos = (i * a_spriteSizeX) % a_spriteSheetDimX;
			currentYPos = (i * a_spriteSizeY) / a_spriteSheetDimY;
			
			currentSpritePixmap.dispose();
			currentSpritePixmap = new Pixmap(Gdx.files.internal(a_directory + a_textures.get(i)));
			
			for (int dimX = 0; dimX < a_spriteSizeX; dimX++) {
				
				for (int dimY = 0; dimY < a_spriteSizeY; dimY++) {
					
					spriteSheetPixmap.drawPixel(currentXPos + dimX, currentYPos + dimY, currentSpritePixmap.getPixel(dimX, dimY));
				
				}
			}
		}
		
		
		
		spriteSheetTexture = new Texture(spriteSheetPixmap);
		spriteSheetPixmap.dispose();
		currentSpritePixmap.dispose();
		return new SpriteSheet(spriteSheetTexture, numberOfTextures, a_spriteSizeX, a_spriteSizeY);
	}	
}
