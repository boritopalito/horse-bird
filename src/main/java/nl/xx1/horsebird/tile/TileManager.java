package nl.xx1.horsebird.tile;

import nl.xx1.horsebird.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import static nl.xx1.horsebird.enums.Tile.getTileById;

public class TileManager {
    Tile[][] tiles;
    int[][] map = new int[GamePanel.COLS][GamePanel.ROWS];

    public TileManager() {
        tiles = new Tile[GamePanel.COLS][GamePanel.ROWS];
        initializeMap();
        loadTiles();
    }

    public void initializeMap() {
        try {
            try (InputStream in = getClass().getResourceAsStream("/map/01.map")) {
                try (BufferedReader br = new BufferedReader(new java.io.InputStreamReader(in))) {
                    for (int y = 0; y < GamePanel.ROWS; y++) {
                        String line = br.readLine();
                        String[] tokens = line.split(" ");
                        for (int x = 0; x < GamePanel.COLS; x++) {
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
        for (int x = 0; x < GamePanel.COLS; x++) {
            for (int y = 0; y < GamePanel.ROWS; y++) {
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
        for (int x = 0; x < GamePanel.COLS; x++) {
            for (int y = 0; y < GamePanel.ROWS; y++) {
                g2d.drawImage(tiles[x][y].image, x * GamePanel.TILE_SIZE, y * GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, null);
            }
        }
    }
}
