package main;

import GUI.Score;
import audio.Audio;

import java.io.FileNotFoundException;

// Класс игры
public class Game implements Runnable {

    private final GamePanel gamePanel;


    public Game() throws InterruptedException {


        gamePanel = new GamePanel(); // Инициализация Контейнера
        gamePanel.setFocusable(true); // Позволяет "захватить" экран
        gamePanel.requestFocus(); // Запрашивает захват экрана для ввода
        GameWindow gameWindow = new GameWindow(gamePanel);
        //Thread menu = new Thread(new Menu(gamePanel)); // запуск потока Menu
        //menu.start();
        //run();

        Audio audio = new Audio();
        audio.soundtrack();
        Score score = new Score();

        try {
            Score.InitScore();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        score.PaintScore(gamePanel.getGraphics());
        Score.PaintHighScore(gamePanel.getGraphics());
        gamePanel.repaint();

        // запуск потока игры
        StartGameThread();
        Thread.sleep(Long.MAX_VALUE);

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

                // приостановка потока gameThread
                synchronized (gamePanel) {
                    if (gamePanel.pauseFlag) {
                        Thread menu = new Thread(new Menu(gamePanel)); // запуск потока Menu
                        menu.start();
                        synchronized (gamePanel) { // синхронизация потоков
                            try {
                                gamePanel.wait(); // установка потока в ожидание
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            gamePanel.buttonsList.clear(); // очистка списка кнопок
                        }
                    }
                }

                gamePanel.repaint();

                    lastFrame = now;
                    frames++;

            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {

                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS: " + frames);
                frames = 0;

            }
        }
    }
}
