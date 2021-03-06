package com.dddbomber.bgj.room;

import com.dddbomber.bgj.assets.Screen;

public class Tile {
	public static int idToGive;
	public static Tile[] tiles = new Tile[24];
	
	public static Tile testTile = new ComplexTile(0);
	public static Tile grate = new ComplexTile(1);
	public static Tile back = new ComplexTile(2).setSolid();
    public static Tile lightOn = new LightTile(true).setSolid();
    public static Tile lightOff = new LightTile(false).setSolid();
	public static Tile tank = new TankTile(0).setSolid().setDec();
    public static Tile spawnTile = new SpawnTile();
    public static Tile doorTile = new DoorTile().setSolid();
    

    public static Tile teleporter = new TeleporterTile();

	public int id;
	
	public Tile(){
		this.id = idToGive;
		tiles[idToGive++] = this;
	}

	public Tile setSolid(){
		this.solid = true;
		return this;
	}
	
	public Tile setDec(){
		this.isDec = true;
		return this;
	}
	
	private boolean solid = false;
	public boolean isDec;

	public boolean isSolid(Room room){
		return solid;
	}
	
	public void render(Screen screen, Room room, int xt, int yt){
		
	}

	public static Tile get(int i) {
		return tiles[i];
	}
}
