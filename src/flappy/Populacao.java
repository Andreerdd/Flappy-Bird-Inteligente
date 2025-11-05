package flappy;

import java.util.ArrayList;

public class Populacao {
	public static ArrayList<Passaro> passaros = new ArrayList<>();
	public static ArrayList<Passaro> piscinaDeAcasalamento = new ArrayList<>();
	public static int NUMERO_PASSAROS = 32;
	public static int passarosVivos;
	public static int geracao;
	public static int pontuacaoMaxima;
	public static int pontuacaoMaximaGeracao;

	public static void gerarPopulacao() {
		passaros.clear();
		geracao = 1;
		pontuacaoMaximaGeracao = 1;
		pontuacaoMaxima = 1;
		Flappy.app.reiniciarCanos();
		passarosVivos = NUMERO_PASSAROS;
		for(int i = 0; i < NUMERO_PASSAROS; i++)
			passaros.add(new Passaro(i * 360f / NUMERO_PASSAROS));
	}

	public static void reproduzirPopulacao() {
		pontuacaoMaximaGeracao = 1;
		geracao++;
		Flappy.app.reiniciarCanos();
		passarosVivos = NUMERO_PASSAROS;

		for(Passaro passaro : passaros) {
			float pontuacaoNormalizada = passaro.pontos * 2f / pontuacaoMaximaGeracao;
			for(int i = 0; i < 1 + Flappy.pow(pontuacaoNormalizada, 2); i++)
				piscinaDeAcasalamento.add(passaro);
		}

		passaros.clear();
		for(int i = 0; i < NUMERO_PASSAROS; i++) {
			Pesos a = piscinaDeAcasalamento.get((int)Flappy.app.random(piscinaDeAcasalamento.size())).pesos;
			Pesos b = piscinaDeAcasalamento.get((int)Flappy.app.random(piscinaDeAcasalamento.size())).pesos;

			passaros.add(new Passaro(new Pesos(a, b), i * 360f / NUMERO_PASSAROS));
		}
	}
}
