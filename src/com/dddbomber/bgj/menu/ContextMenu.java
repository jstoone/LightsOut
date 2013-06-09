package com.dddbomber.bgj.menu;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;

public class ContextMenu extends Menu{
	
	public int mx = 240, my = 150;
	public boolean down;
	
	public boolean play = false, mouse;
	
	public ContextMenu(int time){
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
					Menu.setMenu(new GameMenu());
				}
			}
		}
	}
	
	
	public int time;

	public void render(Screen screen) {
		if(time % 2== 0)screen.fill(0, 0, screen.width, screen.height, 0, 1);
		
		screen.drawRotated(Asset.titleBack, (screen.width-480)/2, (screen.height-480)/2, 0, 0, Asset.titleBack.width, Asset.titleBack.height, time/3);
		
		String msg = "IN A LONG LOST SPACE SHUTTLE";
		screen.draw(msg, screen.width/2-msg.length()*7, 32, 0xffbcbc, 2);
		msg = "ONE MAN IN CRYOSTASIS AWAKES";
		screen.draw(msg, screen.width/2-msg.length()*7, 48, 0xffbcbc, 2);
		msg = "TO FIND HIS SHUTTLE INFESTED";
		screen.draw(msg, screen.width/2-msg.length()*7, 64, 0xffbcbc, 2);
		msg = "WITH MONSTERS THAT CRAVE THE DARK";
		screen.draw(msg, screen.width/2-msg.length()*7, 80, 0xffbcbc, 2);
		msg = "THAT MAN MUST FIGHT TO RESTORE";
		screen.draw(msg, screen.width/2-msg.length()*7, 96, 0xffbcbc, 2);
		msg = "HIS ONCE GREAT SHIP";
		screen.draw(msg, screen.width/2-msg.length()*7, 112, 0xffbcbc, 2);

		msg = "YOU ARE THAT MAN";
		screen.draw(msg, screen.width/2-msg.length()*7, 144, 0xffbcbc, 2);

		msg = "START";
		screen.draw(msg, screen.width/2-msg.length()*28, 256, play ? 0xffffff : 0xffbcbc, 8);
	}

}
