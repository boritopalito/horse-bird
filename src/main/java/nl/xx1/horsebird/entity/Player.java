package nl.xx1.horsebird.entity;

import nl.xx1.horsebird.enums.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static nl.xx1.horsebird.GamePanel.TILE_SIZE;
import static nl.xx1.horsebird.GamePanel.keyHandler;

public class Player extends Entity {

    private final BufferedImage[] sprites;
    private int currentSprite = 0;

    private int spriteCounter = 0;

    public Player(int x, int y) {
        super(x, y);
        sprites = setSprites();
    }

    public void update() {
        if (keyHandler.isLeft() || keyHandler.isRight() || keyHandler.isUp() || keyHandler.isDown()) {
            if (keyHandler.isLeft()) {
                move(-getSpeed(), 0);
                currentSprite = Direction.LEFT.getValue();
            }
            if (keyHandler.isRight()) {
                move(getSpeed(), 0);
                currentSprite = Direction.RIGHT.getValue();
            }
            if (keyHandler.isUp()) {
                move(0, -getSpeed());
                currentSprite = Direction.UP.getValue();
            }
            if (keyHandler.isDown()) {
                move(0, getSpeed());
                currentSprite = Direction.DOWN.getValue();
            }


            spriteCounter++;
            if (spriteCounter > 12) {
                if (currentSprite % 2 == 0) {
                    currentSprite++;
                } else {
                    currentSprite--;
                }
                spriteCounter = 0;
            }
        }
    }

    public BufferedImage[] setSprites() {
        BufferedImage[] sprites = new BufferedImage[8];
        try {
            sprites[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_1.png")));
            sprites[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_up_2.png")));
            sprites[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_1.png")));
            sprites[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_down_2.png")));
            sprites[4] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_1.png")));
            sprites[5] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_left_2.png")));
            sprites[6] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_1.png")));
            sprites[7] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/boy_right_2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sprites;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(sprites[Math.max(currentSprite, 0)], getX(), getY(), TILE_SIZE, TILE_SIZE, null);
    }
}
