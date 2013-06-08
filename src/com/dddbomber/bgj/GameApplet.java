package com.dddbomber.bgj;

import java.applet.Applet;
import java.awt.BorderLayout;

public class GameApplet extends Applet{
	private static final long serialVersionUID = 1L;
	
	public static boolean browser = false;
	
	Game game = new Game();
	
	public void init(){
		setSize(Game.SCREENWIDTH, Game.SCREENHEIGHT);
		setLayout(new BorderLayout());
		add(game, BorderLayout.CENTER);
		browser = true;
	}
	
	public void start(){
		game.start();
	}
	
	public void stop(){
		game.stop();
	}
}