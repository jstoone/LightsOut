package com.dddbomber.bgj.menu;

import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;

public abstract class Menu {
	private static Menu menu = new GameMenu();
	
	public static void setMenu(Menu m){
		menu = m;
	}
	
	public static Menu getMenu(){
		return menu;
	}
	
	public abstract void tick(InputHandler input);
	
	public abstract void render(Screen screen);
}
