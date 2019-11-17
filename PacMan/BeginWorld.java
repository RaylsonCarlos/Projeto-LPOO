
import greenfoot.*;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class BeginWorld extends World {

    private final int minSize = 16;
    private final int maxSize = 64;
    private final String increaseKey = "up";
    private final String decreaseKey = "down";
    private final int defaultSpeed = 35;
    private final Actor textMinSize = new ImageActor(new GreenfootImage("Tamanho mínimo!", 20, Color.RED, null));
    private final Actor textMaxSize = new ImageActor(new GreenfootImage("Tamanho máximo!", 20, Color.RED, null));

    private Actor pacman;
    private int size;
    private GreenfootImage pacmanSprite;

    /**
     * Cria o cenário de configuração inicial do mundo, em que o jogador pode
     * redimensionar o tamanho do jogo.
     */
    public BeginWorld() {
        super(50, 50, 5);

        // Verifica a pasta de imagens.
        checkImagesFolder();

        // Define as configurações iniciais padrões.
        setDefaults();

        // Prepara o cenário de redimensonamento do jogo.
        prepare();
    }

    /**
     * O jogador pode redimensionar o mundo utilizando as teclas de up e down,
     * respectivamente, até seu tamanho máximo ou mínimo.
     */
    public void act() {
        if (Greenfoot.isKeyDown(increaseKey)) {
            if (size >= maxSize) {
                addObject(textMaxSize, 25, 40);
            } else {
                removeObject(textMinSize);
                size += 4;
                scaleSprite(size, size);
            }
        } else if (Greenfoot.isKeyDown(decreaseKey)) {
            if (size <= minSize) {
                addObject(textMinSize, 25, 40);
            } else {
                removeObject(textMaxSize);
                size -= 4;
                scaleSprite(size, size);
            }
        } else if (Greenfoot.isKeyDown("enter")) {
            try {
                File[] spriteFiles = new File("sprites").listFiles();

                for (File spriteFile : spriteFiles) {
                    BufferedImage bfiSprite = ImageIO.read(spriteFile);

                    int width = bfiSprite.getWidth();
                    int height = bfiSprite.getHeight();
                    int newWidth = width * size / 16;
                    int newHeight = height * size / 16;
                    int typeImageSprite = bfiSprite.getType();

                    BufferedImage bfiSpriteResized = new BufferedImage(newWidth, newHeight, typeImageSprite);

                    Graphics2D gph2D = bfiSpriteResized.createGraphics();
                    gph2D.drawImage(bfiSprite, 0, 0, newWidth, newHeight, null);
                    gph2D.dispose();

                    File spriteFileResized = new File("images/" + spriteFile.getName());
                    int lastPonctuation = spriteFile.getName().lastIndexOf(".");
                    String format = spriteFile.getName().substring(lastPonctuation + 1);
                    ImageIO.write(bfiSpriteResized, format, spriteFileResized);
                }

                PacManWorld pw = new PacManWorld(size);

                new GameController(pw);
                SoundPlayer.getInstance().stopSound(SoundPlayer.SOUND_OF_WIND);
                Greenfoot.setWorld(pw);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Ao iniciar o jogo, tocar uma música de fundo.
     */
    public void started() {
        SoundPlayer.getInstance().playSound(SoundPlayer.SOUND_OF_WIND);
    }

    /**
     * Ao pausar o jogo, parar a música de fundo.
     */
    public void stopped() {
        SoundPlayer.getInstance().stopSound(SoundPlayer.SOUND_OF_WIND);
    }

    /**
     * Redimensiona o pacman de acordo com o X e Y recebidos
     *
     * @param sizeX X em pixels
     * @param sizeY Y em pixels
     */
    private void scaleSprite(int sizeX, int sizeY) {
        try {
            BufferedImage biPacmanSprite = pacmanSprite.getAwtImage();
            int typeImageSprite = biPacmanSprite.getType();
            BufferedImage bi = new BufferedImage(sizeX, sizeY, typeImageSprite);

            Graphics2D gph2D = bi.createGraphics();
            gph2D.drawImage(biPacmanSprite, 0, 0, sizeX, sizeY, null);
            gph2D.dispose();

            File newSprite = new File("pacman_sprite_resized" + Integer.toString(size) + ".png");
            ImageIO.write(bi, "png", newSprite);

            pacman.setImage("pacman_sprite_resized" + Integer.toString(size) + ".png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Define os valores iniciais de tamanho, velocidade e plano de fundo.
     */
    private void setDefaults() {
        size = minSize;
        setBackground("sprites/cell.png");
        Greenfoot.setSpeed(defaultSpeed);
    }

    /**
     * Verifica se a pasta imagens existe, caso não, cria. Então limpa todo seu
     * conteúdo.
     */
    private void checkImagesFolder() {
        try {
            File images = new File("images");

            if (!images.exists()) {
                images.mkdir();
            }

            File[] allFilesAtImage = new File("images").listFiles();

            for (File file : allFilesAtImage) {
                file.delete();
            }
        } catch (SecurityException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Prepara a apresentação da tela inicial com uma explicação de como
     * redefinir o tamanho do pacman.
     */
    private void prepare() {
        String msgTop = "Pressione [" + increaseKey + "/" + decreaseKey + "]\n"
                + "para aumentar/diminuir\n"
                + "o tamanho";
        String msgBottom = "[Enter] para prosseguir";
        pacmanSprite = new GreenfootImage("sprites/west_1.png");

        ImageActor textTop = new ImageActor(new GreenfootImage(msgTop, 20, Color.GREEN, null));
        ImageActor textBottom = new ImageActor(new GreenfootImage(msgBottom, 20, Color.GREEN, null));
        pacman = new ImageActor(pacmanSprite);

        addObject(textTop, 25, 7);
        addObject(textBottom, 25, 45);
        addObject(pacman, 25, 27);
    }
}
