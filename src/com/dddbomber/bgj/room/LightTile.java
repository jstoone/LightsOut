package com.dddbomber.bgj.room;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;

public class LightTile extends Tile {

    public void render(Screen screen, Room room, int xt, int yt){
        screen.draw(Asset.tiles, xt*24, yt*24, 72, 216, 24, 24);
        room.lights.add(new Light(xt*24+12, yt*24+12, 16));
    }
}

