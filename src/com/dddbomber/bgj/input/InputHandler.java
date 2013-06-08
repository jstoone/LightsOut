package com.dddbomber.bgj.input;

import java.awt.Canvas;

public class InputHandler {
	public KeyboardHandler keyboard;
	public MouseHandler mouse;
	public FocusHandler focus;
	
	public InputHandler(Canvas c){
		keyboard = new KeyboardHandler(c);
		mouse = new MouseHandler(c);
		focus = new FocusHandler(c, keyboard);
	}
}
