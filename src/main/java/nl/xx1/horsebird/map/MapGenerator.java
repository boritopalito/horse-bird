package nl.xx1.horsebird.map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MapGenerator {
    private static final int MAP_SIZE = 50;
    private static int[][] map = new int[MAP_SIZE][MAP_SIZE];

    public static void main(String[] args) {
        generateMap();
        addGroup(10, 10, 10, 10, 3); // Add a group of EARTH tiles at (10,10) with width and height 10
        writeMapToFile();
    }

    private static void generateMap() {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (i == 0 || i == MAP_SIZE - 1 || j == 0 || j == MAP_SIZE - 1) {
                    map[i][j] = 5; // Set the edge tiles to TREE
                } else {
                    map[i][j] = 2 + (int) (Math.random() * 2); // Generate a random tile ID between 0 and 5
                }
            }
        }
    }

    private static void addGroup(int startX, int startY, int width, int height, int tileId) {
        for (int i = startX; i < startX + width; i++) {
            for (int j = startY; j < startY + height; j++) {
                if (i >= 0 && i < MAP_SIZE && j >= 0 && j < MAP_SIZE) {
                    map[i][j] = tileId;
                }
            }
        }
    }

    private static void writeMapToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/map/world01.map"))) {
            for (int i = 0; i < MAP_SIZE; i++) {
                for (int j = 0; j < MAP_SIZE; j++) {
                    bw.write(map[i][j] + " ");
                }
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}