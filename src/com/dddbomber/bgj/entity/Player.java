package com.dddbomber.bgj.entity;

import java.awt.event.KeyEvent;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Bitmap;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Light;
import com.dddbomber.bgj.room.LightHandler;
import com.dddbomber.bgj.room.Room;
import com.dddbomber.bgj.room.Tile;

public class Player extends Entity{
	public Player(){
		x = 100;
		y = 100;
		xSize = 16;
		ySize = 16;
	}
	
	public int shootDelay, anim, animDelay;
	public double angleTo;
	
	public int teleportDelay = 0;
	public boolean starting = true;
	
	public void tick(InputHandler input, Room room){
		if(starting){
			if(room.time % 2 == 0)teleportDelay++;
			if(teleportDelay == 100){
				starting = false;
			}
		}
		
		angleTo = Math.atan2(room.mouseX+4-x-8, room.mouseY+4-y-8);
		
		int ox = (int) x;
		int oy = (int) y;
		
		if(input.keyboard.keys[KeyEvent.VK_W])if(canPass(room, 0, -2))y-=2;
		if(input.keyboard.keys[KeyEvent.VK_S])if(canPass(room, 0, 2))y+=2;
		if(input.keyboard.keys[KeyEvent.VK_A])if(canPass(room, -2, 0))x-=2;
		if(input.keyboard.keys[KeyEvent.VK_D])if(canPass(room, 2, 0))x+=2;
		if(shootDelay > 0)shootDelay--;
		if(input.mouse.left && shootDelay == 0){
			shootDelay = 20;
			double angleTo = Math.atan2(room.mouseX+4-x-8, room.mouseY+4-y-8);

            int m = 16 - 24;
            int n = 40 - 24;
            int j = ((int) (m * Math.cos(angleTo) + n * Math.sin(angleTo))) + 24;
            int k = ((int) (n * Math.cos(angleTo) - m * Math.sin(angleTo))) + 24;
            
			room.entities.add(new Bullet((int)x+j-22, (int)y+k-22, Math.sin(angleTo)*5, Math.cos(angleTo)*5, Math.toDegrees(angleTo)));
		}
		
		if(ox == (int) x && oy == (int) y)return;
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
				if(level.getTile(xt, yt).solid){
					canPass = false;
				}else{
					if(level.getTile(xt, yt) == Tile.lightOff){
                        level.lightHandlers.add(new LightHandler(xt, yt, 300));
                        level.tiles[xt+yt*Room.w] = Tile.lightOn.id;
                    }
				}
			}
		}
		return canPass;
	}
	
	public void render(Screen screen, Room room){
		screen.drawRotatedTrans(Asset.player, (int)x-16, (int)y-16, anim%4*48, anim/4*48, 48, 48, (int)(Math.toDegrees(angleTo)), teleportDelay);
		
		room.lights.add(new Light((int)x+8, (int)y+8, 32));
		for(int io = 0; io < 8; io++){
			room.lights.add(new Light((int) (x+8+Math.sin(angleTo)*20*io*teleportDelay*0.01), (int) (y+8+Math.cos(angleTo)*20*io*teleportDelay*0.01), io*5+10-(100-teleportDelay)/2));
		}
	}
}
