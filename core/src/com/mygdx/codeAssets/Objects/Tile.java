package com.mygdx.codeAssets.Objects;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Tile {
	
	Texture texture; 
	ArrayList<CollisionRect> collision_boxes;
	private int tileSize = 16;
	
	
	// Constructors
	public Tile(Texture a_texture, ArrayList<CollisionRect> a_collision_boxes){
		texture = a_texture;
		collision_boxes = a_collision_boxes;
	}
	

	public Tile(Texture a_texture, boolean a_is_solid){
		texture = a_texture;
		collision_boxes = new ArrayList<CollisionRect>();
		if (a_is_solid)
			collision_boxes.add(new CollisionRect(new Vector2(0, 0), new Vector2(tileSize, tileSize)));
	}
	
	public Tile(){
		texture = new Texture("missingtxt.png");
		collision_boxes = new ArrayList<CollisionRect>();
		collision_boxes.add(new CollisionRect(new Vector2(0, 0), new Vector2(tileSize, tileSize)));
	}
	
	public ArrayList<CollisionRect> getCollision_boxes() {
		return collision_boxes;
	}
	
}