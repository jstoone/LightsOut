package com.dddbomber.bgj.entity;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Light;
import com.dddbomber.bgj.room.Room;

public class BulletHit extends Entity{
	
	public BulletHit(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int anim = 0;

	public void tick(InputHandler input, Room room){
		if(anim < 8){
			anim++;
		}else{
			removed = true;
			room.shakeSmall();
		}
	}
	
	public void render(Screen screen, Room room){
		screen.draw(Asset.bulletHit, (int) x, (int) y, anim/5*16, 0, 16, 16);
		room.lights.add(new Light((int)x+8, (int)y+8, -anim*2));
	}
}
