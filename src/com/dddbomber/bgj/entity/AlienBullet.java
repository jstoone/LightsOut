package com.dddbomber.bgj.entity;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.assets.Sound;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Light;
import com.dddbomber.bgj.room.Room;

public class AlienBullet extends Entity{
	
	public double xSpeed, ySpeed;
	public int rotation;
	
	public AlienBullet(int x, int y, double xSpeed, double ySpeed, double angleTo){
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		xSize = 10;
		ySize = 10;
		this.x = x;
		this.y = y;
		rotation = (int) angleTo;
	}

	public int animation = 0;
	
	public void tick(InputHandler input, Room room){
		animation++;
		if(animation >= 10){
			animation = 0;
		}
		if(canPass(room, xSpeed, ySpeed)){
			x += xSpeed;
			y += ySpeed;
			if(this.intersects(room.player)){
				removed = true;
				room.player.damage(xSpeed, ySpeed);
			}
		}else{
			removed = true;
		}
		if(removed){
			
		}
	}
	
	public void render(Screen screen, Room room){
		screen.drawRotated(Asset.alienBullet, (int) x, (int) y, animation/5*10, 0, 10, 10, rotation);
	}
}
