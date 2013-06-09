package com.dddbomber.bgj.room;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Bitmap;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.assets.Sound;
import com.dddbomber.bgj.entity.Enemy;
import com.dddbomber.bgj.entity.Entity;
import com.dddbomber.bgj.entity.Player;
import com.dddbomber.bgj.input.InputHandler;

public class Room {
	public static int w = 20, h = 13;
	public int[] tiles = new int[w * h];
	
	public int mouseX, mouseY;
	public Player player;

	public ArrayList<Light> lights = new ArrayList<Light>();
	public ArrayList<LightHandler> lightHandlers = new ArrayList<LightHandler>();
	public ArrayList<Entity> entities = new ArrayList<Entity>();
	public int time;
	
	public int enemies = 0;
	
	public Room(){
		for(int x = 0; x < w; x++){
			tiles[x] = Tile.back.id;
			tiles[x+12*w] = Tile.back.id;
		}
		for(int y = 0; y < h; y++){
			tiles[y*w] = Tile.back.id;
			tiles[19+y*w] = Tile.back.id;
		}
		player = new Player();
		entities.add(player);
	}
	
	public int lightLevel = 0;
	
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
		
		for(int i = 0; i < 1; i++){
			Bitmap b = new Bitmap(screen.width, screen.height);
			for(Light l : lights){
				b.renderLight(l.x, l.y , l.rad+16);
			}
			if(lightLevel != 0)b.fill(0, 0, screen.width, screen.height, lightLevel);
			screen.overlay(b, 0, 0, 0, i*20+50);
		}
		lights.clear();
		
		if(enemies == 0){
			String msg = "INFESTATION CLEARED";
			screen.draw(msg, (int) (screen.width/2-msg.length()*3.5), screen.height-14, 0xbcffbc, 1);
			msg = "TELEPORT TO THE NEXT ROOM";
			screen.draw(msg, (int) (screen.width/2-msg.length()*3.5), screen.height-7, 0xbcffbc, 1);
		}
		for(int i = 0; i < 25; i++)screen.draw(Asset.gui, 2+i*9, 2, 8, 0, 8, 20);
		for(int i = 0; i < player.health; i++)screen.draw(Asset.gui, 2+i*9, 2, 0, 0, 8, 20);
	}

	public Tile getTile(int xt, int yt) {
		if(xt >= w || yt >= h || xt < 0 || yt < 0)return Tile.back;
		return Tile.get(tiles[xt + yt * w]);
	}

	public boolean roomFinished = false;
	
	public void tick(InputHandler input) {
		time++;
		mouseX = input.mouse.x;
		mouseY = input.mouse.y;
		enemies = 0;
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			e.tick(input, this);
			if(e.removed){
				entities.remove(i--);
			}else{
				if(e instanceof Enemy)enemies++;
			}
		}

        for(int i = 0; i < lightHandlers.size(); i++){
            LightHandler lh = lightHandlers.get(i);
            lh.tick(this);
            if(lh.time == 0){
                lightHandlers.remove(i--);
            }
        }

		if(enemies == 0){
			if(lightLevel < 100)lightLevel++;
			if(time % 2 == 0){
				if(getTile((int)player.x/24, (int)player.y/24) == Tile.teleporter){
					player.teleportDelay--;
					if(player.teleportDelay == 99){
						Sound.stopAll();
						Sound.warpstart.play();
					}else if(player.teleportDelay == 50){
						Sound.stopAll();
						Sound.warpback.loop();
					}
					
					teleporting = true;
				}else if(player.teleportDelay < 100){
					player.teleportDelay++;
					if(teleporting){
						Sound.stopAll();
						Sound.warpstop.play();
						teleporting = false;
					}
				}
				if(player.teleportDelay <= 0){
					roomFinished = true;
					Sound.stopAll();
				}
			}
		}
	}
	
	public boolean teleporting = false;
}
