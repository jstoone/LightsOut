package com.dddbomber.bgj.room;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.dddbomber.bgj.assets.Bitmap;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.entity.Entity;
import com.dddbomber.bgj.entity.Player;
import com.dddbomber.bgj.input.InputHandler;

public class Room {
	public static int w = 20, h = 13;
	public int[] tiles = new int[w * h];
	
	public int playerX = 100, playerY = 100, mouseX, mouseY;

	public ArrayList<Light> lights = new ArrayList<Light>();
	public ArrayList<LightHandler> lightHandlers = new ArrayList<LightHandler>();
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public Room(){
		for(int x = 0; x < w; x++){
			tiles[x] = Tile.back.id;
			tiles[x+12*w] = Tile.back.id;
		}
		for(int y = 0; y < h; y++){
			tiles[y*w] = Tile.back.id;
			tiles[19+y*w] = Tile.back.id;
		}
		
		entities.add(new Player());
	}
	
	public void render(Screen screen){
		for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
            	Tile t = getTile(x, y);
            	t.render(screen, this, x, y);
            }
        }
		
		for(Entity e : entities){
			e.render(screen, this);
		}
		
		for(int i = 0; i < 2; i++){
			Bitmap b = new Bitmap(screen.width, screen.height);
			for(Light l : lights){
				b.renderLight(l.x, l.y , l.rad+i*32);
			}
			screen.overlay(b, 0, 0, 0, i*20+50);
		}
		lights.clear();
	}

	public Tile getTile(int xt, int yt) {
		if(xt >= w || yt >= h || xt < 0 || yt < 0)return Tile.back;
		return Tile.get(tiles[xt + yt * w]);
	}

	public void tick(InputHandler input) {
		mouseX = input.mouse.x;
		mouseY = input.mouse.y;
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.tick(input, this);
			if(e.removed){
				entities.remove(i--);
			}
		}

        for(int i = 0; i < lightHandlers.size(); i++){
            LightHandler lh = lightHandlers.get(i);
            lh.tick(this);
            if(lh.time == 0){
                lightHandlers.remove(i--);
            }
        }
	}
}
