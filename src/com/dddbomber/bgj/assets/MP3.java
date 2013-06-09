package com.dddbomber.bgj.assets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;

import javazoom.jl.player.Player;


public class MP3 {
    private File file;
    private Player player; 

    public MP3(String fileName) {
        try {
			this.file = new File(MP3.class.getResource(fileName).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }

    public void close() { if (player != null) player.close(); }

    public void play() {
        try {
            FileInputStream fis     = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
        }
        catch (Exception e) {
            System.out.println("Problem playing file " + file);
            System.out.println(e);
        }

        new Thread() {
            public void run() {
                try { player.play(); }
                catch (Exception e) { System.out.println(e); }
            }
        }.start();
    }

    public static void main(String[] args) {
			MP3 mp3 = new MP3("/song.mp3");
	        mp3.play();
    }

}