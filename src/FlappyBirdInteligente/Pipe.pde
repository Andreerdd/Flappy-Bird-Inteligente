/**
 * Classe responsável pelos canos (obstáculos).
 */

class Pipe {
    // Largura mínima e máxima do cano
    private static final float LARGURA_MINIMA = 40, LARGURA_MAXIMA = 70;
    // Tamanho mínimo e máximo do buraco do cano
    private static final float TAMANHO_BURACO_MINIMO = 150, TAMANHO_BURACO_MAXIMO = 200;
    // Altura mínima e máxima do buraco do cano
    private final float ALTURA_MINIMA = 30+TAMANHO_BURACO_MINIMO, ALTURA_MAXIMA = height-(30+TAMANHO_BURACO_MAXIMO);

    // Posição do cano
    float posicaoHorizontal;
    // Largura do cano
    float largura;
    // A altura do buraco dele
    float alturaBuraco;
    // O tamanho do buraco do cano (para o pássaro passar)
    float tamanhoBuraco;

    Pipe(float _posicaoHorizontal) {
        posicaoHorizontal = _posicaoHorizontal;
        largura = random(LARGURA_MINIMA, LARGURA_MAXIMA);
        alturaBuraco = random(ALTURA_MINIMA, ALTURA_MAXIMA);
        tamanhoBuraco = random(TAMANHO_BURACO_MINIMO, TAMANHO_BURACO_MAXIMO);
    }

    // Verifica se o cano está fora da tela
    boolean isForaDaTela() {
        return posicaoHorizontal-camX+largura < 0;
    }

    // Atualiza o estado do cano
    void update() {

    }
    // Desenha o cano na tela
    void draw() {
        // Verifica se o cano já chegou na tela (se o cano estiver antes da tela, ele
        // já vai ser removido automaticamente).
        if (posicaoHorizontal-camX <= width) {
            fill(color(0, 255, 0));
            // Desenha o cano de cima
            rect(posicaoHorizontal - camX, 0, largura, alturaBuraco);
            // Desenha o cano de baixo
            rect(posicaoHorizontal - camX, alturaBuraco + tamanhoBuraco, largura, height - (alturaBuraco + tamanhoBuraco));
        }
    }
}