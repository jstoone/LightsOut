package com.dddbomber.bgj.entity;

import java.util.Random;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Room;

public class Spider extends Enemy{
	
	public Spider(int x, int y){
		this.x = x;
		this.y = y;
		xSize = 16;
		ySize = 16;
		solid = true;
		
		health = 3;
	}

	public int anim, animDelay;
	Random random = new Random();
	public double rotation = random.nextInt(360);

	public void tick(InputHandler input, Room room){

		if(!canPass(room, 0, 0)){
			removed = true;
		}
		
		if(seenPlayer){
			rotation = Math.toDegrees(Math.atan2(room.player.x-x-8, room.player.y-y-8));
		}else{
			rotation += 3;
		}

		double xm = Math.sin(Math.toRadians(rotation))*1.25;
		double ym = Math.cos(Math.toRadians(rotation))*1.25;

		if(canPass(room, xm, 0)){
			x += xm;
		}

		if(canPass(room, 0, ym)){
			y += ym;
		}

		animDelay++;
		if(animDelay > 7){
			animDelay = 0;
			anim++;
			if(anim >= 8)anim = 0;
		}
	}
	
	public boolean canPass(Room level, double xm, double ym){
		int xp = (int) (x + xm);
		int yp = (int) (y + ym);
		boolean canPass = true;
		for(int x = xp; x < xp+xSize; x++){
			for(int y = yp; y < yp+ySize; y++){
				int xt = x/24;
				int yt = y/24;
				if(level.getTile(xt, yt).isSolid(level)){
					canPass = false;
				}
			}
		}
		x += xm;
		y += ym;
		for(Entity e : level.entities){
			if(e != this && e.solid && e.x+e.xSize >= x && e.y+e.ySize >= y && e.x <= x+xSize && e.y <= y+ySize){
				if(this.intersects(e)){
					canPass = false;
					if(e == level.player && this.seenPlayer){
						level.player.damage(0, 0);
						removed = true;
					}
				}
			}
		}
		x -= xm;
		y -= ym;
		return canPass;
	}
	
	public void render(Screen screen, Room room){
		screen.drawRotated(Asset.enemySmall, (int)x-4, (int)y-4, (seenPlayer ? 24 : 0), 0, 24, 24, (int)rotation);
	}

	public void damage(double xSpeed, double ySpeed) {
		super.damage(xSpeed, ySpeed);
		seenPlayer = true;
	}
}
