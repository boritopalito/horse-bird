package nl.xx1.horsebird.enums;

public enum Direction {
    NOT_MOVING(-1),
    UP(0),
    DOWN(2),
    LEFT(4),
    RIGHT(6);

    private final int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
