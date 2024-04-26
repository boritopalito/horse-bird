package nl.xx1.horsebird;

import nl.xx1.horsebird.entity.Entity;
import nl.xx1.horsebird.tile.Tile;
import nl.xx1.horsebird.tile.TileManager;

import java.awt.*;

public class CollisionDetector {
    public static boolean checkTile(Entity entity) {
        Rectangle solidArea = entity.getBounds();

        int entityLeft = entity.getWorldX() + solidArea.x;
        int entityRight = entity.getWorldX() + solidArea.x + solidArea.width;
        int entityTop = entity.getWorldY() + solidArea.y;
        int entityBottom = entity.getWorldY() + solidArea.y + solidArea.height;

        int tileLeft = entityLeft / GamePanel.TILE_SIZE;
        int tileRight = entityRight / GamePanel.TILE_SIZE;
        int tileTop = entityTop / GamePanel.TILE_SIZE;
        int tileBottom = entityBottom / GamePanel.TILE_SIZE;

        Tile tile1, tile2;

        switch (entity.getDirection()) {
            case UP:
                tileTop = (entityTop - entity.getSpeed()) / GamePanel.TILE_SIZE;
                tile1 = TileManager.tiles[tileLeft][tileTop];
                tile2 = TileManager.tiles[tileRight][tileTop];
                if (tile1.collision || tile2.collision)
                    return false;
                break;
            case DOWN:
                tileBottom = (entityBottom + entity.getSpeed()) / GamePanel.TILE_SIZE;
                tile1 = TileManager.tiles[tileLeft][tileBottom];
                tile2 = TileManager.tiles[tileRight][tileBottom];
                if (tile1.collision || tile2.collision)
                    return false;
                break;
            case LEFT:
                tileLeft = (entityLeft - entity.getSpeed()) / GamePanel.TILE_SIZE;
                tile1 = TileManager.tiles[tileLeft][tileTop];
                tile2 = TileManager.tiles[tileLeft][tileBottom];
                if (tile1.collision || tile2.collision)
                    return false;
                break;
            case RIGHT:
                tileRight = (entityRight + entity.getSpeed()) / GamePanel.TILE_SIZE;
                tile1 = TileManager.tiles[tileRight][tileTop];
                tile2 = TileManager.tiles[tileRight][tileBottom];
                if (tile1.collision || tile2.collision)
                    return false;
                break;
        }
        return true;
    }
}
