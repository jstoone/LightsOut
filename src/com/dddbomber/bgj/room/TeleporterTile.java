package com.dddbomber.bgj.room;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;

public class TeleporterTile extends Tile {

	public void render(Screen screen, Room room, int xt, int yt){
        screen.draw(Asset.tiles, xt*24, yt*24, 24, 96, 24, 24);
		
		Tile ut = room.getTile(xt, yt-1),
				dt = room.getTile(xt, yt+1),
				lt = room.getTile(xt-1, yt),
				rt = room.getTile(xt+1, yt);

		boolean u = ut != this,
				d = dt != this, 
				l = lt != this, 
				r = rt != this;

		if(u && l)screen.draw(Asset.tiles, xt*24, yt*24, 0, 288, 24, 24);
		else if(u && r)screen.draw(Asset.tiles, xt*24, yt*24, 48, 288, 24, 24);
		else if(u)screen.draw(Asset.tiles, xt*24, yt*24, 24, 288, 24, 24);

		else if(d && l)screen.draw(Asset.tiles, xt*24, yt*24, 0, 336, 24, 24);
		else if(d && r)screen.draw(Asset.tiles, xt*24, yt*24, 48, 336, 24, 24);
		else if(d)screen.draw(Asset.tiles, xt*24, yt*24, 24, 336, 24, 24);
		

		else if(l)screen.draw(Asset.tiles, xt*24, yt*24, 0, 312, 24, 24);
		else if(r)screen.draw(Asset.tiles, xt*24, yt*24, 48, 312, 24, 24);
		else screen.draw(Asset.tiles, xt*24, yt*24, 24, 312, 24, 24);
	}
}
