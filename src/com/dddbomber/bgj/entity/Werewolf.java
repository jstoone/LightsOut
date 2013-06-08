package com.dddbomber.bgj.entity;

import java.util.Random;

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

	public int anim, animDelay;
	
	public int targetX, targetY;
	public double angleTo = 0;
	public boolean seenPlayer = false;
	
	Random random = new Random();
	
	public void tick(InputHandler input, Room room){
		if(seenPlayer){
			targetX = (int) (room.player.x+8);
			targetY = (int) (room.player.y+8);
		}else{
			if(random.nextInt(250)==0){
				changeTarget();
			}
		}
		double targetAngle = Math.atan2(targetX-x-8, targetY-y-8);
		if(angleTo < targetAngle-1){
			angleTo+=0.01;
		}else if(angleTo > targetAngle+1){
			angleTo+=0.01;
		}

		double xm = Math.sin(angleTo)*0.25;
		double ym = Math.cos(angleTo)*0.25;
		
		if(canPass(room, xm, ym)){
			x += xm;
			y += ym;
		}else{
			changeTarget();
		}

		animDelay++;
		if(animDelay > 7){
			animDelay = 0;
			anim++;
			if(anim >= 8)anim = 0;
		}
	}
	
	public void changeTarget(){
		targetX = (int) (x + random.nextInt(21)-10);
		targetY = (int) (y + random.nextInt(21)-10);
	}
	
	public void render(Screen screen, Room room){

		screen.drawRotated(Asset.enemy, (int)x-16, (int)y-16, anim%4*48, anim/4*48, 48, 48, (int)(Math.toDegrees(angleTo)));
		
	}
}
