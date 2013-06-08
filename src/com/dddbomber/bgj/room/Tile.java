package com.dddbomber.bgj.room;

import com.dddbomber.bgj.assets.Screen;

public class Tile {
	public static int idToGive;
	public static Tile[] tiles = new Tile[24];
	
	public static Tile testTile = new ComplexTile(0);
	public static Tile air = new Tile();
	
	public int id;
	
	public Tile(){
		this.id = idToGive;
		tiles[idToGive++] = this;
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public void render(Screen screen, Room room, int xt, int yt){
		
	}

	public static Tile get(int i) {
		return tiles[i];
	}
}
