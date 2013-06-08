package com.dddbomber.bgj.input;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandler implements KeyListener	{

	public KeyboardHandler(Canvas c){
		c.addKeyListener(this);
	}
	
	public boolean[] keys = new boolean[65536];
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if(code < 0 || code >= keys.length)return;
		keys[code] = true;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int code = arg0.getKeyCode();
		if(code < 0 || code >= keys.length)return;
		keys[code] = false;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
