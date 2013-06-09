package com.dddbomber.bgj.room;

import java.util.Random;

import com.dddbomber.bgj.assets.Sound;
import com.dddbomber.bgj.entity.Spider;
import com.dddbomber.bgj.entity.SpittingWerewolf;
import com.dddbomber.bgj.entity.Werewolf;

public class RoomGenerator {
	public static Room getBasicRoom(){
		Random random = new Random();
		Room room = new Room();
		
		if(random.nextInt(8)==0){
			for(int i = 0; i < Room.w*Room.h; i++){
				room.tiles[i] = Tile.grate.id;
			}
			for(int x = 0; x < Room.w; x++){
				room.tiles[x] = Tile.back.id;
				room.tiles[x+12*Room.w] = Tile.back.id;
			}
			for(int y = 0; y < Room.h; y++){
				room.tiles[y*Room.w] = Tile.back.id;
				room.tiles[19+y*Room.w] = Tile.back.id;
			}
			for(int x = 0; x < 3; x++){
	            for(int y = 0; y < 3; y++){
	                room.tiles[(x + 1)+(y + 1)*Room.w] = Tile.teleporter.id;
	            }
	        }
            room.player.x = 52;
            room.player.y = 52;

            for(int x = 0; x < 5; x++){
            	for(int y = 0; y < 3; y++){
            		if(random.nextInt(3)==0)continue;
            		room.tiles[(x*3+5)+(y*3+2)*Room.w] = Tile.lightOff.id-(random.nextInt(3)==0 ? 1 : 0);
            		if(x == 4)continue;
            		room.tiles[(x*3+6)+(y*3+2)*Room.w] = Tile.tank.id;
            		room.tiles[(x*3+7)+(y*3+2)*Room.w] = Tile.tank.id;
            	}
            }

            while(room.entities.size() == 1){
            	if(random.nextInt(3)==0)room.entities.add(new Spider(50, 150));
            	if(random.nextInt(3)==0)room.entities.add(new Spider(100, 150));
            	if(random.nextInt(3)==0)room.entities.add(new Spider(150, 150));
            	if(random.nextInt(3)==0)room.entities.add(new Spider(250, 150));
            	
            	if(random.nextBoolean())room.entities.add(new SpittingWerewolf(100, 250));
            	if(random.nextBoolean()) room.entities.add(new SpittingWerewolf(200, 250));
            	if(random.nextBoolean())room.entities.add(new SpittingWerewolf(300, 250));
            	if(random.nextBoolean()) room.entities.add(new SpittingWerewolf(400, 250));
            }
            
            Sound.warpstop.play();
			return room;
		}
		
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
        boolean grate = random.nextInt(3)!=0;
        boolean tank = random.nextBoolean() && grate;
        for(int x = 0; x < 5; x++){
            for(int y = 0; y < 3; y++){
                if(grate)room.tiles[(x + xo)+(y + yo)*Room.w] = Tile.grate.id;
                if(tank && y == 1 && x != 0 && x != 4)room.tiles[(x + xo)+(y + yo)*Room.w] = Tile.tank.id;
            }
        }
        for(int y = 0; y < 3; y++){
        	if(!(xo > 10 && !grate))room.tiles[xo-1+(y + yo)*Room.w] = Tile.back.id;
            if(!(xo < 10 && !grate))room.tiles[xo+5+(y + yo)*Room.w] = Tile.back.id;
        }
        
        boolean doorRoom = random.nextBoolean();
        
        if(doorRoom){
        	if(xo < 10){
        		xo = 15;
        	}else{
        		xo = 1;
        	}
        	if(yo < 5){
        		yo = 8;
        	}else{
        		yo = 4;
        	}

        	room.tiles[xo + 0 + yo * Room.w] = Tile.back.id;
        	room.tiles[xo + 1 + yo * Room.w] = Tile.doorTile.id;
        	room.tiles[xo + 2 + yo * Room.w] = Tile.doorTile.id;
        	room.tiles[xo + 3 + yo * Room.w] = Tile.back.id;


        	int xo2 = xo+4;
        	if(xo < 5){
        		xo--;
        		xo2--;
        	}
        	for(int i = 0; i < 5; i++){
        		if(yo == 4){
        			room.tiles[xo + (yo-i) * Room.w] = Tile.back.id;
        			room.tiles[xo2 + (yo-i) * Room.w] = Tile.back.id;
        		}else{
        			room.tiles[xo + (yo+i) * Room.w] = Tile.back.id;
        			room.tiles[xo2 + (yo+i) * Room.w] = Tile.back.id;
        		}
        	}

        	if(yo == 4){
        		yo = 1;
        	}else{
        		yo++;
        	}
        	for(int x = 0; x < 3; x++){
        		for(int y = 0; y < 3; y++){
        			room.tiles[xo + x + 1 + (yo+y) * Room.w] = Tile.grate.id;
        			if(x == 1 && y == 1)room.tiles[xo + x + 1 + (yo+y) * Room.w] = Tile.lightOff.id;
        		}
        	}
        }

        while(room.entities.size() == 1){
        	if(random.nextInt(3)==0)room.entities.add(new Spider(50, 150));
        	if(random.nextInt(3)==0)room.entities.add(new Spider(100, 150));
        	if(random.nextInt(3)==0)room.entities.add(new Spider(150, 150));
        	if(random.nextInt(3)==0)room.entities.add(new Spider(250, 150));

        	if(random.nextInt(3)==0)room.entities.add(new Werewolf(200, 150));
        	if(random.nextInt(3)==0)room.entities.add(new Werewolf(300, 150));
        	if(random.nextInt(3)==0)room.entities.add(new SpittingWerewolf(200, 100));
        	if(random.nextInt(3)==0) room.entities.add(new SpittingWerewolf(300, 100));
        }

        Sound.warpstop.play();
		return room;
	}
}
