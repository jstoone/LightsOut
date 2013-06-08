package com.dddbomber.bgj.entity;

import java.awt.event.KeyEvent;

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
		screen.fill(x, y, 16, 16, 0xff00ff, 100);
		
		room.lights.add(new Light(x+8, y+8, 32));
		double angleTo = Math.atan2(room.mouseX - x+8, room.mouseY - y+8);
		for(int io = 0; io < 10; io++){
			room.lights.add(new Light((int) (x+8+Math.sin(angleTo)*25*io), (int) (y+8+Math.cos(angleTo)*25*io), io*10+8));
		}
	}
}
