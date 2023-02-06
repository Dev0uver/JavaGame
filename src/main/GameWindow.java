package main;

import javax.swing.JFrame;

// Класс игрового окна
public class GameWindow {

    private JFrame frame;
    public static int width = 1280;
    public static int height = 720;

    // Создание игрового окна
    public GameWindow(GamePanel gamePanel) {

        frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setResizable(true);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);

        frame.add(gamePanel);

        frame.setVisible(true);

    }
}
