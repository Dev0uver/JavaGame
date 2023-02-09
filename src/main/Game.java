package main;

import javax.swing.*;

// Класс игры
public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int fps_set = 60;

    public Game() {

        gamePanel = new GamePanel(); // Инициализация Контейнера
        gameWindow = new GameWindow(gamePanel); // Инициализация объекта окна
        gamePanel.setFocusable(true); // Позволяет "захватить" экран
        gamePanel.requestFocus(); // Запрашивает захват экрана для ввода
        //gamePanel.setBounds(0, 0, GameWindow.width, GameWindow.height);
        StartGameThread();

    }


    private void StartGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }


    public void run() {

        double timePerFrame = 1000000000.0 / fps_set;
        long lastFrame = System.nanoTime();
        long now = System.nanoTime();

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {

            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {

                gamePanel.repaint();
                lastFrame = now;
                frames++;

            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {

                lastCheck = System.currentTimeMillis();
//                System.out.println("FPS: " + frames);
                frames = 0;

            }
        }
    }
}
