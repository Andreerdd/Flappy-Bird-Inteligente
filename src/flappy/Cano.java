package flappy;

import processing.core.PVector;

public class Cano {
	public PVector pos;
	public float largura;
	public float abertura;

	public int hue;

	public static float velocidadeCano;
	public static final float VELOCIDADE_INICIAL = 250f;
	public static final float VELOCIDADE_MAXIMA = 750f;

	public static float multAberturaCano;
	public static final float MULT_ABERTURA_INICIAL = 1f;
	public static final float MULT_ABERTURA_MAXIMA = 3f;

	public Cano(float altura, float largura, float abertura) {
		this.pos = new PVector(Flappy.app.width * 1.5f, altura);
		this.largura = largura;
		this.abertura = abertura;
		this.hue = (int)(Flappy.app.frameCount / 250f % 360);
	}

	public Cano() {
		this.abertura = Flappy.app.random(96, 192) / multAberturaCano;
		this.pos = new PVector(Flappy.app.width * 1.5f, Flappy.app.random(128 + abertura / 2, Flappy.app.height - 128 - abertura / 2));
		this.largura = Flappy.app.random(64, 96);
		this.hue = (int)(Flappy.app.frameCount / 250f % 360);
	}

	public void exibir() {
		Flappy.app.tint(hue, 80, 100);
		Flappy.app.image(SpriteManager.get("pipe_body"), pos.x, 0, largura, pos.y - abertura / 2 - 32);
		Flappy.app.image(SpriteManager.get("pipe_head_down"), pos.x, pos.y - abertura / 2 - 32, largura, 32);
		Flappy.app.image(SpriteManager.get("pipe_body"), pos.x, pos.y + abertura / 2, largura, Flappy.app.height - abertura / 2 - 64);
		Flappy.app.image(SpriteManager.get("pipe_head_up"), pos.x, pos.y + abertura / 2, largura, 32);
		Flappy.app.noTint();
	}

	public void atualizar() {
		pos.x -= Flappy.app.deltaTime * velocidadeCano;
	}
}
