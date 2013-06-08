package com.dddbomber.bgj.entity;

import java.awt.event.KeyEvent;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Bitmap;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Light;
import com.dddbomber.bgj.room.Room;

public class Player extends Entity{
	public Player(){
		x = 100;
		y = 100;
		xSize = 16;
		ySize = 16;
	}
	
	public int shootDelay, anim, animDelay;
	
	public void tick(InputHandler input, Room room){
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
			room.entities.add(new Bullet((int)x-1, (int)y-1, Math.sin(angleTo)*5, Math.cos(angleTo)*5, Math.toDegrees(angleTo)));
		}
		
		if(ox == (int) x && oy == (int) y)return;
		animDelay++;
		if(animDelay > 7){
			animDelay = 0;
			anim++;
			if(anim >= 8)anim = 0;
		}
	}
	
	public void render(Screen screen, Room room){
		double angleTo = Math.atan2(room.mouseX+4-x-8, room.mouseY+4-y-8);

		screen.drawRotated(Asset.player, (int)x-16, (int)y-16, anim%4*48, anim/4*48, 48, 48, (int)(Math.toDegrees(angleTo)));
		
		room.lights.add(new Light((int)x+8, (int)y+8, 32));
		for(int io = 0; io < 8; io++){
			room.lights.add(new Light((int) (x+8+Math.sin(angleTo)*20*io), (int) (y+8+Math.cos(angleTo)*20*io), io*5+10));
		}
	}
}
