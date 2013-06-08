package com.dddbomber.bgj.room;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;

public class Tile {
	public static int idToGive;
	public static Tile[] tiles = new Tile[24];
	
	public static Tile testTile = new Tile();
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
		boolean u = room.getTile(xt, yt-1) != this,
				d = room.getTile(xt, yt+1) != this, 
				l = room.getTile(xt-1, yt) != this, 
				r = room.getTile(xt+1, yt) != this;
		if(u){
			if(l){
				screen.draw(Asset.tiles, xt*24, yt*24, 0, 0, 24, 24);
			}else if(r){
				screen.draw(Asset.tiles, xt*24, yt*24, 48, 0, 24, 24);
			}else{
				screen.draw(Asset.tiles, xt*24, yt*24, 24, 0, 24, 24);
			}
		}else if(d){

			if(l){
				screen.draw(Asset.tiles, xt*24, yt*24, 0, 48, 24, 24);
			}else if(r){
				screen.draw(Asset.tiles, xt*24, yt*24, 48, 48, 24, 24);
			}else{
				screen.draw(Asset.tiles, xt*24, yt*24, 24, 48, 24, 24);
			}
		}else if(l){
			screen.draw(Asset.tiles, xt*24, yt*24, 0, 24, 24, 24);
		}else if(r){
			screen.draw(Asset.tiles, xt*24, yt*24, 48, 24, 24, 24);
		}else{
			screen.draw(Asset.tiles, xt*24, yt*24, 24, 24, 24, 24);
		}
	}

	public static Tile get(int i) {
		return tiles[i];
	}
}
