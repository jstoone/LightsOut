package com.dddbomber.bgj.entity;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Light;
import com.dddbomber.bgj.room.Room;

public class WerewolfSlash extends Entity{
	public int rotation;
	
	public WerewolfSlash(double x, double y, double angleTo){
		xSize = 48;
		ySize = 48;
		this.x = x;
		this.y = y;
		rotation = (int) angleTo;
	}

	public boolean damaged = false;
	public int timeLeft = 50;
	
	public void tick(InputHandler input, Room room){
		if(!damaged && this.intersects(room.player)){
			damaged = true;
			room.player.damage(0, 0);
		}
		if(timeLeft > 0){
			timeLeft--;
		}else{
			removed = true;
		}
	}
	
	public void render(Screen screen, Room room){
		screen.drawRotatedTrans(Asset.enemyClawEffect, (int) x, (int) y, 0, 0, 48, 48, rotation, timeLeft*2);
	}
}
