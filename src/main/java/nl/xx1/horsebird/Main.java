package nl.xx1.horsebird;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Horse Bird Adventures");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        GamePanel gamePanel = new GamePanel();
        jFrame.add(gamePanel);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        gamePanel.startGameThread();
    }
}