/**
 * Arquivo responsável pelo gerenciamento do programa.
 */

// Posição x do pássaro na tela (estático)
final static float POSICAO_HORIZONTAL_PASSARO = 67;
// Valor da gravidade
final static PVector GRAVIDADE = new PVector(0, 9.8);
// Distância entre os canos
static float DISTANCIA_CANOS = 167;
// Tempo entre a geração de canos. Depois, troca isso daqui pra
// gerar um novo cano toda vez que passar por um cano (ou deixa assim sla)
static float GERACAO_CANO_COOLDOWN = 1;

// Posição x da câmera e a sua velocidade
float camX = 0;
float camSpeed = 90;
// DeltaTime
float last = 0, deltaTime = 0;
// Se o jogo está rodando ou não
boolean jogoRodando = false;

// Pássaro que o jogador irá jogar (temporário)
Bird passaroJogador;
ArrayList<Pipe> pipes = new ArrayList<>();
void setup() {
    size(800, 600);
    frameRate(60);
    // Cria o pássaro
    passaroJogador = new Bird();

    // Cria os canos iniciais
    float x = 100+DISTANCIA_CANOS;
    for (int i = 1; x <= width; i++) {
        Pipe cano = new Pipe(x);
        pipes.add(cano);
        x += cano.largura + DISTANCIA_CANOS;
    }
}

void atualizarCanos() {
    // Verifica se é para criar outro cano (na direita da tela)
    pipeCooldown -= deltaTime;
    if (camX <= 0) {
        Pipe lastPipe = pipes.get(pipes.size()-1);
        float proximoCanoX = lastPipe.posicaoHorizontal + lastPipe.largura + DISTANCIA_CANOS;
        pipes.add(new Pipe(proximoCanoX));
        pipeCooldown = GERACAO_CANO_COOLDOWN;
    }


    ArrayList<Pipe> paraRemover = new ArrayList<>();
    for (Pipe cano : pipes) {
        // Se o cano estiver fora da tela, adiciona
        // ele na lista de para remover
        if (cano.isForaDaTela()) {
            paraRemover.add(cano);
            continue;
        }

        cano.update();
        cano.draw();
    }

    // Remove os canos que estão na lista de para remover
    // (que estão fora da tela)
    for (Pipe cano : paraRemover) {
        pipes.remove(cano);
    }
}

// Cooldown para gerar próximo cano
float pipeCooldown = GERACAO_CANO_COOLDOWN;

void draw() {
    background(#20E5F5);

    // Calcula o deltaTime
    calculateDeltaTime();

    // Se o jogo não estiver rodando, desenha a tela parada
    if (!jogoRodando) {
        desenharTelaParada();
        return;
    };

    // Atualiza a câmera
    camX += camSpeed * deltaTime;

    // informacao legal
    textSize(30);
    text("UwU pos x: " + camX, 40, 40);

    // Atualiza e desenha os canos
    atualizarCanos();

    // Atualiza e desenha o pássaro
    passaroJogador.update();
    passaroJogador.draw();
}

// Calcula o tempo desde o último frame (deltaTime) e
// salva na variável deltaTime
void calculateDeltaTime() {
    deltaTime = (millis() - last) / 1000;
    last = millis();
}

void desenharTelaParada() {
    passaroJogador.draw();
    atualizarCanos();
    fill(0);
    textSize(64);
    textAlign(CENTER);
    text("Clique para começar", width/2, height/2);
    textAlign(LEFT);
}

// Quando o mouse for clicado, começa a simulação.
// Fiz isso só para ficar mais fácil de JOGAR, mas
// quando for apenas a simulação, pode tirar isso
void mousePressed() {
    jogoRodando = true;
}

// Processar o pulo (deixei no final pq depois vai ter q tirar) //
// Se já processou o pulo
boolean processouPulo = false;
void keyPressed() {
    if (key == ' ' && !processouPulo) {
        processouPulo = true;
        passaroJogador.jump();
    }
}
void keyReleased() {
    if (key == ' ') {
        processouPulo = false;
    }
}

