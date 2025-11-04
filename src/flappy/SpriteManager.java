package flappy;

import processing.core.PImage;

import java.io.File;
import java.util.HashMap;

public class SpriteManager {
	private static final HashMap<String, PImage> sprites = new HashMap<>();

	public static void loadSprites() {
		Debug.log("LOADING ASSETS");
		File folder = new File("assets/sprites");
		File[] files = folder.listFiles();

		if(files != null) {
			for(File f : files) {
				if(f.isDirectory()) {
					loadSpritesSubFolder(f.getName());
				} else if (f.isFile() && f.getName().toLowerCase().endsWith(".png")) {
					String name = f.getName().substring(0, f.getName().lastIndexOf("."));
					Debug.log("LOADING ASSET: `assets/sprites/" + f.getName() + "`");
					sprites.put(name, Flappy.app.loadImage("assets/sprites/" + f.getName()));
				}
			}
		} else {
			Debug.error("Folder \'" + folder.getAbsolutePath() + "\' does not exist!");
		}
	}

	public static void loadSpritesSubFolder(String folderName) {
		String path = "assets/sprites/" + folderName;
		File folder = new File(path);
		File[] files = folder.listFiles();

		if (files != null) {
			for (File f : files) {
				if (f.isFile() && f.getName().toLowerCase().endsWith(".png")) {
					String name = f.getName().substring(0, f.getName().lastIndexOf("."));
					Debug.log("LOADING ASSET: `" + path + "/" + f.getName() + "`");
					sprites.put(folderName + "/" + name, Flappy.app.loadImage(path + "/" + f.getName()));
				}
			}
		}
	}

	public static PImage get(String spriteName) {
		return sprites.get(spriteName);
	}
}