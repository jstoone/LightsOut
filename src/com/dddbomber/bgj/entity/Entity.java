package com.dddbomber.bgj.entity;

import java.awt.Rectangle;

import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.LightHandler;
import com.dddbomber.bgj.room.Room;
import com.dddbomber.bgj.room.Tile;

public class Entity {
	public double x, y;
	public int xSize, ySize;
	public boolean removed = false;
	
	public int health = 25;
	
	public boolean intersects(Entity e){
		Rectangle tr = new Rectangle((int)x, (int)y, xSize, ySize);
		Rectangle er = new Rectangle((int)e.x, (int)e.y, e.xSize, e.ySize);
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
				if(level.getTile(xt, yt).solid){
					canPass = false;
                    if(level.getTile(xt, yt) == Tile.lightOff){
                        level.lightHandlers.add(new LightHandler(xt, yt, 300));
                        level.tiles[xt+yt*Room.w] = Tile.lightOn.id;
                    }
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
