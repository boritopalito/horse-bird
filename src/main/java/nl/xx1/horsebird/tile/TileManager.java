package nl.xx1.horsebird.tile;

import nl.xx1.horsebird.GamePanel;
import nl.xx1.horsebird.map.WorldMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static nl.xx1.horsebird.enums.Tile.getTileById;

public class TileManager {
    public static Tile[][] tiles;
    int[][] map = new int[WorldMap.MAP_SIZE][WorldMap.MAP_SIZE];

    public TileManager() {
        tiles = new Tile[WorldMap.MAP_SIZE][WorldMap.MAP_SIZE];
        initializeMap("world01.map");
        loadTiles();
    }

    public void initializeMap(String mapName) {
        try {
            try (InputStream in = getClass().getResourceAsStream(String.format("/map/%s", mapName))) {
                try (BufferedReader br = new BufferedReader(new java.io.InputStreamReader(in))) {
                    for (int y = 0; y < WorldMap.MAP_SIZE; y++) {
                        String line = br.readLine();
                        String[] tokens = line.split(" ");
                        for (int x = 0; x < WorldMap.MAP_SIZE; x++) {
                            map[x][y] = Integer.parseInt(tokens[x]);
                        }
                    }

                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTiles() {
        for (int x = 0; x < WorldMap.MAP_SIZE; x++) {
            for (int y = 0; y < WorldMap.MAP_SIZE; y++) {
                nl.xx1.horsebird.enums.Tile tile = getTileById(map[x][y]);
                tiles[x][y] = new Tile();
                try {
                    tiles[x][y].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + tile.getImage())));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                tiles[x][y].collision = tile.isCollision();
            }
        }
    }

    public void graphics(Graphics2D g2d) {
        for (int x = 0; x < WorldMap.MAP_SIZE; x++) {
            for (int y = 0; y < WorldMap.MAP_SIZE; y++) {
                int worldX = x * GamePanel.TILE_SIZE;
                int worldY = y * GamePanel.TILE_SIZE;
                int screenX = worldX - GamePanel.localPlayer.getWorldX() + GamePanel.localPlayer.getScreenX();
                int screenY = worldY - GamePanel.localPlayer.getWorldY() + GamePanel.localPlayer.getScreenY();
                g2d.drawImage(tiles[x][y].image, screenX, screenY, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
            }
        }
    }
}
