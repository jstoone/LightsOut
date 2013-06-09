package com.dddbomber.bgj.menu;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;

public class LoseMenu extends Menu{
	
	public int mx = 240, my = 150;
	public boolean down;
	
	public boolean play = false, mouse;
	
	public LoseMenu(int time){
		this.time = time;
	}
	
	public void tick(InputHandler input) {
		mx = input.mouse.x;
		my = input.mouse.y;
		time++;
		play = false;
		if(input.mouse.y >= 256 && input.mouse.y < 296 && input.mouse.x >= 96 && input.mouse.x <= 384){
			play = true;
		}
		if(input.mouse.left){
			mouse = true;
		}else{
			if(mouse){
				mouse = false;
				if(play){
					Menu.setMenu(new MainMenu(time));
				}
			}
		}
	}
	
	
	public int time;

	public void render(Screen screen) {
		if(time % 2== 0)screen.fill(0, 0, screen.width, screen.height, 0, 5);
		
		screen.drawRotated(Asset.titleBack, (screen.width-480)/2, (screen.height-480)/2, 0, 0, Asset.titleBack.width, Asset.titleBack.height, time/3);
		
		String msg = "THE ONE PERSON AWAKE KILLED";
		screen.draw(msg, screen.width/2-msg.length()*7, 32, 0xffbcbc, 2);
		msg = "TRYING TO SAVE THE CREW";
		screen.draw(msg, screen.width/2-msg.length()*7, 48, 0xffbcbc, 2);
		msg = "OVERMATCHED AND OUTNUMBERED";
		screen.draw(msg, screen.width/2-msg.length()*7, 64, 0xffbcbc, 2);
		msg = "HIS DEFEAT WAS CERTAIN";
		screen.draw(msg, screen.width/2-msg.length()*7, 80, 0xffbcbc, 2);
		msg = "THE LAST HOPE FOR THE SHIP";
		screen.draw(msg, screen.width/2-msg.length()*7, 96, 0xffbcbc, 2);
		msg = "A HOPE THAT VANISHED";
		screen.draw(msg, screen.width/2-msg.length()*7, 112, 0xffbcbc, 2);

		msg = "RETURN";
		screen.draw(msg, screen.width/2-msg.length()*28, 256, play ? 0xffffff : 0xffbcbc, 8);
	}

}
