package com.dddbomber.bgj.room;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;

public class TankTile extends Tile{
	public int tex;

	public TankTile(int tex){

	}

	public void render(Screen screen, Room room, int xt, int yt){
        screen.draw(Asset.tiles, xt*24, yt*24, 24, 96, 24, 24);
		
		if(room.tiles[xt-1+yt*Room.w] != id){
			screen.draw(Asset.tiles, xt*24, yt*24, 0, 240, 24, 24);
		}else if(room.tiles[xt+1+yt*Room.w] != id){
			screen.draw(Asset.tiles, xt*24, yt*24, 48, 240, 24, 24);
		}else{
			screen.draw(Asset.tiles, xt*24, yt*24, 24, 240, 24, 24);
		}
	}
}
