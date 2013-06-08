package com.dddbomber.bgj.entity;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Room;

public class Werewolf extends Enemy{
	public Werewolf(int x, int y){
		this.x = x;
		this.y = y;
		xSize = 16;
		ySize = 16;
	}
	
	public void tick(InputHandler input, Room room){
		double angleTo = Math.atan2(room.player.x+8-x-8, room.player.y+8-y-8);
		x += Math.sin(angleTo)*0.25;
		y += Math.cos(angleTo)*0.25;
	}
	
	public void render(Screen screen, Room room){
		double angleTo = Math.atan2(room.player.x+8-x-8, room.player.y+8-y-8);

		screen.drawRotated(Asset.enemy, (int)x-16, (int)y-16, 0, 0, 48, 48, (int)(Math.toDegrees(angleTo)));
		
	}
}
