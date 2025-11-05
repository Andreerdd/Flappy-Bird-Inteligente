package flappy;

import java.util.ArrayList;

public class Populacao {
	public static ArrayList<Passaro> passaros = new ArrayList<>();
	public static ArrayList<Passaro> piscinaDeAcasalamento = new ArrayList<>();
	public static int NUMERO_PASSAROS = 32;
	public static boolean REINICIO_AUTOMATICO;
	public static int passarosVivos;
	public static int geracao;
	public static int pontuacaoMaxima;
	public static int pontuacaoMaximaGeracao;

	public static void gerarPopulacao() {
		Debug.log("GERANDO POPULAÇÃO");
		passaros.clear();
		geracao = 1;
		pontuacaoMaximaGeracao = 1;
		pontuacaoMaxima = 1;
		Flappy.app.tempoSimuladoSegundos = 0f;
		Flappy.app.tempoSimuladoMinutos = 0;
		Flappy.app.tempoSimuladoHoras = 0;
		Flappy.app.tempoSimuladoDias = 0;
		Flappy.app.reiniciarCanos();
		passarosVivos = NUMERO_PASSAROS;
		for(int i = 0; i < NUMERO_PASSAROS; i++)
			passaros.add(new Passaro(i * 360f / NUMERO_PASSAROS));
	}

	public static void reproduzirPopulacao() {
		if(pontuacaoMaxima == 1 && REINICIO_AUTOMATICO) {
			Debug.log("REINICIO AUTOMÁTICO");
			gerarPopulacao();
			return;
		}

		for(Passaro passaro : passaros) {
			float pontuacaoNormalizada = (float)passaro.pontos / pontuacaoMaximaGeracao;
			for(int i = 0; i < 1 + 100 * Flappy.pow(pontuacaoNormalizada, 2); i++)
				piscinaDeAcasalamento.add(passaro);
		}

		passaros.clear();
		for(int i = 0; i < NUMERO_PASSAROS; i++) {
			Pesos a = piscinaDeAcasalamento.get((int)Flappy.app.random(piscinaDeAcasalamento.size())).pesos;
			Pesos b = piscinaDeAcasalamento.get((int)Flappy.app.random(piscinaDeAcasalamento.size())).pesos;

			passaros.add(new Passaro(new Pesos(a, b), i * 360f / NUMERO_PASSAROS));
		}

		pontuacaoMaximaGeracao = 1;
		geracao++;
		Flappy.app.reiniciarCanos();
		passarosVivos = NUMERO_PASSAROS;
	}
}
