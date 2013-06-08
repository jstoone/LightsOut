package com.dddbomber.bgj.assets;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

public class AssetLoader {
	public static Bitmap loadBitmap(String location){
		try{
			BufferedImage img = ImageIO.read(AssetLoader.class.getResource(location));
			int w = img.getWidth();
			int h = img.getHeight();
			Bitmap result = new Bitmap(w, h);
			img.getRGB(0, 0, w, h, result.pixels, 0, w);
			for(int i = 0; i < result.pixels.length; i++){
				int src = result.pixels[i];
				if(src == 0 || src == 16777215){
					src = -2;
				}
				result.pixels[i] = src;
			}
			
			System.out.println("Loaded Bitmap: " +location);
			return result;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	public static Bitmap loadBitmapFromWeb(String location) throws Exception{
		final URL url = new URL(location);
		final HttpURLConnection connection = (HttpURLConnection) url
				.openConnection();
		connection.setRequestProperty(
				"User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_5) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
		final BufferedImage img = ImageIO.read(connection.getInputStream());
		int w = img.getWidth();
		int h = img.getHeight();
		Bitmap result = new Bitmap(w, h);
		img.getRGB(0, 0, w, h, result.pixels, 0, w);
		for(int i = 0; i < result.pixels.length; i++){
			int src = result.pixels[i];
			if(src == 0 || src == 16777215){
				src = -2;
			}
			result.pixels[i] = src;
		}

		System.out.println("Loaded Bitmap: " +location);
		return result;
	}
}
