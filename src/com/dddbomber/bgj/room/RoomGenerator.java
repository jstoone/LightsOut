package com.dddbomber.bgj.room;

import java.util.Random;

import com.dddbomber.bgj.assets.Sound;
import com.dddbomber.bgj.entity.Werewolf;

public class RoomGenerator {
	public static Room getBasicRoom(){
		Random random = new Random();
		Room room = new Room();
		int corner = random.nextInt(4);

        if(corner == 0){
            room.player.x = 52;
            room.player.y = 52;
        } else if(corner == 1){
            room.player.x = 52+15*24;
            room.player.y = 52;
        } else if(corner == 2){
            room.player.x = 52+15*24;
            room.player.y = 52+8*24;
        } else if(corner == 3){
            room.player.x = 52;
            room.player.y = 52+8*24;
        }

        int xo = 1;
        int yo = 1;
        if(corner == 1){
        	xo = Room.w - 4;
        } else if(corner == 2){
        	xo = Room.w - 4;
        	yo = Room.h - 4;
        } else if(corner == 3){
        	yo = Room.h - 4;
        }

        for(int xl = 0; xl < 2; xl++){
        	for(int yl = 0; yl < 2; yl++){
        		if(random.nextInt(3)==0){
        			room.tiles[xl*13 + 3 + (yl*6+3) * Room.w] = Tile.lightOn.id;
        		}else if(random.nextInt(3)==0){
        			room.tiles[xl*13 + 3 + (yl*6+3) * Room.w] = Tile.lightOff.id;
        		}
        	}
        }

        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                room.tiles[(x + xo)+(y + yo)*Room.w] = Tile.teleporter.id;
            }
        }
        
        if(xo < 5){
        	xo += 4;
        }else{
        	xo -= 6;
        }
        for(int x = 0; x < 5; x++){
            for(int y = 0; y < 3; y++){
                room.tiles[(x + xo)+(y + yo)*Room.w] = Tile.grate.id;
                if(y == 1 && x != 0 && x != 4)room.tiles[(x + xo)+(y + yo)*Room.w] = Tile.tank.id;
            }
        }
        for(int y = 0; y < 3; y++){
            room.tiles[xo-1+(y + yo)*Room.w] = Tile.back.id;
            room.tiles[xo+5+(y + yo)*Room.w] = Tile.back.id;
        }
        room.entities.add(new Werewolf(200, 200));
        room.entities.add(new Werewolf(300, 200));
        
        Sound.warpstop.play();
		return room;
	}
}
