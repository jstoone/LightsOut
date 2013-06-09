package com.dddbomber.bgj.assets;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Render extends Bitmap{
	
	private BufferedImage img;

	public Render(int width, int height) {
		super(width, height);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)img.getRaster().getDataBuffer()).getData();
	}
	
	public BufferedImage getImage(){
		return img;
	}
}
