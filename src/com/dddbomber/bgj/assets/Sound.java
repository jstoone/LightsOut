package com.dddbomber.bgj.assets;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.ArrayList;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;

public class Sound {
	public static ArrayList<Sound> sounds = new ArrayList<Sound>();

	public static Sound roar = new Sound("/roar.wav");
	public static Sound wolfHurt = new Sound("/wolfHurt.wav");
	
	public static Sound warpback = new Sound("/warpback.wav");
	public static Sound warpstop = new Sound("/warpstop.wav");
	public static Sound warpstart = new Sound("/warpstart.wav");
	
	public static Sound click = new Sound("/click.wav");

	public static boolean muted = false;

	public static Sound doorClose = new Sound("/doorclose.wav");
	public static Sound doorOpen = new Sound("/dooropen.wav");

	public static Sound song = new Sound("/song.wav");

	
	private AudioClip clip;
	public boolean stopped;
	
	protected Sound(String name){
		try{
			clip = Applet.newAudioClip(Sound.class.getResource(name));
			sounds.add(this);
		}catch (Exception e){
			
		}
	}
	
	public static void stopAll(){
		for(Sound s : sounds){
			s.clip.stop();
			s.stopped = true;
		}
	}
	
	public void loop(){
		if(muted)return;
		try{
			new Thread(){
				public void run(){
					clip.loop();
					stopped = false;
				}
			}.start();
		}catch(Throwable e){
			e.printStackTrace();
		}
	}
	
	public void play(){
		if(muted)return;
		try{
			new Thread(){
				public void run(){
					clip.play();
					stopped = false;
				}
			}.start();
		}catch(Throwable e){
			e.printStackTrace();
		}
	}

	public void stop() {
		this.clip.stop();
		this.stopped = true;
	}
}
