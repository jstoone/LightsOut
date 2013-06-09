package com.dddbomber.bgj.menu;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.entity.Entity;
import com.dddbomber.bgj.entity.Spider;
import com.dddbomber.bgj.entity.SpittingWerewolf;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Room;
import com.dddbomber.bgj.room.RoomGenerator;
import com.dddbomber.bgj.room.Tile;

public class GameMenu extends Menu{
	
	Room room = new Room();
	
	public int progress = 0;
	
	public GameMenu(){
		room.msgToDisplay = "MOVE WITH WASD / MOUSE TO AIM";
		room.msgToDisplay2 = "TOUCH LIGHTS TO TURN THEM ON";
		room.entities.clear();
		room.entities.add(room.player);
		for(int xl = 0; xl < 2; xl++){
        	for(int yl = 0; yl < 2; yl++){
        			room.tiles[xl*13 + 3 + (yl*6+3) * Room.w] = Tile.lightOff.id;
        	}
        }
		for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                room.tiles[(x + 1)+(y + 1)*Room.w] = Tile.teleporter.id;
            }
        }
		
		
        room.player.x = 52;
        room.player.y = 52;
	}

	public void tick(InputHandler input) {
		room.tick(input);
		if(room.roomFinished){
			progress++;
			int health = room.player.health;
			String msg = room.msgToDisplay;
			room = RoomGenerator.getBasicRoom();
			room.player.health = health;
			if(msg.equals("MOVE WITH WASD / MOUSE TO AIM")){
				room.msgToDisplay = "LEFT CLICK TO SHOOT";
				room.msgToDisplay2 = "KILL THE ENEMIES AND TURN ON THE LIGHTS";
			}
		}
	}

	public void render(Screen screen) {
		room.render(screen);
		screen.drawScaledString(Asset.ship, 4, 4, 0, 0, 32, 80, 0xbc0000, 1);
		screen.drawScaledString(Asset.ship, 4, 4, 0, 0, 32, (progress+(room.lightsOff==0 ? 1 : 0))*5, 0xbcffbc, 1);
	}

}
