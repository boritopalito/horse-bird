package nl.xx1.horsebird.object;

public class Chest extends GameObject {
    @Override
    public String getName() {
        return "chest";
    }

    @Override
    public boolean isCollision() {
        return false;
    }
}
