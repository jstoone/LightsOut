package com.dddbomber.bgj.entity;

import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Room;

public class Entity {
	public double x, y;
	public int xSize, ySize;
	public boolean removed = false;
	
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
				}
			}
		}
		return canPass;
	}
}
