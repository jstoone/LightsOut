package com.dddbomber.bgj.entity;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Light;
import com.dddbomber.bgj.room.Room;

public class Bullet extends Entity{
	
	public double xSpeed, ySpeed;
	public int rotation;
	
	public Bullet(int x, int y, double xSpeed, double ySpeed, double angleTo){
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		xSize = 12;
		ySize = 12;
		this.x = x;
		this.y = y;
		rotation = (int) angleTo;
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
		screen.drawRotated(Asset.bullet, (int) x - 3, (int) y - 3, 0, 0, 18, 18, rotation);
		room.lights.add(new Light((int)x+6, (int)y+6, 0));
	}
}
