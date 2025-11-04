package flappy;

import processing.core.PVector;

import static flappy.FlappyBirdInteligente.GRAVIDADE;
import static flappy.FlappyBirdInteligente.POSICAO_HORIZONTAL_PASSARO;

/**
 * Classe responsável pelo pássaro (Flappy Bird).
 */

public class Bird {
    // Posição inicial do pássaro
    private final PVector POSICAO_INICIAL_PASSARO = new PVector(POSICAO_HORIZONTAL_PASSARO, Main.app.height/2);
    // Força do pulo inicial do pássaro
    private final static float FORCA_PULO_INICIAL = 4.67f;
    // Tamanho do pássaro
    private final PVector TAMANHO_PASSARO = new PVector(40, 30);


    // Tamanho da hitbox do pássaro
    PVector hitbox;
    // Posição do pássaro na tela
    PVector posicao;
    // Velocidade do pássaro
    PVector velocidade;
    // Força do pulo do pássaro
    float forcaPulo;


    Bird() {
        posicao = POSICAO_INICIAL_PASSARO;
        velocidade = new PVector(0, 0);
        forcaPulo = FORCA_PULO_INICIAL;
        hitbox = TAMANHO_PASSARO;
    }


    // Método que faz o pássaro pular
    void jump() {
        // Se o pássaro tiver velocidade positiva(caindo), força ele a ir para cima
        // na velocidade da força do pulo. Caso contrário, retira a força do pulo
        // à velocidade vertical(para ele ir para cima).
        velocidade.y = velocidade.y > 0f ? -forcaPulo : velocidade.y - forcaPulo/2.6769f;
        // obs.: esse valor q a forca pulo é dividida no 2º caso foi milimetricamente
        // calculada para deixar a experiência do usuário mais divertida.
    }

    // Verifica se o pássaro colidiu com um cano ou com os limites da tela.
    void checkCollision(Pipe pipe) {

    }

    // Método que atualiza o estado do pássaro
    void update() {
        velocidade.add(GRAVIDADE.copy().mult(Main.app.deltaTime));
        posicao.add(velocidade);
    }

    // Método que desenha o pássaro na tela
    void draw() {
	    Main.app.fill(255, 255, 0);
	    Main.app.rect(posicao.x, posicao.y - hitbox.y/2, hitbox.x, hitbox.y);
    }
}
