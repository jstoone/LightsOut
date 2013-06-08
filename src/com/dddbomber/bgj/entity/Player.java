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
	
	public int shootDelay;
	
	public void tick(InputHandler input, Room room){
		if(input.keyboard.keys[KeyEvent.VK_W])if(canPass(room, 0, -2))y-=2;
		if(input.keyboard.keys[KeyEvent.VK_S])if(canPass(room, 0, 2))y+=2;
		if(input.keyboard.keys[KeyEvent.VK_A])if(canPass(room, -2, 0))x-=2;
		if(input.keyboard.keys[KeyEvent.VK_D])if(canPass(room, 2, 0))x+=2;
		if(shootDelay > 0)shootDelay--;
		if(input.mouse.left && shootDelay == 0){
			shootDelay = 30;
			double angleTo = Math.atan2(room.mouseX+4-x-8, room.mouseY+4-y-8);
			room.entities.add(new Bullet((int)x+8, (int)y+8, Math.sin(angleTo)*5, Math.cos(angleTo)*5));
		}
	}
	
	public void render(Screen screen, Room room){
		double angleTo = Math.atan2(room.mouseX - x+8, room.mouseY - y+8);
		
		/*Bitmap b = new Bitmap(32, 32);
		b.fill(0, 0, 32, 32, -2, 100);
		b.fill(6, 12, 4, 20, 0x00bcbc, 100);
		
		screen.drawRotated(b, x-8, y-8, 0, 0, 32, 32, (int)(Math.toDegrees(angleTo)));
		
		b = new Bitmap(16, 16);
		b.fill(0, 0, 16, 16, -2, 100);
		b.fill(0, 2, 16, 12, 0xbcbc00, 100);
		
		screen.drawRotated(b, x, y, 0, 0, 16, 16, (int)(Math.toDegrees(angleTo)));*/

		screen.drawRotated(Asset.player, (int)x-8, (int)y-8, 32, 0, 32, 32, (int)(Math.toDegrees(angleTo)));
		screen.drawRotated(Asset.player, (int)x-8, (int)y-8, 0, 0, 32, 32, (int)(Math.toDegrees(angleTo)));
		
		room.lights.add(new Light((int)x+8, (int)y+8, 32));
		for(int io = 0; io < 10; io++){
			room.lights.add(new Light((int) (x+8+Math.sin(angleTo)*25*io), (int) (y+8+Math.cos(angleTo)*25*io), io*10+8));
		}
	}
}
