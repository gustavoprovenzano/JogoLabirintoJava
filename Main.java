import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean querJogarNoVamente = true;

        // BANCO DE MAPAS (Três labirintos complexos e diferentes de tamanho 11x7)
        char[][][] bancoDeMapas = {
                // Mapa 1 (O mapa atual que você já conhece)
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
                // Mapa 2 (Inverte o fluxo, força caminhos pelas laterais)
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
                        {'#', '#', '#', ' ', 'S', '#', '#'} // Saída em coluna diferente
                },
                // Mapa 3 (Caminho em formato de "Sigal" / zigue-zague vertical)
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
                        {'#', ' ', 'S', ' ', ' ', ' ', '#'} // Saída deslocada
                }
        };

        // LOOP EXTERNO: Controla se o jogador quer jogar uma nova partida
        while (querJogarNoVamente) {

            // --- PASSO 1: RANDOMIZAR O MAPA ---
            // Sorteia um índice de 0 até o tamanho do banco de mapas menos 1
            int mapaSorteado = random.nextInt(bancoDeMapas.length);

            // Criamos uma cópia do mapa sorteado para não estragar o mapa original do banco
            char[][] labirinto = new char[11][7];
            for (int i = 0; i < 11; i++) {
                for (int j = 0; j < 7; j++) {
                    labirinto[i][j] = bancoDeMapas[mapaSorteado][i][j];
                }
            }

            // --- PASSO 2: ENCONTRAR O JOGADOR DE FORMA DINÂMICA ---
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

            // LOOP INTERNO: O jogo em si (Game Loop clássico)
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
                System.out.print("Sua jogada (W/A/S/D): ");
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

            // --- FIM DA RODADA ATUAL ---
            System.out.println("\nPARABÉNS! Você escapou deste labirinto!");

            // --- PERGUNTA AO JOGADOR SE ELE DESEJA CONTINUAR ---
            System.out.print("\nDeseja jogar novamente em um mapa diferente? (S/N): ");
            char resposta = scanner.next().toLowerCase().charAt(0);

            if (resposta != 's') {
                querJogarNoVamente = false; // Quebra o laço externo e finaliza o programa
                System.out.println("\nObrigado por jogar! Até a próxima.");
            }
        }

        scanner.close();
    }
}