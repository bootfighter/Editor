package com.mygdx.codeAssets.Handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.codeAssets.Objects.Tile;


public class TileHandler {
	private Tile selcetedTile;
	private String tilePath = " ";
	private ArrayList<Tile> totalTile = new ArrayList<Tile>();
	
	
	public TileHandler(File a_input) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(a_input));
		Path path = Paths.get(".");
		System.out.println(path.toAbsolutePath().toString());
		
		tilePath = br.readLine();
		
		while(tilePath != null) {
			System.out.println(tilePath);
			totalTile.add(new Tile(new Texture("../core/assets/" + tilePath), false));
			tilePath = br.readLine();
		}
		
		selectTile(1);
		
		br.close();
	}
	
	public Tile getSelectedTile() {
		return selcetedTile;
	}
	
	public void selectTile(int a_tileNr) {
		selcetedTile = totalTile.get(a_tileNr);
	}
	
	
	
	
}
