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
		int xo = room.time%160/10;
		if(xo >= 8){
			xo = 7-(xo-8);
		}
		xo *= 72;
		

		if(u && l)screen.draw(Asset.teleporter, xt*24, yt*24, 0+xo, 0, 24, 24);
		else if(u && r)screen.draw(Asset.teleporter, xt*24, yt*24, 48+xo, 0, 24, 24);
		else if(u)screen.draw(Asset.teleporter, xt*24, yt*24, 24+xo, 0, 24, 24);

		else if(d && l)screen.draw(Asset.teleporter, xt*24, yt*24, 0+xo, 48, 24, 24);
		else if(d && r)screen.draw(Asset.teleporter, xt*24, yt*24, 48+xo, 48, 24, 24);
		else if(d)screen.draw(Asset.teleporter, xt*24, yt*24, 24+xo, 48, 24, 24);
		

		else if(l)screen.draw(Asset.teleporter, xt*24, yt*24, 0+xo, 24, 24, 24);
		else if(r)screen.draw(Asset.teleporter, xt*24, yt*24, 48+xo, 24, 24, 24);
		else{
			screen.draw(Asset.teleporter, xt*24, yt*24, 24+xo, 24, 24, 24);
			int light = room.time%160;
			if(light > 80)light = 80-(light-80);
			room.lights.add(new Light(xt*24+12, yt*24+12, 64+light/4));
		}
	}
}
