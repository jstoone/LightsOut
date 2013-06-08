package com.dddbomber.bgj.room;

import java.util.Random;

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
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                room.tiles[(x + xo)+(y + yo)*Room.w] = Tile.spawnTile.id;
            }
        }
        for(int y = 0; y < 3; y++){
            room.tiles[xo-1+(y + yo)*Room.w] = Tile.back.id;
            room.tiles[xo+3+(y + yo)*Room.w] = Tile.back.id;
        }
		return room;
	}
}
