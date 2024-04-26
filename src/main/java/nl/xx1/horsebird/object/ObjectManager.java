package nl.xx1.horsebird.object;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class ObjectManager {
    private static final List<GameObject> objects = new LinkedList<>();

    public static void addObject(GameObject object) {
        objects.add(object);
    }

    public static Stream<GameObject> filter(Predicate<GameObject> predicate) {
        return objects.stream().filter(predicate);
    }

    public static void removeObject(GameObject object) {
        objects.remove(object);
    }

    public static void clear() {
        objects.clear();
    }

    public static int getSize() {
        return objects.size();
    }

    public static List<GameObject> all() {
        return objects;
    }

    public static void draw(Graphics2D g2d) {
        for (GameObject object : objects) {
            object.draw(g2d);
        }
    }
}
