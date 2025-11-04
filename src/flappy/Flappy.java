package flappy;

import processing.core.PApplet;
import processing.opengl.PGraphicsOpenGL;

import java.util.ArrayList;

public class Flappy extends PApplet {
	public static Flappy app;
	public float deltaTime;
	private int lastMillis;

	public final int TEMPO_SURGIMENTO_CANOS = 1500;
	private int ultimoCanoMillis;

	private final float CLOUD_SPEED = 500f;
	private float cloud1pos = 0;
	private float cloud2pos = 0;
	private float cloud3pos = 0;

	public ArrayList<Cano> canos = new ArrayList<>();

	private Passaro passaro;

	public void settings() {
		size(640, 480, P2D);
		noSmooth();
	}

	public void setup() {
		app = this;
		frameRate(180);
		((PGraphicsOpenGL)g).textureSampling(2);
		colorMode(HSB, 360, 100, 100);
		SpriteManager.loadSprites();

		lastMillis = millis();
		deltaTime = 1;

		passaro = new Passaro();
	}

	public void draw() {
		int curMillis = millis();
		deltaTime = (curMillis - lastMillis) / 1000f;
		lastMillis = curMillis;

		if(curMillis > ultimoCanoMillis + TEMPO_SURGIMENTO_CANOS) {
			ultimoCanoMillis = curMillis;
			canos.add(new Cano());
		}

		cloud1pos = (cloud1pos + -CLOUD_SPEED * deltaTime / 2) % width;
		cloud2pos = (cloud2pos + -CLOUD_SPEED * deltaTime / 4) % width;
		cloud3pos = (cloud3pos + -CLOUD_SPEED * deltaTime / 8) % width;

		background(201, 58, 100);
//		background(240, 38, 39);

		for(int j = -1; j <= 1; j++)
			image(SpriteManager.get("cloud3"), j * width + cloud3pos, 0, width, height);
		for(int j = -1; j <= 1; j++)
			image(SpriteManager.get("cloud2"), j * width + cloud2pos, 0, width, height);

		for(int i = canos.size() - 1; i >= 0; i--) {
			Cano cano = canos.get(i);
			cano.atualizar();
			if(cano.pos.x + cano.largura < 0) canos.remove(cano);
		}

		passaro.atualizar();

		for(int j = -1; j <= 1; j++)
			image(SpriteManager.get("cloud1"), j * width + cloud1pos, 0, width, height);
	}

	public void keyPressed() {
		passaro.pular();
	}

	public void mousePressed() {
		canos.add(new Cano());
	}

	public static void main(String[] args) {
		PApplet.main("flappy.Flappy");
	}
}
