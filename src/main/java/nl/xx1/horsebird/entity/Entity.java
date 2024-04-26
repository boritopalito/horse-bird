package nl.xx1.horsebird.entity;

import lombok.Getter;
import nl.xx1.horsebird.GamePanel;

import java.awt.*;

@Getter
public class Entity {
    private int x;
    private int y;
    private int speed = 4;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.fillRect(x, y, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE);
    }
}
