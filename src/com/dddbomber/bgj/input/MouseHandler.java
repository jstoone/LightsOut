package com.dddbomber.bgj.input;

import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseHandler implements MouseListener, MouseMotionListener{
	
	public MouseHandler(Canvas c){
		c.addMouseListener(this);
		c.addMouseMotionListener(this);
	}
	
	public int x, y;
	
	public boolean left, right;

	@Override
	public void mouseDragged(MouseEvent e) {
		x = (int) (e.getX()/2);
		y = (int) (e.getY()/2);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = (int) (e.getX()/2);
		y = (int) (e.getY()/2);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1)left = true;
		if(e.getButton() == 3)right = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == 1)left = false;
		if(e.getButton() == 3)right = false;
	}
}
