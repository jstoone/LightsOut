package com.dddbomber.bgj.menu;

import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Room;
import com.dddbomber.bgj.room.RoomGenerator;

public class GameMenu extends Menu{
	
	Room room = RoomGenerator.getBasicRoom();

	public void tick(InputHandler input) {
		room.tick(input);
		if(room.roomFinished){
			room = RoomGenerator.getBasicRoom();
		}
	}

	public void render(Screen screen) {
		room.render(screen);
	}

}
