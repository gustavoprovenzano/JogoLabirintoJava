// ===== PROJETO LABIRINTO ===== //

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        executarJogo();
    }

    // Função principal responsável por controlar o jogo
    public static void executarJogo() {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean querJogarNovamente = true;

        // Matriz tridimensional usada para armazenar 3 mapas bidimensionais (11x7)
        char[][][] bancoDeMapas = {

                // Mapa 1
                {
                        {'#', '#', '#', '#', '#', '#', '#'},
                        {'#', 'P', ' ', ' ', '#', ' ', '#'},
                        {'#', '#', '#', ' ', '#', ' ', '#'},
                        {'#', ' ', ' ', ' ', ' ', ' ', '#'},
                        {'#', ' ', '#', '#', '#', '#', '#'},
                        {'#', ' ', ' ', ' ', ' ', ' ', '#'},
                        {'#', '#', '#', '#', '#', ' ', '#'},
                        {'#', ' ', ' ', ' ', '#', ' ', '#'},
                        {'#', ' ', '#', ' ', '#', ' ', '#'},
                        {'#', ' ', '#', ' ', ' ', ' ', '#'},
                        {'#', '#', '#', 'S', '#', '#', '#'}
                },

                // Mapa 2
                {
                        {'#', '#', '#', '#', '#', '#', '#'},
                        {'#', 'P', ' ', ' ', ' ', ' ', '#'},
                        {'#', ' ', '#', '#', '#', ' ', '#'},
                        {'#', ' ', '#', ' ', ' ', ' ', '#'},
                        {'#', ' ', '#', ' ', '#', '#', '#'},
                        {'#', ' ', ' ', ' ', ' ', ' ', '#'},
                        {'#', '#', '#', '#', '#', ' ', '#'},
                        {'#', ' ', ' ', ' ', '#', ' ', '#'},
                        {'#', ' ', '#', ' ', '#', ' ', '#'},
                        {'#', ' ', '#', ' ', ' ', ' ', '#'},
                        {'#', '#', '#', ' ', 'S', '#', '#'}
                },

                // Mapa 3
                {
                        {'#', '#', '#', '#', '#', '#', '#'},
                        {'#', 'P', '#', ' ', ' ', ' ', '#'},
                        {'#', ' ', '#', ' ', '#', ' ', '#'},
                        {'#', ' ', '#', ' ', '#', ' ', '#'},
                        {'#', ' ', ' ', ' ', '#', ' ', '#'},
                        {'#', '#', '#', '#', '#', ' ', '#'},
                        {'#', ' ', ' ', ' ', ' ', ' ', '#'},
                        {'#', ' ', '#', '#', '#', '#', '#'},
                        {'#', ' ', ' ', ' ', ' ', ' ', '#'},
                        {'#', '#', '#', '#', '#', ' ', '#'},
                        {'#', ' ', 'S', ' ', ' ', ' ', '#'}
                }
        };

        while (querJogarNovamente) {

            int mapaSorteado = random.nextInt(bancoDeMapas.length);

            char[][] labirinto = copiarMapa(bancoDeMapas[mapaSorteado]);

            System.out.println("\n=== NOVO LABIRINTO GERADO (Mapa Tipo " + (mapaSorteado + 1) + ") ===");
            System.out.println("Encontre a saída 'S'!");

            jogarPartida(labirinto, scanner);

            System.out.println("\nPARABÉNS! Você escapou deste labirinto!");

            System.out.print("\nDeseja jogar novamente em um mapa diferente? (S/N): ");
            char resposta = scanner.next().toLowerCase().charAt(0);

            if (resposta != 's') {
                querJogarNovamente = false;
                System.out.println("\nObrigado por jogar! Até a próxima.");
            }
        }

        scanner.close();
    }

    // Faz uma cópia do mapa sorteado
    public static char[][] copiarMapa(char[][] mapaOriginal) {

        char[][] copia = new char[11][7];

        for (int i = 0; i < mapaOriginal.length; i++) {
            for (int j = 0; j < mapaOriginal[i].length; j++) {
                copia[i][j] = mapaOriginal[i][j];
            }
        }

        return copia;
    }

    // Procura a posição inicial do jogador
    public static int[] encontrarJogador(char[][] labirinto) {

        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto[i].length; j++) {

                if (labirinto[i][j] == 'P') {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{1, 1};
    }

    // Exibe o labirinto no terminal
    public static void desenharLabirinto(char[][] labirinto) {

        System.out.println("\n-----------------");

        for (int i = 0; i < labirinto.length; i++) {
            for (int j = 0; j < labirinto[i].length; j++) {
                System.out.print(labirinto[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("-----------------");
    }

    // Controla uma partida completa
    public static void jogarPartida(char[][] labirinto, Scanner scanner) {

        int[] posicao = encontrarJogador(labirinto);

        int linhaAtual = posicao[0];
        int colunaAtual = posicao[1];

        boolean ganhou = false;

        while (!ganhou) {

            desenharLabirinto(labirinto);

            System.out.print("Sua jogada (W/A/S/D): ");
            char comando = scanner.next().toLowerCase().charAt(0);

            int proximaLinha = linhaAtual;
            int proximaColuna = colunaAtual;

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
    }
}