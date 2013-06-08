package com.dddbomber.bgj.entity;

import java.awt.event.KeyEvent;

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
	
	public void tick(InputHandler input, Room room){
		if(input.keyboard.keys[KeyEvent.VK_W])y-=2;
		if(input.keyboard.keys[KeyEvent.VK_S])y+=2;
		if(input.keyboard.keys[KeyEvent.VK_A])x-=2;
		if(input.keyboard.keys[KeyEvent.VK_D])x+=2;
	}
	
	public void render(Screen screen, Room room){
		double angleTo = Math.atan2(room.mouseX - x+8, room.mouseY - y+8);
		
		Bitmap b = new Bitmap(32, 32);
		b.fill(0, 0, 32, 32, -2, 100);
		b.fill(6, 12, 4, 20, 0x00bcbc, 100);
		
		screen.drawRotated(b, x-8, y-8, 0, 0, 32, 32, (int)(Math.toDegrees(angleTo)));
		
		b = new Bitmap(16, 16);
		b.fill(0, 0, 16, 16, -2, 100);
		b.fill(0, 2, 16, 12, 0xbcbc00, 100);
		
		screen.drawRotated(b, x, y, 0, 0, 16, 16, (int)(Math.toDegrees(angleTo)));
		
		room.lights.add(new Light(x+8, y+8, 32));
		for(int io = 0; io < 10; io++){
			room.lights.add(new Light((int) (x+8+Math.sin(angleTo)*25*io), (int) (y+8+Math.cos(angleTo)*25*io), io*10+8));
		}
	}
}
