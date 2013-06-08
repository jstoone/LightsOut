package com.dddbomber.bgj.entity;

import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Light;
import com.dddbomber.bgj.room.Room;

public class Bullet extends Entity{
	
	public double xSpeed, ySpeed;
	
	public Bullet(int x, int y, double xSpeed, double ySpeed){
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		xSize = 8;
		ySize = 8;
		this.x = x;
		this.y = y;
	}

	public void tick(InputHandler input, Room room){
		if(canPass(room, xSpeed, ySpeed)){
			x += xSpeed;
			y += ySpeed;
		}else{
			removed = true;
		}
	}
	
	public void render(Screen screen, Room room){
		screen.fill((int)x, (int)y, 8, 8, 0xbcff00, 100);
	}
}
