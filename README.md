# PacMan

Grupo: Carlos Roberto, Raylson Carlos, Weydson Victor

Projeto: Criar um jogo similar ao PacMan.

## Descrição

O jogo irá seguir a idéia base do jogo conhecido como “PacMan”, em que o jogador controla uma cabeça redonda e faminta com uma boca que se abre e fecha, posicionado num labirinto repleto de pastilhas, algumas pastilhas especiais e 4 fantasmas que o perseguirão. O objetivo do jogo é consumir todas as pastilhas sem ser alcançado pelos fantasmas.

## Domínio do problema

Criar um jogo em que um objeto controlável pelo usuário (PacMan) percorre um cenário com obstáculos, formando um labirinto, em busca de pastilhas imóveis que serão consumidas e somarão pontos. Enquanto o PacMan se move será perseguido por fantasmas e em caso de colisão o jogo acabará. Os fantasmas irão iniciar numa prisão e serão liberados com o passar do tempo. Caso o jogador consuma uma pastilha especial se abrirá uma janela de tempo onde será possível consumir fantasmas e somar pontos, fantasmas consumidos se transformaram em olhos que irão percorrer o labirinto indo para a prisão onde se transformarão em fantasmas novamente.

## Classes

- PacManWorld extends World: Representa o cenário em que os objetos irão interagir;

- Character extends Actor: Cria os movimentos básicos e a animação básica

- PacMan extends Character: Movimenta-se com os comandos do usuário, consome as pastilhas pelo cenário e ocasionalmente os fantasmas;

- Fantasma extends Character: Movimenta-se pelo cenário, perseguindo o PacMan;

- Pellet extends Actor: Permanece imóvel no cenário, em caso de colisão com o PacMan é removido do PacManWorld e acrescenta pontos ao jogador.

- PowerPellet extends Pastilha: Permanece imóvel no cenário, em caso de colisão com o PacMan é removido do PacManWorld, acrescenta pontos ao jogador e torna temporariamente os fantasmas em consumíveis pelo PacMan.

- Wall extends Actor: representa as paredes do labirinto, impedindo a passagem do PacMan e dos fantasmas.

Recursos/Técnicas: Criação de classes e objetos; interação entre os objetos e o mundo, e entre si; manipulação de objetos através do teclado; utilização de recursos musicais.

Ferramentas: Greenfoot versão 2.4.2

## Cronograma

01/09 ~ 17/09 : Animações e implementação de Classes

17/09 ~ 24/09 : Som

24/09 ~ 01/10 : Implementação da mecânica do Jogo

01/10 ~ 08/10 : Entrega do protótipo para avaliação

08/10 ~ 15/10 : Testes

15/10 ~ 22/10 : Correção de bugs, melhorias e entrega final



