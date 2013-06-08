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
		Tile ut = room.getTile(xt, yt-1),
			 dt = room.getTile(xt, yt+1),
			 lt = room.getTile(xt-1, yt),
			 rt = room.getTile(xt+1, yt);

		Tile ult = room.getTile(xt-1, yt-1),
			 urt = room.getTile(xt+1, yt-1),
			 dlt = room.getTile(xt-1, yt+1),
			 drt = room.getTile(xt+1, yt+1);
		
		boolean u = ut != this && !ut.isDec,
				d = dt != this && !dt.isDec, 
				l = lt != this && !lt.isDec, 
				r = rt != this && !rt.isDec;

		boolean ul = ult != this && !ult.isDec,
				ur = urt != this && !urt.isDec, 
				dl = dlt != this && !dlt.isDec, 
				dr = drt != this && !drt.isDec;

		if(l && r){
			if(u){
				screen.draw(Asset.tiles, xt*24, yt*24, 72, tex, 24, 24);
			}else if(d){
				screen.draw(Asset.tiles, xt*24, yt*24, 72, tex+48, 24, 24);
			}else{
				screen.draw(Asset.tiles, xt*24, yt*24, 72, tex+24, 24, 24);
			}
		}else if(u && d){
			if(l){
				screen.draw(Asset.tiles, xt*24, yt*24, 96, tex+48, 24, 24);
			}else if(r){
				screen.draw(Asset.tiles, xt*24, yt*24, 144, tex+48, 24, 24);
			}else{
				screen.draw(Asset.tiles, xt*24, yt*24, 120, tex+48, 24, 24);
			}
		}else if(u){
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
			if(ur){
				screen.draw(Asset.tiles, xt*24, yt*24, 192, tex, 24, 24);
			}else{
				screen.draw(Asset.tiles, xt*24, yt*24, 120, 24+tex, 24, 24);
			}
		}else if(ur){
			if(dr){
				screen.draw(Asset.tiles, xt*24, yt*24, 216, tex, 24, 24);
			}else{
				screen.draw(Asset.tiles, xt*24, yt*24, 96, 24+tex, 24, 24);
			}
		}else if(dr){
			if(dl){
				screen.draw(Asset.tiles, xt*24, yt*24, 216, tex+24, 24, 24);
			}else{
				screen.draw(Asset.tiles, xt*24, yt*24, 96, tex, 24, 24);
			}
		}else if(dl){
			if(ul){
				screen.draw(Asset.tiles, xt*24, yt*24, 192, tex+24, 24, 24);
			}else{
				screen.draw(Asset.tiles, xt*24, yt*24, 120, tex, 24, 24);
			}
		}else{
			screen.draw(Asset.tiles, xt*24, yt*24, 24, 24+tex, 24, 24);
		}
	}
}
