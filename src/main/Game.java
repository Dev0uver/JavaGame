package main;

// Класс игры
public class Game implements Runnable {

    private final GamePanel gamePanel;

    public Game() {

        gamePanel = new GamePanel(); // Инициализация Контейнера
        gamePanel.setFocusable(true); // Позволяет "захватить" экран
        gamePanel.requestFocus(); // Запрашивает захват экрана для ввода
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
