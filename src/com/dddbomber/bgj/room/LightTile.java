package com.dddbomber.bgj.room;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;

public class LightTile extends Tile {

    public boolean lightOn;

    public LightTile(boolean lightOn){
        this.lightOn = lightOn;
    }

    public void render(Screen screen, Room room, int xt, int yt){
        screen.draw(Asset.tiles, xt*24, yt*24, 24, 24, 24, 24);

        screen.draw(Asset.tiles, xt*24, yt*24,lightOn?72:48, 216, 24, 24);
        if(lightOn){
            room.lights.add(new Light(xt*24+12, yt*24+12, 24+room.lightLevel));
        }
    }
}

