
import greenfoot.World;

public class DefaultMaze implements MazeFactory {

    private final World world;
    private Portal portal;

    public DefaultMaze(World w) {
        world = w;
    }

    /**
     * Cria as pastilhas e coloca-os dentro do mundo.
     */
    @Override
    public void createPellet() {
        // Pastilhas na posição vertical.
        verticalPellets(3, 3, 19);
        verticalPellets(3, 43, 49);
        verticalPellets(3, 55, 61);
        verticalPellets(7, 49, 55);
        verticalPellets(13, 5, 55);
        verticalPellets(19, 13, 19);
        verticalPellets(19, 49, 55);
        verticalPellets(25, 5, 13);
        verticalPellets(25, 43, 47);
        verticalPellets(31, 5, 11);
        verticalPellets(31, 43, 47);
        verticalPellets(37, 13, 19);
        verticalPellets(37, 49, 55);
        verticalPellets(43, 5, 11);
        verticalPellets(43, 13, 55);
        verticalPellets(49, 49, 55);
        verticalPellets(53, 5, 11);
        verticalPellets(53, 13, 19);
        verticalPellets(53, 43, 49);
        verticalPellets(53, 55, 61);

        // Pastilhas na posição horizontal.
        horizontalPellets(3, 5, 27);
        horizontalPellets(3, 31, 55);
        horizontalPellets(11, 5, 13);
        horizontalPellets(11, 15, 55);
        horizontalPellets(17, 5, 13);
        horizontalPellets(17, 21, 27);
        horizontalPellets(17, 31, 37);
        horizontalPellets(17, 45, 53);
        horizontalPellets(41, 3, 27);
        horizontalPellets(41, 31, 55);
        horizontalPellets(47, 15, 27);
        horizontalPellets(47, 29, 43);
        horizontalPellets(53, 21, 27);
        horizontalPellets(53, 31, 37);
        horizontalPellets(59, 5, 53);

        // Pastilhas em posições específicas.
        world.addObject(new Pellet(), 3, 9);
        world.addObject(new Pellet(), 3, 53);
        world.addObject(new Pellet(), 5, 47);
        world.addObject(new Pellet(), 5, 53);
        world.addObject(new Pellet(), 7, 47);
        world.addObject(new Pellet(), 9, 53);
        world.addObject(new Pellet(), 11, 53);
        world.addObject(new Pellet(), 25, 55);
        world.addObject(new Pellet(), 25, 57);
        world.addObject(new Pellet(), 31, 55);
        world.addObject(new Pellet(), 31, 57);
        world.addObject(new Pellet(), 45, 53);
        world.addObject(new Pellet(), 47, 53);
        world.addObject(new Pellet(), 49, 47);
        world.addObject(new Pellet(), 51, 47);
        world.addObject(new Pellet(), 51, 53);
        world.addObject(new Pellet(), 53, 9);
        world.addObject(new Pellet(), 53, 53);

        // Pastilhas especiais.
        populatePowerPellet();
    }

    /**
     * Cria as pastilhas especiais.
     */
    private void populatePowerPellet() {
        // Remove as pastilhas normais da posição das pastilhas especiais.
        world.removeObjects(world.getObjectsAt(3, 7, Pellet.class));
        world.removeObjects(world.getObjectsAt(3, 47, Pellet.class));
        world.removeObjects(world.getObjectsAt(53, 7, Pellet.class));
        world.removeObjects(world.getObjectsAt(53, 47, Pellet.class));

        // Pastilhas especiais.
        world.addObject(new PowerPellet(), 3, 7);
        world.addObject(new PowerPellet(), 3, 47);
        world.addObject(new PowerPellet(), 53, 7);
        world.addObject(new PowerPellet(), 53, 47);
    }

    /**
     * Cria linhas de pellets verticais.
     *
     * @param x célula de eixo X
     * @param y0 célula de eixo Y incial
     * @param y1 célula de eixo Y final
     */
    private void verticalPellets(int x, int y0, int y1) {
        // Ele começa no eixo y0 e vai até o eixo y1, pulando de 1 em 1.
        for (int i = y0; i < y1; i += 2) {
            world.addObject(new Pellet(), x, i);
        }
    }

