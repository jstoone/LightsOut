package com.dddbomber.bgj.room;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.dddbomber.bgj.assets.Bitmap;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;

public class Room {
	public static int w = 20, h = 13;
	public int[] tiles = new int[w * h];
	
	public int playerX = 100, playerY = 100, mouseX, mouseY;
	
	public ArrayList<Light> lights = new ArrayList<Light>();
	
	public Room(){
		tiles[66] = 1;
		tiles[67] = 1;
		tiles[87] = 1;

		for(int x = 0; x < w; x++){
			tiles[x] = 2;
			tiles[x+12*w] = 2;
		}
		for(int y = 0; y < h; y++){
			tiles[y*w] = 2;
			tiles[19+y*w] = 2;
		}
	}
	
	public void render(Screen screen){
		for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
            	Tile t = getTile(x, y);
            	t.render(screen, this, x, y);
            }
        }
		lights.add(new Light(playerX, playerY, 32));
		double angleTo = Math.atan2(mouseX - playerX, mouseY - playerY);
		for(int io = 0; io < 10; io++){
			lights.add(new Light((int) (playerX+Math.sin(angleTo)*25*io), (int) (playerY+Math.cos(angleTo)*25*io), io*10+8));
		}
		
		for(int i = 0; i < 3; i++){
			Bitmap b = new Bitmap(screen.width, screen.height);
			for(Light l : lights){
				b.renderLight(l.x, l.y , l.rad+i*32);
			}
			screen.overlay(b, 0, 0, 0, i*10+30);
		}
		lights.clear();
	}

	public Tile getTile(int xt, int yt) {
		if(xt >= w || yt >= h || xt < 0 || yt < 0)return Tile.back;
		return Tile.get(tiles[xt + yt * w]);
	}

	public void tick(InputHandler input) {
		if(input.keyboard.keys[KeyEvent.VK_W])playerY-=2;
		if(input.keyboard.keys[KeyEvent.VK_S])playerY+=2;
		if(input.keyboard.keys[KeyEvent.VK_A])playerX-=2;
		if(input.keyboard.keys[KeyEvent.VK_D])playerX+=2;
		mouseX = input.mouse.x;
		mouseY = input.mouse.y;
	}
}
