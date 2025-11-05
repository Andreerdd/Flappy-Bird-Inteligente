package flappy;

import processing.core.PVector;

public class Passaro {
	public float y;
	public float velocidade;

	public static final float FORCA_PULO = 175f;
	public static final float GRAVIDADE = 350f;
	public static final int POSICAO_X = 160;

	public static Cano proximoCano;
	public Pesos pesos;

	public int pontos = 1;
	public boolean perdeu;

	public static final float TEMPO_BATER_ASAS = 0.1f;
	public float baterAsas = 0;

	float hue;

	public Passaro(float hue) {
		this.hue = hue;
		y = Flappy.app.height / 2f;
		pesos = new Pesos();
	}

	public Passaro(Pesos pesos, float hue) {
		this.pesos = pesos;
		this.hue = hue;
		y = Flappy.app.height / 2f;
	}

	public void pular() {
		velocidade = -FORCA_PULO;
		baterAsas = TEMPO_BATER_ASAS;
//		Debug.log("Pulou!");
	}

	boolean checarColisao() {
		if(y - 8 < 0 || y + 8 > Flappy.app.height) return true;
		for(Cano cano : Flappy.app.canos) {
			if(POSICAO_X - 12 < cano.pos.x + cano.largura && POSICAO_X + 12 > cano.pos.x)
				if(y + 8 > cano.pos.y + cano.abertura / 2 || y - 8 < cano.pos.y - cano.abertura / 2) {
					return true;
				}
		}

		return false;
	}

	public boolean calcularPular() {
		if(proximoCano == null) return false;
		float posicaoY = y;
		float distanciaX = POSICAO_X - proximoCano.pos.x;
		float distanciaY = posicaoY - proximoCano.pos.y;
		float largura = proximoCano.largura;
		float abertura = proximoCano.abertura;

		float total =
			posicaoY * pesos.posicaoY +
			distanciaY * pesos.distanciaY +
			distanciaX * pesos.distanciaX +
			largura * pesos.largura +
			abertura * pesos.abertura;

		return total > pesos.totalPulo;
	}

	public void atualizar() {
		if(perdeu) return;
		velocidade += GRAVIDADE * Flappy.app.deltaTime;
		y += velocidade * Flappy.app.deltaTime;

		Flappy.app.tint(hue, 80, 100);
		Flappy.app.image(SpriteManager.get("bird_base"), POSICAO_X - 16, y - 16, 32, 32);
		Flappy.app.noTint();
		if(baterAsas > 0) Flappy.app.image(SpriteManager.get("bird_overlay2"), POSICAO_X - 16, y - 16, 32, 32);
		else {
			Flappy.app.image(SpriteManager.get("bird_overlay1"), POSICAO_X - 16, y - 16, 32, 32);
		}
		baterAsas -= Flappy.app.deltaTime;

		perdeu = checarColisao();
		if(perdeu) {
			Populacao.passarosVivos--;
			Debug.log("Passaros vivos: " + Populacao.passarosVivos);
		}

		if(proximoCano != null) {
			if(calcularPular()) pular();
			if(POSICAO_X > proximoCano.pos.x + proximoCano.largura) {
				Populacao.pontuacaoMaxima = pontos;
				pontos++;
				Debug.log("Pontos: " + pontos);
			}
		}
	}
}