    /**
     * Cria linhas de pellets horizontais.
     *
     * @param y célula de eixo Y
     * @param x0 célula de eixo X incial
     * @param x1 célula de eixo X final
     */
    private void horizontalPellets(int y, int x0, int x1) {
        // Ele começa no eixo x0 e vai até o eixo x1, pulando de 1 em 1.
        for (int i = x0; i < x1; i += 2) {
            world.addObject(new Pellet(), i, y);
        }
    }

    /**
     * Cria todas as paredes para criar o labirinto.
     */
    @Override
    public void createWall() {
        // Cria as bordas, as quinas, a jail e os quadrados do labirinto.
        outerEdges();
        externalVertices();
        innerDefaultWalls();
        jail();
    }

    /**
     * Cria a cela, onde os Fanstasmas irão ficar presos no início do jogo.
     */
    private void jail() {
        // Cria um quadrado para a jail dos Fantasmas.
        squareWall(21, 25, 14, 8);
        squareWall(22, 26, 12, 6);
    }

    /**
     * Cria as bordas externas, para delimitar o mundo onde o PacMan, os
     * Fantasmas e as Pastilhas podem agir.
     */
    private void outerEdges() {
        // Paredes na posição vertical.
        verticalLine(1, 1, 19);
        verticalLine(1, 39, 49);
        verticalLine(1, 51, 61);
        verticalLine(11, 19, 27);
        verticalLine(11, 31, 39);
        verticalLine(45, 19, 27);
        verticalLine(45, 31, 39);
        verticalLine(55, 1, 19);
        verticalLine(55, 39, 49);
        verticalLine(55, 51, 61);

        // Paredes na posição horizontal.
        horizontalLine(1, 1, 27);
        horizontalLine(1, 29, 55);
        horizontalLine(19, 1, 11);
        horizontalLine(19, 45, 55);
        horizontalLine(27, -1, 11);
        horizontalLine(27, 45, 56);
        horizontalLine(31, -1, 11);
        horizontalLine(31, 45, 56);
        horizontalLine(39, 1, 11);
        horizontalLine(39, 45, 55);
        horizontalLine(61, 1, 55);
    }

    /**
     * Cria as paredes que representam as quinas externas do labirinto.
     */
    private void externalVertices() {
        // Paredes nas quinas do labirinto.
        world.addObject(new DefaultWall(), 1, 1);
        world.addObject(new DefaultWall(), 1, 19);
        world.addObject(new DefaultWall(), 1, 39);
        world.addObject(new DefaultWall(), 1, 61);
        world.addObject(new DefaultWall(), 11, 19);
        world.addObject(new DefaultWall(), 11, 27);
        world.addObject(new DefaultWall(), 11, 31);
        world.addObject(new DefaultWall(), 11, 39);
        world.addObject(new DefaultWall(), 45, 19);
        world.addObject(new DefaultWall(), 45, 27);
        world.addObject(new DefaultWall(), 45, 31);
        world.addObject(new DefaultWall(), 45, 39);
        world.addObject(new DefaultWall(), 55, 1);
        world.addObject(new DefaultWall(), 55, 19);
        world.addObject(new DefaultWall(), 55, 39);
        world.addObject(new DefaultWall(), 55, 61);
    }

