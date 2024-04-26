package nl.xx1.horsebird.object;

import lombok.Getter;
import lombok.Setter;
import nl.xx1.horsebird.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static nl.xx1.horsebird.GamePanel.TILE_SIZE;

@Getter
@Setter
public abstract class GameObject {
    private BufferedImage image;
    private String name;
    private boolean collision;
    private int worldX;
    private int worldY;

    public abstract String getName();

    public GameObject() {
        try {
            BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(String.format("/objects/%s.png", getName()))));
            setImage(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2d) {
        int screenX = worldX - GamePanel.localPlayer.getWorldX() + GamePanel.localPlayer.getScreenX();
        int screenY = worldY - GamePanel.localPlayer.getWorldY() + GamePanel.localPlayer.getScreenY();
        g2d.drawImage(image, screenX, screenY, TILE_SIZE, TILE_SIZE, null);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        GameObject that = (GameObject) object;
        return collision == that.collision && worldX == that.worldX && worldY == that.worldY && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, collision, worldX, worldY);
    }
}
