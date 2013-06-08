package com.dddbomber.bgj.assets;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.ArrayList;

public class Sound {
	public static ArrayList<Sound> sounds = new ArrayList<Sound>();


	public static boolean muted = false;
	
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
}
