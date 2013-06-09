package com.dddbomber.bgj;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.dddbomber.bgj.assets.Asset;
import com.dddbomber.bgj.assets.MP3;
import com.dddbomber.bgj.assets.Render;
import com.dddbomber.bgj.assets.Screen;
import com.dddbomber.bgj.input.InputHandler;
import com.dddbomber.bgj.menu.Menu;

public class Game extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 480, HEIGHT = 312;
	public static int SCREENWIDTH = 960, SCREENHEIGHT = 624;
	public static final String NAME = "Wierd Game Thing";

	public Render render;
	public Screen screen;
	public InputHandler input;
	
	public Game(){
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "invisible"));
		setSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
		render = new Render(WIDTH, HEIGHT);
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
			boolean render = true;
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

	private void render() {
		renders++;
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Menu.getMenu().render(screen);
		
		render.draw(screen, screen.xKnock, screen.yKnock, 0, 0, WIDTH, HEIGHT);
		
		if(!input.focus.hasFocus){
			render.fill(0, 0, WIDTH, HEIGHT, 0, 50);
			String msg = "CLICK TO FOCUS";
			render.draw(msg, 240-msg.length()*7, 100, 0xffffff, 2);
		}
		
		if(input.focus.hasFocus){
			render.draw(Asset.gui, input.mouse.x-8, input.mouse.y-8, 0, 20, 16, 16);
		}
		
		g.drawImage(render.getImage(), 0, 0, getWidth(), getHeight(), null);
		
		g.dispose();
		bs.show();
	}
	
	public boolean pressed = false;

	private void tick() {
		ticks++;
		if(!input.focus.hasFocus)return;
		Menu.getMenu().tick(input);
	}
	
	public static Image icon;
	
	public static void main(String[] args){
		//MP3.main(args);
		Game game = new Game();
		JFrame frame = new JFrame(NAME);
		try{
			icon = ImageIO.read(Game.class.getResourceAsStream("/icon.png"));
			frame.setIconImage(icon);
		}catch(Exception e){
			
		}
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
