package flappy;

public class Passaro {
	public float y;
	public float velocidade;

	public static final float FORCA_PULO = 175f;
	public static final float GRAVIDADE = 350f;
	public static final int POSICAO_X = 160;

	int hue;

	public Passaro() {
		hue = (int)Flappy.app.random(360);
		y = Flappy.app.height / 2f;
	}

	public void pular() {
		velocidade = -FORCA_PULO;
	}

	boolean checarColisao() {
		for(Cano cano : Flappy.app.canos) {
			if(POSICAO_X - 16 > cano.pos.x && POSICAO_X + 16 < cano.pos.x + cano.largura)
				if(y + 8 < cano.pos.y - cano.abertura / 2 || y + 8 > cano.pos.y + cano.abertura / 2) {
					return true;
				}
		}

		return false;
	}

	boolean checarColisao(int i, int j) {
		for(Cano cano : Flappy.app.canos) {
			if(i > cano.pos.x && i < cano.pos.x + cano.largura)
				if(j < cano.pos.y - cano.abertura / 2 || j > cano.pos.y + cano.abertura / 2) {
					return true;
				}
		}

		return false;
	}

	public void atualizar() {
		velocidade += GRAVIDADE * Flappy.app.deltaTime;
		y += velocidade * Flappy.app.deltaTime;

		if(checarColisao())
			Flappy.app.tint(0, 80, 100);
		else
			Flappy.app.tint(120, 80, 100);
		Flappy.app.image(SpriteManager.get("bird_base"), POSICAO_X - 16, y - 16, 32, 32);
		Flappy.app.noTint();

//		if(checarColisao()) Debug.warning("Passaro colidiu!");

//		Flappy.app.fill(160, 100, 100);
//		for(int i = 0; i < Flappy.app.width; i += 8)
//			for(int j = 0; j < Flappy.app.height; j += 8)
//				if(checarColisao(i, j))
//					Flappy.app.circle(i, j, 6);
	}
}
