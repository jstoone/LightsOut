package com.dddbomber.bgj.input;

import java.awt.Canvas;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class FocusHandler implements FocusListener{
	
	public KeyboardHandler keyboard;
	
	public FocusHandler(Canvas c, KeyboardHandler keyboard){
		c.addFocusListener(this);
		this.keyboard = keyboard;
	}
	public boolean hasFocus;
	
	public void focusGained(FocusEvent arg0) {
		hasFocus = true;
	}
	
	public void focusLost(FocusEvent arg0) {
		hasFocus = false;
		for(int i  = 0; i < keyboard.keys.length; i++){
			keyboard.keys[i] = false;
		}
	}
}
