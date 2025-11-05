package flappy;

import processing.core.PApplet;
import processing.opengl.PGraphicsOpenGL;

import java.util.ArrayList;

public class Flappy extends PApplet {
	public static Flappy app;
	public float deltaTime;
	private int lastMillis;

	public final float TEMPO_SURGIMENTO_CANOS = 1f;
	private float contadorSurgimentoCano = 0;

	private final float CLOUD_SPEED = 125f;
	private float cloud1pos = 0;
	private float cloud2pos = 0;
	private float cloud3pos = 0;

	private int escalaTempo = 1;

	public float tempoSimulado = 0f;

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

		tempoSimulado = millis() / 1000f;

		Populacao.gerarPopulacao();
	}

	public void reiniciarCanos() {
		canos.clear();
		Cano.velocidadeCano = Cano.VELOCIDADE_INICIAL;
		Cano.multAberturaCano = Cano.MULT_ABERTURA_INICIAL;

		adicionarCano();
	}

	public void adicionarCano() {
		canos.add(new Cano());
		contadorSurgimentoCano = 0;

		Cano.velocidadeCano = min(Cano.VELOCIDADE_MAXIMA, Cano.velocidadeCano + 1f);
		Cano.multAberturaCano = map(Cano.velocidadeCano, Cano.VELOCIDADE_INICIAL, Cano.VELOCIDADE_MAXIMA, Cano.MULT_ABERTURA_INICIAL, Cano.MULT_ABERTURA_MAXIMA);

		acharProximoCano();
	}

	public void draw() {
		background(201, 58, 100);

		int curMillis = millis();
		deltaTime = (curMillis - lastMillis) / 1000f;
		lastMillis = curMillis;

		for(int j = 0; j < escalaTempo; j++) {
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

			if(contadorSurgimentoCano > TEMPO_SURGIMENTO_CANOS) adicionarCano();
			contadorSurgimentoCano += deltaTime;

			acharProximoCano();

			if(Populacao.passarosVivos == 0) Populacao.reproduzirPopulacao();

			tempoSimulado += deltaTime;
		}

		for(int j = -1; j <= 1; j++)
			image(SpriteManager.get("cloud3"), j * width + cloud3pos, 0, width, height);
		for(int j = -1; j <= 1; j++)
			image(SpriteManager.get("cloud2"), j * width + cloud2pos, 0, width, height);

		for(Passaro passaro : Populacao.passaros)
			passaro.exibir();

		for(Cano cano : canos)
			cano.exibir();

		for(int j = -1; j <= 1; j++)
			image(SpriteManager.get("cloud1"), j * width + cloud1pos, 0, width, height);

		fill(0, 0, 0);
		textAlign(LEFT, TOP);
		text("Geração: " + Populacao.geracao ,4, 4);
		text("Vivos: " + Populacao.passarosVivos ,4, 20);
		text("Pontuação: " + (Populacao.pontuacaoMaximaGeracao - 0) ,4, 36);
		text("Velocidade do cano: " + nf(Cano.velocidadeCano, 0, 1) ,4, 52);
		textAlign(RIGHT, TOP);
		text("Pontuação máxima: " + (Populacao.pontuacaoMaxima - 0) ,width - 4, 4);
		text("Velocidade da simulação: " + escalaTempo ,width - 4, 20);
		textAlign(LEFT, BOTTOM);
		text("Tempo de execução: " + nf(millis() / 1000f, 0, 1) + "s" ,4, height - 4);
		text("Tempo simulado: " + nf(tempoSimulado, 0, 1) + "s" ,4, height - 20);
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
		if(key == '=')
			escalaTempo++;
		if(key == '-')
			escalaTempo = max(escalaTempo - 1, 1);
		if(key == '+')
			escalaTempo *= 10;
		if(key == '_')
			escalaTempo = max(escalaTempo / 10, 1);
		if(key > '0' && key <= '9')
			escalaTempo = (key - '0') * 10;
		if(key == '0')
			escalaTempo = 100;
		if(key == ' ')
			if(escalaTempo > 0) escalaTempo = 0;
			else escalaTempo = 1;
	}
}
