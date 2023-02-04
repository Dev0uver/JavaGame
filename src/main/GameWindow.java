package main;

import javax.swing.JFrame;

// Класс игрового окна
public class GameWindow {

    private JFrame frame;

    // Создание игрового окна
    public GameWindow() {
        frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setResizable(true);
        frame.setSize(1280, 720);
        frame.setVisible(true);
    }
}
