package com.dddbomber.bgj.menu;

import com.dddbomber.bgj.Game;
import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;

public class MainMenu extends Menu{
	
	public int mx = 240, my = 150;
	public boolean down;
	
	public boolean play = false, mouse;
	
	public MainMenu(int time) {
		this.time = time;
	}

	public void tick(InputHandler input) {
		mx = input.mouse.x;
		my = input.mouse.y;
		time++;
		play = false;
		if(input.mouse.y >= 128 && input.mouse.y < 168 && input.mouse.x >= 96 && input.mouse.x <= 384){
			play = true;
		}
		if(input.mouse.left){
			mouse = true;
		}else{
			if(mouse){
				mouse = false;
				if(play){
					Menu.setMenu(new ContextMenu(time));
				}
			}
		}
	}
	
	
	public int time;

	public void render(Screen screen) {
		if(time % 2== 0)screen.fill(0, 0, screen.width, screen.height, 0, 1);
		
		screen.drawRotated(Asset.titleBack, (screen.width-480)/2, (screen.height-480)/2, 0, 0, Asset.titleBack.width, Asset.titleBack.height, time/3);

		String msg = "INTERSTELLAR";
		screen.draw(msg, screen.width/2-msg.length()*14, 16, 0xffbcbc, 4);
		msg = "INFESTATION";
		screen.draw(msg, screen.width/2-msg.length()*14, 48, 0xffbcbc, 4);

		msg = "PLAY";
		screen.draw(msg, screen.width/2-msg.length()*28, 128, play ? 0xffffff : 0xffbcbc, 8);
		

		msg = "BY";
		screen.draw(msg, screen.width/2-msg.length()*7, screen.height-64, 0xffbcbc, 2);
		msg = "DDDBOMBER";
		screen.draw(msg, screen.width/2-msg.length()*7, screen.height-48, 0xffbcbc, 2);
		msg = "JSTOONE";
		screen.draw(msg, screen.width/2-msg.length()*7, screen.height-32, 0xffbcbc, 2);
		msg = "7SOUL";
		screen.draw(msg, screen.width/2-msg.length()*7, screen.height-16, 0xffbcbc, 2);
	}

}
