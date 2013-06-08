package com.dddbomber.bgj.assets;

public class Bitmap {
	public final int width, height;
	public int[] pixels;
	
	public Bitmap(int width, int height){
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
	}
	
	public static String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
			"1234567890:/$";
	
	public static Bitmap font = AssetLoader.loadBitmap("/chars.png");
	
	public static int[] lightFX = new int[] { 0, 8, 2, 10, 12, 4, 14, 6, 3, 11, 1, 9, 15, 7, 13, 5, };

	public void overlay(Bitmap bitmap, int xa, int ya, int col, int trans) {
		int[] oPixels = bitmap.pixels;
		int i = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (oPixels[i] / 10 <= lightFX[((x + xa) & 3) + ((y + ya) & 3) * 4]) pixels[i] = merge(col, pixels[i], trans);
				i++;
			}

		}
	}
	
	public void renderLight(int x, int y, int r) {
		int x0 = x - r;
		int x1 = x + r;
		int y0 = y - r;
		int y1 = y + r;

		if (x0 < 0) x0 = 0;
		if (y0 < 0) y0 = 0;
		if (x1 > width) x1 = width;
		if (y1 > height) y1 = height;
		for (int yy = y0; yy < y1; yy++) {
			int yd = yy - y;
			yd = yd * yd;
			for (int xx = x0; xx < x1; xx++) {
				int xd = xx - x;
				int dist = xd * xd + yd;
				if (dist <= r * r) {
					int br = 255 - dist * 255 / (r * r);
					if (pixels[xx + yy * width] < br) pixels[xx + yy * width] = br;
				}
			}
		}
	}
	
	public void draw(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;
				
				int src = image.pixels[(x+xo) + (y+yo) * image.width];
				if(src != -2)pixels[xPix + yPix * width] = src;
			}
		}
	}
	
	public void drawFlipped(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, int trans){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;
				
				int src = image.pixels[(x+xo) + (h-y+yo) * image.width];
				int col = src;
				if(trans != 100)col = merge(src, pixels[xPix + yPix * width], trans);
				if(src != -2)pixels[xPix + yPix * width] = col;
			}
		}
	}
	
	public void drawScaled(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, double xScale, double yScale){
		w *= xScale;
		h *= yScale;
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;

				int xDraw = (int) (x/xScale+xo);
				int yDraw = (int) (y/yScale+yo);
				int src = image.pixels[(int) (xDraw + yDraw * image.width)];
				if(src != -2)pixels[xPix + yPix * width] = src;
			}
		}
	}
	
	public void drawScaledAndCentered(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, double xScale, double yScale){
		w *= xScale;
		h *= yScale;
		for(int y = 0; y < h; y++){
			int yPix = y+yPos-h/2;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos-w/2;
				if(xPix < 0 || xPix >= width)continue;

				int xDraw = (int) (x/xScale+xo);
				int yDraw = (int) (y/yScale+yo);
				int src = image.pixels[(int) (xDraw + yDraw * image.width)];
				if(src != -2)pixels[xPix + yPix * width] = src;
			}
		}
	}
	
	public void drawScaledAndCentered(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, double xScale, double yScale, int col){
		w *= xScale;
		h *= yScale;
		for(int y = 0; y < h; y++){
			int yPix = y+yPos-h/2;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos-w/2;
				if(xPix < 0 || xPix >= width)continue;

				int xDraw = (int) (x/xScale+xo);
				int yDraw = (int) (y/yScale+yo);
				int src = image.pixels[(int) (xDraw + yDraw * image.width)];
				if(src != -2)pixels[xPix + yPix * width] = col;
			}
		}
	}
	
	public void drawRotated(Bitmap bitmap, int xOffs, int yOffs, int xo, int yo, int w, int h, int rotation) {
        final double radians = Math.toRadians(rotation);
        final double cos = Math.cos(radians);
        final double sin = Math.sin(radians);
        for(int y = 0; y < w; y++){
            int yPix = y + yo;
            for(int x = 0; x < h; x++){
                int xPix = x + xo;

                int centerX = w / 2;
                int centerY = h / 2;
                int m = x - centerX;
                int n = y - centerY;
                int j = ((int) (m * cos + n * sin)) + centerX;
                int k = ((int) (n * cos - m * sin)) + centerY;

                int src = bitmap.pixels[xPix + yPix * bitmap.width];

                int xDraw = xOffs+j;
                int yDraw = yOffs+k;

                if(xDraw < 0 || xDraw >= width-1 || yDraw < 0 || yDraw >= height-1)continue;
                if(src != -2){
                	pixels[xDraw + yDraw * width] = src;
                	if(x < w-1)pixels[xDraw + 1 + yDraw * width] = src;
                	if(y < h-1)pixels[xDraw + (yDraw + 1) * width] = src;
                }

            }
        }
    }
	
	public void draw(String string, int x, int y, int col, double scale) {
		string = string.toUpperCase();
        for (int i = 0; i < string.length(); i++) {
            int ch = chars.indexOf(string.charAt(i));
            if (ch < 0) continue;

            int xx = ch % 26;
            int yy = ch / 26;
            if(string.charAt(i) == '$'){
                drawScaledString(font, x +(int)(i*7*scale-2*scale), (int)(y-(scale)), xx * 7, yy * 6 - 1, 8, 7, col, scale);
            }else{
                drawScaledString(font, x +(int)(i*7*scale), y, xx * 7, yy * 6, 7, 5, col, scale);
            }
        }
    }
	
	public void drawScaledString(Bitmap image, int xPos, int yPos, int xo, int yo, int w, int h, int col, double scale) {
		w *= scale;
		h *= scale;
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;

				int xDraw = (int) (x/scale+xo);
				int yDraw = (int) (y/scale+yo);
				int src = image.pixels[(int) (xDraw + yDraw * image.width)];
				if(src != 0xffffffff)pixels[xPix + yPix * width] = col;
			}
		}
    }
	
	public void drawTrans(Bitmap image, int xPos, int yPos, int xo, int yo, int w , int h, int amount){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;

				int src = image.pixels[(x+xo) + (y+yo) * image.width];
				int merged = merge(src, pixels[xPix + yPix * width], amount);
				if(src != -2)pixels[xPix + yPix * width] = merged;
			}
		}
	}
	
	public void fill(int xPos, int yPos, int w, int h, int col, int amount){
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height)continue;
			
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width)continue;
				int merged = col;
				if(amount != 100){
					merged = merge(col, pixels[xPix + yPix * width], amount);
				}
				pixels[xPix + yPix * width] = merged;
			}
		}
		
	}
	
	public int merge(int color, int color2, int amount) {

        int r = (color >> 16) & 0xff;
        int g = (color >> 8) & 0xff;
        int b = (color) & 0xff;

        int r2 = (color2 >> 16) & 0xff;
        int g2 = (color2 >> 8) & 0xff;
        int b2 = (color2) & 0xff;

        int a = 100 - amount;

        int tr = (r * amount + r2 * a) / 100;
        int tg = (g * amount + g2 * a) / 100;
        int tb = (b * amount + b2 * a) / 100;

        return tr << 16 | tg << 8 | tb;
    }

	public void blur(int xPos, int yPos, int w, int h) {
		for(int y = 0; y < h; y++){
			int yPix = y+yPos;
			if(yPix < 0 || yPix >= height-1)continue;
			if(y% 3 == 0)continue;
			for(int x = 0; x < w; x++){
				int xPix = x+xPos;
				if(xPix < 0 || xPix >= width-3)continue;
				int merged = merge(pixels[xPix + 1 + yPix * width], pixels[xPix + yPix * width], 75);
				merged = merge(pixels[xPix + 2 + yPix * width], merged, 75);
				merged = merge(pixels[xPix + (yPix + 1) * width], merged, 75);
				pixels[xPix + yPix * width] = merged;
			}
		}
		
	}
}
