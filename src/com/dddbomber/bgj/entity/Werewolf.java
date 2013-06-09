package com.dddbomber.bgj.entity;

import java.util.Random;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.assets.Sound;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Room;
import com.dddbomber.bgj.room.Tile;

public class Werewolf extends Enemy{
	
	public static int[] roarAnim = {
		0, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 0
	};
	
	public Werewolf(int x, int y){
		this.x = x;
		this.y = y;
		xSize = 20;
		ySize = 20;
		solid = true;
		
		health = 10;
	}

	public int anim, animDelay, clawAnim;
	
	public int targetX, targetY;
	public double angleTo = 0;
	public boolean seenPlayer = false;
	
	public boolean invinsible = true;
	public int seeDelay = 60, attackDelay;
	
	Random random = new Random();
	
	public double getAngleDifference(double arg1, double arg2){
	    return ((((arg1 - arg2) % 360) + 540) % 360) - 180;
	}
	
	public void tick(InputHandler input, Room room){
		if(seenPlayer){
			targetX = (int) (room.player.x+8);
			targetY = (int) (room.player.y+8);
			
			double targetAngle = Math.toDegrees(Math.atan2(targetX-x-8, targetY-y-8));
			targetAngle = getAngleDifference(Math.toDegrees(angleTo), targetAngle);
			if(targetAngle > 1){
				angleTo-=0.06;
			}else if(targetAngle < -1){
				angleTo+=0.06;
			}

			double xm = Math.sin(angleTo)*0.75;
			double ym = Math.cos(angleTo)*0.75;
			
			if(canPass(room, xm, 0)){
				x += xm;
			}
			
			if(canPass(room, 0, ym)){
				y += ym;
			}
			if(hitDelay == 0)attackDelay++;
			if(attackDelay > 45){
				attackDelay = -15;
				clawAnim = 30;
			}
			if(clawAnim > 0){
				clawAnim--;
				if(clawAnim == 10){
					room.entities.add(new WerewolfSlash(x-14, y-14, (int)(Math.toDegrees(angleTo))));
				}
			}
		}else{
			if(random.nextInt(250)==0){
				changeTarget();
			}
		}
		
		if(hitDelay > 0)hitDelay--;
		if(seeDelay < 60 && seeDelay > 0){
			seeDelay--;
			if(seeDelay == 0){
				seenPlayer = true;
				invinsible = false;
			}
		}

		double targetAngle = Math.toDegrees(Math.atan2(targetX-x-8, targetY-y-8));
		targetAngle = getAngleDifference(Math.toDegrees(angleTo), targetAngle);
		if(targetAngle > 1){
			angleTo-=0.06;
		}else if(targetAngle < -1){
			angleTo+=0.06;
		}

		double xm = Math.sin(angleTo)*0.25;
		double ym = Math.cos(angleTo)*0.25;
		
		if(canPass(room, xm, 0)){
			x += xm;
		}
		
		if(canPass(room, 0, ym)){
			y += ym;
		}

		animDelay++;
		if(animDelay > 7){
			animDelay = 0;
			anim++;
			if(anim >= 8)anim = 0;
		}
		
		angleTo = Math.toRadians(Math.toDegrees(angleTo) % 360);
	}
	
	public void changeTarget(){
		targetX = (int) (x + random.nextInt(21)-10);
		targetY = (int) (y + random.nextInt(21)-10);
	}
	
	public boolean canPass(Room level, double xm, double ym){
		int xp = (int) (x + xm);
		int yp = (int) (y + ym);
		boolean canPass = true;
		for(int x = xp; x < xp+xSize; x++){
			for(int y = yp; y < yp+ySize; y++){
				int xt = x/24;
				int yt = y/24;
				if(level.getTile(xt, yt).isSolid(level)){
					canPass = false;
                    if(level.getTile(xt, yt) == Tile.lightOn){
                        level.tiles[xt+yt*Room.w] = Tile.lightOff.id;
                        if(seeDelay == 60){
                			seeDelay--;
                			Sound.roar.play();
                        }
                    }
				}
			}
		}
		x += xm;
		y += ym;
		for(Entity e : level.entities){
			if(e != this && e.solid && e.x+e.xSize >= x && e.y+e.ySize >= y && e.x <= x+xSize && e.y <= y+ySize){
				if(this.intersects(e))canPass = false;
			}
		}
		x -= xm;
		y -= ym;
		return canPass;
	}
	
	public void render(Screen screen, Room room){
		if(clawAnim > 0){
			screen.drawRotated(Asset.enemyClaw, (int)x-14, (int)y-14, ((25-clawAnim)/5)*48, 0, 48, 48, (int)(Math.toDegrees(angleTo)));
		}else if(seeDelay < 60 && seeDelay > 0){
			screen.drawRotated(Asset.enemyRoar, (int)x-14, (int)y-14, roarAnim[seeDelay/4]*48, 0, 48, 48, (int)(Math.toDegrees(angleTo)));
		}else if(hitDelay > 0){
			screen.drawRotated(Asset.enemyHit, (int)x-14, (int)y-14, hitDelay/5*48, 0, 48, 48, (int)(Math.toDegrees(angleTo)));
		}else{
			screen.drawRotated(Asset.enemy, (int)x-14, (int)y-14, anim%4*48, anim/4*48+(seenPlayer ? 96 : 0), 48, 48, (int)(Math.toDegrees(angleTo)));
		}
		
	}
	
	public int hitDelay;

	public void damage(double xSpeed, double ySpeed) {
		if(!invinsible){
			super.damage(xSpeed, ySpeed);
			hitDelay = 9;
		}else if(seeDelay == 60){
			seeDelay--;
			Sound.roar.play();
		}
	}
}
