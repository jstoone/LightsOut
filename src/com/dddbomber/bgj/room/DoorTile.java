package com.dddbomber.bgj.room;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;

public class DoorTile extends Tile{
	public void render(Screen screen, Room room, int xt, int yt){
		boolean left = room.getTile(xt-1, yt) != this;

		screen.draw(Asset.tiles, xt*24, yt*24, 24, 24, 24, 24);
		screen.draw(Asset.door, xt*24, yt*24, room.doorsOpen%5*48+(left ? 0 : 24), room.doorsOpen/5*24, 24, 24);
	}
	


	public boolean isSolid(Room room){
		return room.doorsOpen < 9;
	}
}
