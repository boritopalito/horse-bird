package org.example;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

@Log
public class GamePanel extends JPanel implements Runnable {

    private final int ORIGINAL_TILE_SIZE = 16;
    private final int SCALE = 3;
    private final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    private final int COLS = 16;
    private final int ROWS = 12;
    private final int WIDTH = COLS * TILE_SIZE;
    private final int HEIGHT = ROWS * TILE_SIZE;

    public GamePanel() {
        log.info("GamePanel created");
        setBackground(Color.black);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        setVisible(true);
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.white);
        g.drawString("Hello world!", 400, 300);
        Graphics2D g2d = (Graphics2D) g;
    }

    @Override
    public void run() {
        log.info("GamePanel run");
        while (true) {
            log.info("Looping...");
            repaint();
        }
    }
}
