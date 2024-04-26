package nl.xx1.horsebird.enums;

import lombok.Getter;

@Getter
public enum Tile {
    TREE(5, "tree.png", true),
    SAND(4, "sand.png", false),
    EARTH(3, "earth.png", false),
    GRASS(2,"grass.png", false),
    WATER(1, "water.png", true),
    WALL(0,"wall.png", true);

    private final int id;
    private final String image;
    private final boolean collision;

    private Tile(int id, String image, boolean collision) {
        this.id = id;
        this.image = image;
        this.collision = collision;
    }

    public static Tile getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }

    public static Tile getTileById(int id) {
        for (Tile tile : values()) {
            if (tile.getId() == id) {
                return tile;
            }
        }
        return null;
    }
}
