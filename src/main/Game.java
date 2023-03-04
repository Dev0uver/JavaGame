package main;

import java.awt.*;

// Класс игры
public class Game implements Runnable {

    private final GamePanel gamePanel;

    public Game() {

        gamePanel = new GamePanel(); // Инициализация Контейнера
        //gameWindow = new GameWindow(gamePanel); // Инициализация объекта окна
        gamePanel.setFocusable(true); // Позволяет "захватить" экран
        gamePanel.requestFocus(); // Запрашивает захват экрана для ввода
        //gamePanel.setPreferredSize(new Dimension(1280, 720));
        //gamePanel.setBounds(0, 0, GameWindow.width, GameWindow.height);
        GameWindow gameWindow = new GameWindow(gamePanel);
        run();
        StartGameThread();

    }


    private void StartGameThread() {

        Thread gameThread = new Thread(this);
        gameThread.start();

    }


    public void run() {

        int fpsSet = 60;
        double timePerFrame = 1000000000.0 / fpsSet;
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