    /**
     * Cria as paredes internas do labirinto.
     */
    private void innerDefaultWalls() {
        squareWall(0, 49, 5, 2);
        squareWall(5, 5, 6, 4);
        squareWall(5, 13, 6, 2);
        squareWall(5, 43, 6, 2);
        squareWall(5, 55, 18, 2);
        squareWall(9, 43, 2, 8);
        squareWall(15, 5, 8, 4);
        squareWall(15, 13, 2, 14);
        squareWall(15, 31, 2, 8);
        squareWall(15, 43, 8, 2);
        squareWall(15, 49, 2, 6);
        squareWall(17, 19, 6, 2);
        squareWall(21, 13, 14, 2);
        squareWall(21, 37, 14, 2);
        squareWall(21, 49, 14, 2);
        squareWall(27, 0, 2, 9);
        squareWall(27, 13, 2, 8);
        squareWall(27, 37, 2, 8);
        squareWall(21, 37, 14, 2);
        squareWall(27, 49, 2, 8);
        squareWall(33, 5, 8, 4);
        squareWall(33, 19, 6, 2);
        squareWall(33, 43, 8, 2);
        squareWall(33, 55, 18, 2);
        squareWall(39, 13, 2, 14);
        squareWall(39, 31, 2, 8);
        squareWall(39, 49, 2, 6);
        squareWall(45, 5, 6, 4);
        squareWall(45, 13, 6, 2);
        squareWall(45, 43, 2, 8);
        squareWall(45, 43, 6, 2);
        squareWall(51, 49, 5, 2);
    }

    /**
     * Cria linhas de paredes verticais.
     *
     * @param x célula de eixo X
     * @param y0 célula de eixo Y incial
     * @param y1 célula de eixo Y final
     */
    private void verticalLine(int x, int y0, int y1) {
        for (int i = y0 + 1; i < y1; i++) {
            world.addObject(new DefaultWall(), x, i);
        }
    }

    /**
     * Cria linhas de paredes horizontais.
     *
     * @param y célula de eixo Y
     * @param x0 célula de eixo X incial
     * @param x1 célula de eixo X final
     */
    private void horizontalLine(int y, int x0, int x1) {
        for (int i = x0 + 1; i < x1; i++) {
            world.addObject(new DefaultWall(), i, y);
        }
    }

    /**
     * Cria os quadrados, que serão usados para formar o labirinto.
     *
     * @param x0 eixo X incial
     * @param y0 eixo Y inicial
     * @param width largura do quadrado
     * @param height altura do quadrado
     */
    private void squareWall(int x0, int y0, int width, int height) {
        // Cria duas variáveis que recebem os valores dos eixos X final e
        // Y final.
        int xMax = x0 + width;
        int yMax = y0 + height;

        // Adiciona objetos do tipo DefaultWall para criar as bordas dos quadrados.
        world.addObject(new DefaultWall(), x0, y0);
        world.addObject(new DefaultWall(), xMax, y0);
        world.addObject(new DefaultWall(), x0, yMax);
        world.addObject(new DefaultWall(), xMax, yMax);

        // Usa um laço para percorrer desde o X inicial até o X final.
        for (int i = x0 + 1; i < xMax; i++) {
            // Adiciona objetos do tipo DefaultWall nos lados paralelos do quadrado.
            world.addObject(new DefaultWall(), i, y0);
            world.addObject(new DefaultWall(), i, yMax);
        }

        // Usa um laço para percorrer desde o Y inicial até o Y final
        for (int j = y0 + 1; j < yMax; j++) {
            // Adiciona objetos do tipo DefaultWall nos lados paralelos do quadrado.
            world.addObject(new DefaultWall(), x0, j);
            world.addObject(new DefaultWall(), xMax, j);
            world.addObject(new DefaultWall(), x0, y0);
            world.addObject(new DefaultWall(), xMax, y0);
            world.addObject(new DefaultWall(), x0, yMax);
            world.addObject(new DefaultWall(), xMax, yMax);

            // Usa um laço para percorrer desde o X inicial até o X final.
            for (int i = x0 + 1; i < xMax; i++) {
                // Adiciona objetos do tipo DefaultWall nos lados paralelos do quadrado.
                world.addObject(new DefaultWall(), i, y0);
                world.addObject(new DefaultWall(), i, yMax);
            }

            // Usa um laço para percorrer desde o Y inicial até o Y final
            for (j = y0 + 1; j < yMax; j++) {
                // Adiciona objetos do tipo DefaultWall nos lados paralelos do quadrado.
                world.addObject(new DefaultWall(), x0, j);
                world.addObject(new DefaultWall(), xMax, j);
            }
        }
    }

    @Override
    public void createPortal() {
        portal = new Portal(world, 0, 29, 56, 29);
    }

    @Override
    public Portal getPortal() {
        return portal;
    }
}
