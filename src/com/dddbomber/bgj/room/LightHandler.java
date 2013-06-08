package com.dddbomber.bgj.room;

public class LightHandler {

    public int xt, yt, time;

    public LightHandler(int xt, int yt, int time) {
        this.xt = xt;
        this.yt = yt;
        this.time = time;
    }

    public void tick(Room room) {
        if (time > 0) {
            time--;
        }
        if (time == 0) {
            room.tiles[xt + yt * Room.w] = Tile.lightOff.id;
        }
    }
}
