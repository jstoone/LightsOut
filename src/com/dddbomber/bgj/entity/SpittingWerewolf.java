package com.dddbomber.bgj.entity;

import java.util.Random;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.assets.Sound;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Room;
import com.dddbomber.bgj.room.Tile;

public class SpittingWerewolf extends Werewolf{
	
	public static int[] roarAnim = {
		0, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 0
	};
	
	public SpittingWerewolf(int x, int y){
		super(x, y);
		health = 8;
		seeDelay = 48;
	}
	
	public int attackDelay;
	
	Random random = new Random();
	
	public void tick(InputHandler input, Room room){
		if(!canPass(room, 0, 0)){
			removed = true;
		}
		
		if(seenPlayer){
			targetX = (int) (room.player.x+8);
			targetY = (int) (room.player.y+8);
			
			double targetAngle = Math.toDegrees(Math.atan2(targetX-x-8, targetY-y-8));
			targetAngle = getAngleDifference(Math.toDegrees(angleTo), targetAngle);
			if(targetAngle > 1){
				angleTo-=0.1;
			}else if(targetAngle < -1){
				angleTo+=0.1;
			}

			double xm = Math.sin(angleTo)*0.05;
			double ym = Math.cos(angleTo)*0.05;
			
			if(canPass(room, xm, 0)){
				x += xm;
			}
			
			if(canPass(room, 0, ym)){
				y += ym;
			}
			if(hitDelay == 0)attackDelay++;
			if(attackDelay > 45){
				attackDelay = -15;
				seeDelay = 47;
			}
		}else{
			if(random.nextInt(250)==0){
				changeTarget();
			}
		}
		
		if(hitDelay > 0)hitDelay--;
		if(seeDelay < 48 && seeDelay > 0){
			seeDelay--;
			if(seeDelay == 12 || seeDelay == 36){
				room.entities.add(new AlienBullet((int)x+4, (int)y+4, Math.sin(angleTo)*3, Math.cos(angleTo)*3, Math.toDegrees(angleTo)));
			}
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

		if(removed){
			room.entities.add(new WerewolfDeath(x, y, Math.toDegrees(angleTo)));
		}
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
                        if(seeDelay == 48){
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
		if(seeDelay < 48 && seeDelay > 0){
			screen.drawRotated(Asset.enemyRoar, (int)x-14, (int)y-14, roarAnim[seeDelay/4]*48, 0, 48, 48, (int)(Math.toDegrees(angleTo)));
		}else if(hitDelay > 0){
			screen.drawRotated(Asset.enemyHit, (int)x-14, (int)y-14, hitDelay/5*48, 0, 48, 48, (int)(Math.toDegrees(angleTo)));
		}else{
			screen.drawRotated(Asset.enemy, (int)x-14, (int)y-14, anim%4*48, anim/4*48+(seenPlayer ? 96 : 0), 48, 48, (int)(Math.toDegrees(angleTo)));
		}		
	}

	public void damage(double xSpeed, double ySpeed) {
		if(!invinsible){
			super.damage(xSpeed, ySpeed);
			hitDelay = 9;
		}else if(seeDelay == 48){
			seeDelay--;
			Sound.roar.play();
		}
	}
}
