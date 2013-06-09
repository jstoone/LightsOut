package com.dddbomber.bgj.entity;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Light;
import com.dddbomber.bgj.room.Room;

public class WerewolfDeath extends Entity{
	public int rotation;
	
	public WerewolfDeath(double x, double y, double angleTo){
		this.x = x;
		this.y = y;
		rotation = (int) angleTo;
	}
	
	public int timeLeft = 0;
	
	public void tick(InputHandler input, Room room){
		if(timeLeft < 23){
			timeLeft++;
		}else{
			removed = true;
		}
	}
	
	public void render(Screen screen, Room room){
		screen.drawRotated(Asset.enemyDeath, (int) x, (int) y, (timeLeft/3)%4*48, (timeLeft/3)/4*48, 48, 48, rotation);
	}
}
