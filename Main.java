import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 1. CRIAÇÃO DO MAPA (MATRIZ 11x7)
        // O mapa foi redesenhado para ter um caminho real, curvas e becos sem saída.
        char[][] labirinto = {
                {'#', '#', '#', '#', '#', '#', '#'},
                {'#', 'P', ' ', ' ', '#', ' ', '#'},
                {'#', '#', '#', ' ', '#', ' ', '#'},
                {'#', ' ', ' ', ' ', ' ', ' ', '#'}, // Linha 3 abre caminhos cruzados
                {'#', ' ', '#', '#', '#', '#', '#'}, // Beco sem saída se o jogador for para a esquerda
                {'#', ' ', ' ', ' ', ' ', ' ', '#'},
                {'#', '#', '#', '#', '#', ' ', '#'}, // Bloqueio estratégico
                {'#', ' ', ' ', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', '#', ' ', '#'},
                {'#', ' ', '#', ' ', ' ', ' ', '#'}, // Rota de fuga antes da saída
                {'#', '#', '#', 'S', '#', '#', '#'}
        };

        // 2. VARIÁVEIS DE POSIÇÃO
        // O jogador ainda começa em (1, 1)
        int linhaAtual = 1;
        int colunaAtual = 1;

        Scanner scanner = new Scanner(System.in);
        boolean ganhou = false;

        System.out.println("=== BEM-VINDO AO LABIRINTO COMPLEXO! ===");
        System.out.println("Use W (cima), A (esquerda), S (baixo), D (direita) para mover.");

        // 3. LOOP PRINCIPAL DO JOGO (GAME LOOP)
        while (!ganhou) {

            // --- PASSO A: DESENHAR O TABULEIRO ---
            System.out.println("\n-----------------");
            for (int i = 0; i < labirinto.length; i++) {
                for (int j = 0; j < labirinto[i].length; j++) {
                    System.out.print(labirinto[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println("-----------------");

            // --- PASSO B: RECEBER O COMANDO DO JOGADOR ---
            System.out.print("Sua jogada: ");
            char comando = scanner.next().toLowerCase().charAt(0);

            int proximaLinha = linhaAtual;
            int proximaColuna = colunaAtual;

            // --- PASSO C: CALCULAR A NOVA POSIÇÃO ---
            if (comando == 'w') {
                proximaLinha--;
            } else if (comando == 's') {
                proximaLinha++;
            } else if (comando == 'a') {
                proximaColuna--;
            } else if (comando == 'd') {
                proximaColuna++;
            } else {
                System.out.println("Comando inválido! Use apenas W, A, S ou D.");
                continue;
            }

            // --- PASSO D: VALIDAÇÃO DO MOVIMENTO e COLISÃO ---
            char destino = labirinto[proximaLinha][proximaColuna];

            if (destino == '#') {
                System.out.println("Bateu na parede! Tente outro caminho.");
            } else if (destino == 'S') {
                labirinto[linhaAtual][colunaAtual] = ' ';
                labirinto[proximaLinha][proximaColuna] = 'P';
                ganhou = true;
            } else {
                labirinto[linhaAtual][colunaAtual] = ' ';
                linhaAtual = proximaLinha;
                colunaAtual = proximaColuna;
                labirinto[linhaAtual][colunaAtual] = 'P';
            }
        }

        // --- 4. FIM DE JOGO ---
        System.out.println("\n--- MAPA FINAL ---");
        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto[i].length; j++) {
                System.out.print(labirinto[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nPARABÉNS! Você desvendou o labirinto complexo e encontrou a saída!");
        scanner.close();
    }
}