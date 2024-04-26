package nl.xx1.horsebird.entity;

import lombok.Getter;
import lombok.Setter;
import nl.xx1.horsebird.GamePanel;
import nl.xx1.horsebird.enums.Direction;

import java.awt.*;

@Getter
@Setter
public class Entity {
    private int worldX;
    private int worldY;
    private int speed = 4;
    private Rectangle bounds;
    private boolean collision = false;
    private Direction direction = Direction.DOWN;

    public Entity(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void move(int dx, int dy) {
        worldX += dx;
        worldY += dy;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(worldX, worldY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }
}
