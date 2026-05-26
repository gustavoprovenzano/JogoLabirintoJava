// ===== PROJETO LABIRINTO ===== //

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean querJogarNoVamente = true;


        //PRIMEIRA PARTE:

        // Matriz tridimensional usada para armazenar 3 mapas bidimensionais de tamanho 11x7)
        char[][][] bancoDeMapas = {
                // Mapa 1 (O mapa atual)
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

        // Primeiro Loop: Controla se o jogador quer jogar uma nova partida
        while (querJogarNoVamente) {

            // --- Passo 1: Aqui irá randomizar os mapas ---
            // Sorteia um índice de 0 até o tamanho do banco de mapas menos 1
            int mapaSorteado = random.nextInt(bancoDeMapas.length);

            // Cópia criada do mapa em que se encontra o jogador.
            char[][] labirinto = new char[11][7];
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 7; j++) {
                    labirinto[i][j] = bancoDeMapas[mapaSorteado][i][j];
                }
            }
            //--------------------------------------------------------------------------------------------//

                //SEGUNDA PARTE:

            // --- Passo 2: Aqui encontra o jogador de forma dinâmica ---
            // Como o mapa muda, precisamos varrer a matriz no início para achar onde o 'P' foi colocado
            int linhaAtual = 1;
            int colunaAtual = 1;

            for (int i = 0; i < labirinto.length; i++) {
                for (int j = 0; j < labirinto[i].length; j++) {
                    if (labirinto[i][j] == 'P') {
                        linhaAtual = i;
                        colunaAtual = j;
                    }
                }
            }

            boolean ganhou = false;
            System.out.println("\n=== NOVO LABIRINTO GERADO (Mapa Tipo " + (mapaSorteado + 1) + ") ===");
            System.out.println("Encontre a saída 'S'!");

            // Segundo Loop: O jogo em si
            while (!ganhou) {

                // --- Passo A1: Desenho do tabuleiro!! ---
                //Aqui é onde será exibido no terminal toda a estrutura do labirinto
                System.out.println("\n-----------------");
                for (int i = 0; i < labirinto.length; i++) {
                    for (int j = 0; j < labirinto[i].length; j++) {
                        System.out.print(labirinto[i][j] + " ");
                    }
                    System.out.println();
                }
                System.out.println("-----------------");
                //--------------------------------------------------------------------------------------------------------------//

                 //TERCEIRA PARTE:

                // --- Passo B1: Aqui recebe o comando do jogador ---
                System.out.print("Sua jogada (W/A/S/D): ");
                char comando = scanner.next().toLowerCase().charAt(0);

                int proximaLinha = linhaAtual;
                int proximaColuna = colunaAtual;

                // --- Passo 3: Calculando a nova Posição do Jogador! ---
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
                //----------------------------------------------------------------------------------------------

                //QUARTA PARTE:

                // --- Passo 4: Validando a movimentação e a colisão: ---
                char destino = labirinto[proximaLinha][proximaColuna];

                if (destino == '#') {
                    System.out.println("Bateu na parede! Tente outro caminho.");
                } else if (destino == 'S') {
                    labirinto[linhaAtual][colunaAtual] = ' ';
                    labirinto[proximaLinha][proximaColuna] = 'P'; //Aqui atualiza a posição do jogador na Saída
                    ganhou = true;
                } else {
                    labirinto[linhaAtual][colunaAtual] = ' ';
                    linhaAtual = proximaLinha;
                    colunaAtual = proximaColuna;
                    labirinto[linhaAtual][colunaAtual] = 'P'; // Atualiza a nova posição do jogador no caminho livre
                }
            }

            // --- Fim da Partida ---
            System.out.println("\nPARABÉNS! Você escapou deste labirinto!");

            // --- Essa parte é responsável para perguntar ao usuário se quer começar de novo ---
            System.out.print("\nDeseja jogar novamente em um mapa diferente? (S/N): ");
            char resposta = scanner.next().toLowerCase().charAt(0);

            if (resposta != 's') {
                querJogarNoVamente = false; // Aqui encerra a programação, pois se for diferente de S, o jogo termina
                System.out.println("\nObrigado por jogar! Até a próxima.");
            }
        }

        scanner.close();
    }
}