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
		xSize = 12;
		ySize = 12;
		solid = true;
		
		health = 3;
	}

	public int anim, animDelay;
	public double rotation;

	public void tick(InputHandler input, Room room){
		rotation = Math.toDegrees(Math.atan2(room.player.x-x-8, room.player.y-y-8));

		double xm = Math.sin(Math.toRadians(rotation))*1.75;
		double ym = Math.cos(Math.toRadians(rotation))*1.75;

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
				if(this.intersects(e))canPass = false;
			}
		}
		x -= xm;
		y -= ym;
		return canPass;
	}
	
	public void render(Screen screen, Room room){
		screen.drawRotated(Asset.enemySmall, (int)x-2, (int)y-2, 0, 0, 16, 16, (int)rotation);
	}

	public void damage(double xSpeed, double ySpeed) {
		super.damage(xSpeed, ySpeed);
	}
}
