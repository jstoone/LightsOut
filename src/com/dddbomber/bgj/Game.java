package com.dddbomber.bgj;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.room.Room;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 480, HEIGHT = 312;
	public static int SCREENWIDTH = 960, SCREENHEIGHT = 624;
	public static final String NAME = "Wierd Game Thing";

	public Screen screen;
	public InputHandler input;
	
	public Game(){
		setSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
		screen = new Screen(WIDTH, HEIGHT);
		input = new InputHandler(this);
	}
	
	public static int ticks, renders;
	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000.0 / 60.0;
		
		double time = 0;
		
		long lastSecond = System.currentTimeMillis();
		
		this.requestFocus();
		
		while(running){
			long now = System.nanoTime();
			time += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean render = false;
			while(time >= 1){
				tick();
				time -= 1;
				render = true;
			}
			if(render)render();
			if(System.currentTimeMillis() - lastSecond > 1000){
				lastSecond += 1000;
				System.out.println("FPS - " +renders +", TICKS - " + ticks);
				ticks = renders = 0;
			}
		}
	}
	
	Room room = new Room();

	private void render() {
		renders++;
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		room.render(screen);
		
		if(!input.focus.hasFocus){
			screen.fill(0, 0, WIDTH, HEIGHT, 0, 50);
			String msg = "CLICK TO FOCUS";
			screen.draw(msg, 280-msg.length()*7, 100, 0xffffff, 2);
		}
		
		
		
		g.drawImage(screen.getImage(), 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
	}
	
	public boolean pressed = false;

	private void tick() {
		ticks++;
		if(!input.focus.hasFocus)return;
		room.tick(input);
		
	}
	
	public static Image icon;
	
	public static void main(String[] args){ 
		Game game = new Game();
		JFrame frame = new JFrame(NAME);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(game, BorderLayout.CENTER);
		frame.setContentPane(panel);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		game.start();
	}
	
	private Thread gameThread;
	
	public boolean running = false;

	public void start() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void stop() {
		running = false;
	}
}
