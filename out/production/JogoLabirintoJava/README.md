🎮 Jogo do Labirinto em Java
      Este projeto consiste em um jogo de labirinto interativo baseado em console (terminal), desenvolvido na linguagem Java. O sistema utiliza o conceito de matrizes tridimensionais e bidimensionais para armazenar mapas, gerenciar a posição do jogador e renderizar o cenário dinamicamente a cada rodada.

🚀 Funcionalidades Principais
  Seleção Aleatória de Mapas: O jogo conta com um banco contendo 3 mapas desenhados manualmente. A cada nova partida, o módulo java.util.Random sorteia um labirinto diferente.
  Manipulação de Matrizes Dinâmicas: Para preservar os mapas originais, o sistema gera uma cópia em memória da matriz sorteada antes de iniciar o jogo.
  Validação de Movimentos e Colisões: O código detecta as intenções de movimento do usuário, impedindo-o de atravessar paredes (#) e exibindo alertas interativos.
  Estrutura de Repetição Infinita (Game Loop): O jogo mantém uma interface ativa através de menus interativos, permitindo que o usuário jogue múltiplas partidas até optar por sair.
  Arquitetura Modular: Todo o sistema foi dividido em funções específicas, facilitando a legibilidade, manutenção e organização do código.
  
  🕹️ Como Jogar
Os elementos do mapa são representados visualmente no terminal por:
P: O jogador.
#: Paredes (bloqueiam a movimentação).
(Espaço vazio): Caminhos livres transitáveis.
S: A saída do labirinto (objetivo final).
Comandos de Movimentação:
W ou w: Mover para Cima
S ou s: Mover para Baixo
A ou a: Mover para a Esquerda
D ou d: Mover para a Direita

🛠️ PARTE 2: Documentação Técnica do Código
Esta parte detalha matematicamente e logicamente o papel de cada função implementada no arquivo Main.java.
1. main(String[] args)Descrição: Ponto de entrada do programa. Gerencia o fluxo principal através de um menu iterativo controlado por uma estrutura while e um switch-case.Tratamento de Exceção: Inclui um bloco try-catch para capturar erros do tipo NumberFormatException, garantindo que o programa não quebre caso o usuário digite letras no menu numérico.
2. exibirMenu() e exibirRegras()Descrição: Funções utilitárias puramente estéticas responsáveis por imprimir na tela o menu gráfico em blocos de caracteres Unicode e as instruções de jogabilidade, respectivamente.
3. executarJogo(Scanner scanner)Descrição: Atua como o controlador geral de partidas. Ela inicializa a matriz tridimensional bancoDeMapas, sorteia o índice do mapa atual e gerencia o loop que pergunta ao jogador se ele deseja continuar jogando após vencer um labirinto.
4. copiarMapa(char[][] mapaOriginal)Descrição: Recebe a matriz de referência do banco de mapas e gera uma nova instância de matriz bidimensional de tamanho fixed de $11 \times 7$.Mecânica: Utiliza dois laços de repetição for encadeados para realizar uma cópia profunda (deep copy) dos valores char elementares, evitando que as modificações de movimento do jogador alterem permanentemente o banco de dados de mapas.Retorno: char[][] (Cópia limpa do mapa).
5. encontrarJogador(char[][] labirinto)Descrição: Percorre a matriz do jogo linha por linha à procura da posição inicial do caractere 'P'.Retorno: Retorna um array unidimensional de inteiros com duas posições int[]{linha, coluna} representando a coordenada cartesiana do jogador. Caso não encontre, retorna a posição padrão [1, 1].
6. desenharLabirinto(char[][] labirinto)Descrição: Limpa visualmente o buffer textual imprimindo linhas delimitadoras e varre a matriz utilizando estruturas for para exibir os caracteres espaciais na tela, adicionando espaçamentos para manter a proporção visual do tabuleiro no console.
7. jogarPartida(char[][] labirinto, Scanner scanner)Descrição: Concentra toda a física e regras de movimentação do jogo em um loop contínuo até que a variável booleana ganhou torne-se verdadeira.

Lógica de Atualização:Captura o comando do teclado (W, A, S, D).Calcula de forma preditiva as variáveis proximaLinha e proximaColuna.Testa o caractere de destino:Se for #: Cancela a ação e emite um alerta.Se for S: Finaliza o laço definindo ganhou = true.Se for caminho livre: Substitui a coordenada antiga por um espaço vazio ' ' e move o 'P' para a nova coordenada calculada.
