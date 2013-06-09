package com.dddbomber.bgj.entity;

import java.awt.Rectangle;

import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Room;
import com.dddbomber.bgj.room.Tile;

public class Entity {
	public double x, y;
	public int xSize, ySize;
	public boolean removed = false;
	
	public int health = 25;
	public boolean solid = false;
	
	public boolean intersects(Entity e){
		Rectangle tr = new Rectangle((int)x, (int)y, xSize, ySize);
		Rectangle er = new Rectangle((int)e.x, (int)e.y, e.xSize, e.ySize);
		return tr.intersects(er);
	}
	
	public boolean intersectsLarge(Entity e){
		Rectangle tr = new Rectangle((int)x-2, (int)y-2, xSize+4, ySize+4);
		Rectangle er = new Rectangle((int)e.x-2, (int)e.y-2, e.xSize+4, e.ySize+4);
		return tr.intersects(er);
	}
	
	public void tick(InputHandler input, Room room){
		
	}
	
	public void render(Screen screen, Room room){
		
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
		return canPass;
	}

	public void damage(double xSpeed, double ySpeed) {
		if(health > 0){
			health--;
			if(health == 0)removed = true;
		}
	}
}
