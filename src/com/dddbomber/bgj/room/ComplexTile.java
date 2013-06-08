package com.dddbomber.bgj.room;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;

public class ComplexTile extends Tile{
	public int tex;
	public ComplexTile(int tex){
		super();
		this.tex = tex*72;
	}
	
	public void render(Screen screen, Room room, int xt, int yt){
		boolean u = room.getTile(xt, yt-1) != this,
				d = room.getTile(xt, yt+1) != this, 
				l = room.getTile(xt-1, yt) != this, 
				r = room.getTile(xt+1, yt) != this;

		boolean ul = room.getTile(xt-1, yt-1) != this,
				ur = room.getTile(xt+1, yt-1) != this, 
				dl = room.getTile(xt-1, yt+1) != this, 
				dr = room.getTile(xt+1, yt+1) != this;
		
		if(u){
			if(l){
				screen.draw(Asset.tiles, xt*24, yt*24, 0, tex, 24, 24);
			}else if(r){
				screen.draw(Asset.tiles, xt*24, yt*24, 48, tex, 24, 24);
			}else{
				screen.draw(Asset.tiles, xt*24, yt*24, 24, tex, 24, 24);
			}
		}else if(d){
			if(l){
				screen.draw(Asset.tiles, xt*24, yt*24, 0, 48+tex, 24, 24);
			}else if(r){
				screen.draw(Asset.tiles, xt*24, yt*24, 48, 48+tex, 24, 24);
			}else{
				screen.draw(Asset.tiles, xt*24, yt*24, 24, 48+tex, 24, 24);
			}
		}else if(l){
			screen.draw(Asset.tiles, xt*24, yt*24, 0, 24+tex, 24, 24);
		}else if(r){
			screen.draw(Asset.tiles, xt*24, yt*24, 48, 24+tex, 24, 24);
		}else if(ul){
			screen.draw(Asset.tiles, xt*24, yt*24, 120, 24+tex, 24, 24);
		}else if(ur){
			screen.draw(Asset.tiles, xt*24, yt*24, 96, 24+tex, 24, 24);
		}else if(dl){
			screen.draw(Asset.tiles, xt*24, yt*24, 120, tex, 24, 24);
		}else if(dr){
			screen.draw(Asset.tiles, xt*24, yt*24, 96, tex, 24, 24);
		}else{
			screen.draw(Asset.tiles, xt*24, yt*24, 24, 24+tex, 24, 24);
		}
	}
}
