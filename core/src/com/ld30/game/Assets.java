package com.ld30.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	private final Texture tileTexture;
	private final Texture cityTexture;
	private final Texture moveableTexture;
	private final Texture waterTexture;
	
	public final TextureRegion grass;
	public final TextureRegion water ;
	public final TextureRegion moveable;
	public final TextureRegion city;
	
	public Assets () {
		Pixmap pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.GREEN);
		pixmap.fillRectangle(0, 0, 32, 32);
		
		tileTexture = new Texture(pixmap);
		pixmap.dispose();
		
		grass = new TextureRegion(tileTexture);
		
		pixmap = new Pixmap(32 * 10, 32 * 10, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fillRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
		
		cityTexture = new Texture(pixmap);
		pixmap.dispose();
		city = new TextureRegion(cityTexture);
		
		pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.fillRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
		
		moveableTexture = new Texture(pixmap);
		pixmap.dispose();
		moveable = new TextureRegion(moveableTexture);
		
		pixmap = new Pixmap(32, 32, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.BLUE);
		pixmap.fillRectangle(0, 0, pixmap.getWidth(), pixmap.getHeight());
		
		waterTexture = new Texture(pixmap);
		pixmap.dispose();
		water = new TextureRegion(waterTexture);
	}
	
	public void dispose () {
		tileTexture.dispose();
		cityTexture.dispose();
		moveableTexture.dispose();
	}
	
}
