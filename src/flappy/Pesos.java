package flappy;

public class Pesos {
	public float posicaoY;
	public float distanciaX, distanciaY;
	public float largura, abertura;
	public float totalPulo;

	public static final float CHANCE_MUTACAO = 0.0005f;
	public static final float EFEITO_MUTACAO = 0.001f;

	public Pesos(float posicaoY, float distanciaX, float distanciaY, float largura, float abertura, float totalPulo) {
		this.posicaoY = posicaoY;
		this.distanciaX = distanciaX;
		this.distanciaY = distanciaY;
		this.largura = largura;
		this.abertura = abertura;
		this.totalPulo = totalPulo;
	}

	public Pesos(Pesos fonte) {
		this.posicaoY = fonte.posicaoY;
		this.distanciaX = fonte.distanciaX;
		this.distanciaY = fonte.distanciaY;
		this.largura = fonte.largura;
		this.abertura = fonte.abertura;
		this.totalPulo = fonte.totalPulo;
	}

	public Pesos(Pesos a, Pesos b) {
		this.posicaoY = Flappy.app.random(1) > 0.5f ? a.posicaoY : b.posicaoY;
		this.distanciaX = Flappy.app.random(1) > 0.5f ? a.distanciaX : b.distanciaX;
		this.distanciaY = Flappy.app.random(1) > 0.5f ? a.distanciaY : b.distanciaY;
		this.largura = Flappy.app.random(1) > 0.5f ? a.largura : b.largura;
		this.abertura = Flappy.app.random(1) > 0.5f ? a.abertura : b.abertura;
		this.totalPulo = Flappy.app.random(1) > 0.5f ? a.totalPulo : b.totalPulo;

		if(Flappy.app.random(1) < CHANCE_MUTACAO) posicaoY += Flappy.app.random(-EFEITO_MUTACAO, EFEITO_MUTACAO);
		if(Flappy.app.random(1) < CHANCE_MUTACAO) distanciaX += Flappy.app.random(-EFEITO_MUTACAO, EFEITO_MUTACAO);
		if(Flappy.app.random(1) < CHANCE_MUTACAO) distanciaY += Flappy.app.random(-EFEITO_MUTACAO, EFEITO_MUTACAO);
		if(Flappy.app.random(1) < CHANCE_MUTACAO) largura += Flappy.app.random(-EFEITO_MUTACAO, EFEITO_MUTACAO);
		if(Flappy.app.random(1) < CHANCE_MUTACAO) abertura += Flappy.app.random(-EFEITO_MUTACAO, EFEITO_MUTACAO);
		if(Flappy.app.random(1) < CHANCE_MUTACAO) totalPulo += Flappy.app.random(-EFEITO_MUTACAO, EFEITO_MUTACAO);
	}

	public Pesos() {
		this.posicaoY = Flappy.app.random(-1, 1);
		this.distanciaX = Flappy.app.random(-1, 1);
		this.distanciaY = Flappy.app.random(-1, 1);
		this.largura = Flappy.app.random(-1, 1);
		this.abertura = Flappy.app.random(-1, 1);
		this.totalPulo = Flappy.app.random(-1000, 1000);
	}
}