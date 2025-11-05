package flappy;

import processing.core.PImage;

import java.util.HashMap;

public class SpriteManager {
	private static final HashMap<String, PImage> sprites = new HashMap<>();

	public static void loadSprites() {
		Debug.log("LOADING ASSETS");
		String[] spriteNames = { "bird_base1", "bird_base2", "bird_overlay1", "bird_overlay2", "cloud1", "cloud2", "cloud3", "pipe_body", "pipe_head_down", "pipe_head_up" };

		for(String name : spriteNames) {
			Debug.log("LOADING ASSET: `assets/sprites/" + name + "`");
			sprites.put(name, Flappy.app.loadImage("assets/sprites/" + name + ".png"));
		}
	}

	public static PImage get(String spriteName) {
		return sprites.get(spriteName);
	}
}