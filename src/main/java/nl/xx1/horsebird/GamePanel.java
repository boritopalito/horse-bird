package nl.xx1.horsebird;

import lombok.extern.java.Log;
import nl.xx1.horsebird.entity.Player;
import nl.xx1.horsebird.input.KeyHandler;
import nl.xx1.horsebird.tile.TileManager;

import javax.swing.*;
import java.awt.*;

@Log
public class GamePanel extends JPanel implements Runnable {

    public static final int ORIGINAL_TILE_SIZE = 16;
    public static final int SCALE = 3;
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    public static final int COLS = 16;
    public static final int ROWS = 12;
    public static final int WIDTH = COLS * TILE_SIZE;
    public static final int HEIGHT = ROWS * TILE_SIZE;

    public final int FPS = 60;

    Thread gameThread;
    public static KeyHandler keyHandler = new KeyHandler();
    public static Player localPlayer = new Player(TILE_SIZE * 23, TILE_SIZE * 21);
    TileManager tileManager = new TileManager();

    public GamePanel() {
        log.info("GamePanel created");
        setBackground(Color.black);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        setDoubleBuffered(true);
        setVisible(true);
        addKeyListener(keyHandler);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        tileManager.graphics(g2d);
        localPlayer.draw(g2d);
    }

    public void update() {
        localPlayer.update();
    }

    @Override
    public void run() {
        log.info("GamePanel run");
        long drawInterval = 1000 / FPS;
        long delta = 0;
        long lastTime = System.currentTimeMillis();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.currentTimeMillis();

            delta += currentTime - lastTime;

            lastTime = currentTime;

            if (delta >= drawInterval) {
                update();
                repaint();
                delta = 0;
            }
        }
    }
}
