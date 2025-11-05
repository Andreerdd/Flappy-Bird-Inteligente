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

	private final float CLOUD_SPEED = 125f;
	private float cloud1pos = 0;
	private float cloud2pos = 0;
	private float cloud3pos = 0;

	private boolean paused = true;

	public ArrayList<Cano> canos = new ArrayList<>();

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

		Populacao.gerarPopulacao();
	}

	public void reiniciarCanos() {
		canos.clear();
		adicionarCano();
	}

	public void adicionarCano() {
		canos.add(new Cano());
		ultimoCanoMillis = millis();

		acharProximoCano();
	}

	public void draw() {
		background(201, 58, 100);

		for(int j = -1; j <= 1; j++)
			image(SpriteManager.get("cloud3"), j * width + cloud3pos, 0, width, height);
		for(int j = -1; j <= 1; j++)
			image(SpriteManager.get("cloud2"), j * width + cloud2pos, 0, width, height);

		int curMillis = millis();
		deltaTime = (curMillis - lastMillis) / 1000f;
		lastMillis = curMillis;

		cloud1pos = (cloud1pos + -CLOUD_SPEED * deltaTime / 1) % width;
		cloud2pos = (cloud2pos + -CLOUD_SPEED * deltaTime / 2) % width;
		cloud3pos = (cloud3pos + -CLOUD_SPEED * deltaTime / 4) % width;

		for(int i = canos.size() - 1; i >= 0; i--) {
			Cano cano = canos.get(i);
			cano.atualizar();
			if(cano.pos.x + cano.largura < 0) canos.remove(cano);
		}

		for(Passaro passaro : Populacao.passaros)
			passaro.atualizar();

		if(Populacao.passarosVivos == 0) Populacao.reproduzirPopulacao();

		if(curMillis > ultimoCanoMillis + TEMPO_SURGIMENTO_CANOS) adicionarCano();
		acharProximoCano();

		for(int j = -1; j <= 1; j++)
			image(SpriteManager.get("cloud1"), j * width + cloud1pos, 0, width, height);

		fill(0, 0, 0);
		textAlign(LEFT, TOP);
		text("Geração: " + Populacao.geracao ,4, 4);
		text("Vivos: " + Populacao.passarosVivos ,4, 20);
		text("Pontuação máxima: " + (Populacao.pontuacaoMaxima - 1) ,4, 36);
	}

	private void acharProximoCano() {
		Passaro.proximoCano = null;
		float menorX = Float.MAX_VALUE;
		for(int i = canos.size() - 1; i >= 0; i--) {
			Cano cano = canos.get(i);
			if(Passaro.POSICAO_X < cano.pos.x + cano.largura) {
				Passaro.proximoCano = cano;
				menorX = cano.pos.x;
			}
		}
	}

	public void keyPressed() {
		if(key == 'r')
			Populacao.gerarPopulacao();
	}

	public void mousePressed() {
		canos.add(new Cano());
	}
}
