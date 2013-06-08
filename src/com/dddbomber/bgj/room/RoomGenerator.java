package com.dddbomber.bgj.room;

import java.util.Random;

public class RoomGenerator {
	public static Room getBasicRoom(){
		Random random = new Random();
		Room room = new Room();
		int xo = random.nextInt(9)+Room.w/2-4;
		for(int i = 0; i < 2+random.nextInt(3); i++){
			room.tiles[xo + (i+1) * Room.w] = Tile.back.id;
		}
		int yo = random.nextInt(7)+Room.h/2-3;
		for(int i = 0; i < 2+random.nextInt(3); i++){
			room.tiles[i + 1 + yo * Room.w] = Tile.back.id;
		}

        room.tiles[66] = Tile.lightOff.id;
		return room;
	}
}
