package nl.xx1.horsebird.object;

public class Door extends GameObject {
    @Override
    public String getName() {
        return "door";
    }

    @Override
    public boolean isCollision() {
        return true;
    }
}
