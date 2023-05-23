package main;

import GUI.Score;
import audio.Audio;
import entities.Bullet;
import entities.Enemy;
import entities.Player;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Класс игры
public class Game implements Runnable {

    private final GamePanel gamePanel;

    private Player player;
    private Score score;

    private final List<Enemy> enemyList = new ArrayList<>();
    private final List<Bullet> bulletList = new ArrayList<>();

    private Menu menu;
    public Game() throws InterruptedException {


        gamePanel = new GamePanel(this); // Инициализация Контейнера
        gamePanel.setFocusable(true); // Позволяет "захватить" экран
        gamePanel.requestFocus(); // Запрашивает захват экрана для ввода
        GameWindow gameWindow = new GameWindow(gamePanel);
        menu  = new Menu(gamePanel);
        Initializer.Initialization();

        gamePanel.PaintBackground(gamePanel.getGraphics()); // отрисовка фона до меню

        menu.MainMenu();
        initClasses();



        Audio.Soundtrack();



        // запуск потока игры
        StartGameLoop();
        Thread.sleep(Long.MAX_VALUE);

    }


    private void StartGameLoop() {

        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() throws IOException {
        if (!gamePanel.pauseFlag) {
            player.UpdatePos();
            if (player.shooting) {
                Shot();
            }
            MoveEnemy();
            for (Bullet bullet : bulletList) {
                bullet.MoveBullet();
            }
            CheckEnemyCollision();
            CheckTopReach();
        }
    }

    public void render(Graphics graphics) {

            if (player != null) {
                player.Render(graphics);
            }
            for (Enemy enemy : enemyList) {
                enemy.Render(graphics);
            }
            for (Bullet bullet : bulletList) {
                bullet.Render(graphics);
            }
            if (score != null) {
                score.RenderScore(graphics);
                score.RenderHighScore(graphics);
                score.RenderWave(graphics);// счетчик волн
            }

    }


    private void initClasses() {

        player = new Player((float) ((GameWindow.size.getWidth() / 2) - Player.playerSprite.getWidth() / 2), (float) (GameWindow.size.getHeight() - 200));
        score = new Score();
        try {
            score.InitScore();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        initFleet();
    }

    private void initFleet() {

        int availableSpaceX = (int) (GameWindow.size.getWidth() - (2 * Enemy.enemySprite.getWidth()));
        int amountX = availableSpaceX / (2 * Enemy.enemySprite.getHeight());

        int availableSpaceY = (int) GameWindow.size.getHeight() - (3 * Enemy.enemySprite.getHeight() - Player.playerSprite.getHeight());
        int amountY = availableSpaceY / (3 * Enemy.enemySprite.getHeight());

        for (int rowNumber = 0; rowNumber < amountY; rowNumber++) {
            for (int enemyNumber = 0; enemyNumber < amountX; enemyNumber++) {
                enemyList.add(CreateEnemy(rowNumber, enemyNumber));
            }
        }
    }

    private Enemy CreateEnemy(int rowNumber, float enemyNumber) {

        Enemy enemy = new Enemy(0, 0);
        enemy.SetPosX(Enemy.enemySprite.getWidth() + 2 * Enemy.enemySprite.getWidth() * enemyNumber);
        enemy.SetPosY(Enemy.enemySprite.getHeight() + 2 * Enemy.enemySprite.getHeight() * rowNumber);
        return enemy;
    }

    private void Shot() {

        if (System.currentTimeMillis() - player.lastCheck >= 300) {
            CreateBullet();
            player.lastCheck = System.currentTimeMillis();
        }
    }
    public void CreateBullet() {

        Bullet bullet = new Bullet(player.GetPosX(), player.GetPosY());
        bulletList.add(bullet);
        Audio.Shot();
    }

    private void reset() throws IOException {
        enemyList.clear();
        bulletList.clear();
        menu.Defeat();
        initClasses();
        gamePanel.pauseFlag = true;
    }

    private void MoveEnemy() throws IOException {
        // !! Управление врагами происходит из переменных описанных выше !!
        boolean down = false;
        if(enemyList.size() != 0){ // проверка, закончились ли враги
        for (int i = 0; i < enemyList.size(); i++) {
            // Проверка достижения правого края
            if (enemyList.get(i).GetPosX() + Enemy.width >= GameWindow.size.getWidth()) {
                // Означает что надо двигать впараво
                Enemy.direction = false;
                // Означает что надо двигать вниз
                down = true;
            }
            // Проверка достижения левого края
            if (enemyList.get(i).GetPosX() <= 0) {
                Enemy.direction = true;
                down = true;
            }
            // Проверка не вышли ли враги за нижнюю границу
            if (enemyList.get(i).GetPosY() >= player.GetPosY() - Player.playerSprite.getHeight()) {
                score.SaveHighScore();
                reset();


            }
        }
        }
        else{
            initFleet();
            score.wave++;
        }

        // Перемещение вниз при достижении края
        if (down) {
            for (Enemy enemy : enemyList) {
                enemy.JumpDown();
            }
        }
        // Движение вправо или влево
        for (Enemy enemy : enemyList) {
            enemy.Move();
        }
    }

    private void CheckTopReach() {

        synchronized (bulletList) {

            for (int i = 0; i < bulletList.size(); i++) {

                if (bulletList.get(i).GetPosY() <= -Bullet.height) {

                    bulletList.remove(i);
                }
            }
        }
    }


    // Коллизия
    private void CheckEnemyCollision() {

        for (int i = 0; i < bulletList.size(); i++) {

            // позиция и размеры снаряда
            float bulletX = bulletList.get(i).GetPosX();
            float bulletY = bulletList.get(i).GetPosY();

            for (int j = 0; j < enemyList.size(); j++) {
                // позиция и размеры пришельца
                float enemyX = enemyList.get(j).GetPosX();
                float enemyY = enemyList.get(j).GetPosY();

                // проверка столкновения снаряда и пришельца и их удаление в случае подтверждения
                if ( bulletX + Bullet.width >= enemyX
                        && bulletX <= enemyX + Enemy.width
                        && bulletY >= enemyY
                        && bulletY - Bullet.height <= enemyY + Enemy.height ) {
                    enemyList.get(j).Death(score);
                    enemyList.remove(j);
                    bulletList.remove(i);
                    //Audio.Death();
                }
            }
        }
    }


    public void run() {

        int fpsSet = 60;
        int upsSet = 200;
        double timePerFrame = 1000000000.0 / fpsSet;
        double timePerUpdate = 1000000000.0 / upsSet;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaUpd = 0;
        double deltaFps = 0;

        while (true) {

            long currentTime = System.nanoTime();

            deltaUpd += (currentTime - previousTime) / timePerUpdate;
            deltaFps += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaUpd >= 1) {
                try {
                    update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                updates++;
                deltaUpd--;
            }

            if (deltaFps >= 1) {

                    if (gamePanel.pauseFlag) {
                        menu.MainMenu();
                    }
                    else{
                        gamePanel.buttonsList.clear(); // очистка списка кнопок
                        gamePanel.repaint();
                    }



                frames++;
                deltaFps--;
            }



            if (System.currentTimeMillis() - lastCheck >= 1000) {

                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | " + "Updates: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void WindowFocusLost() {
        player.ResetDirBooleans();
    }

    public Player GetPlayer() {
        return player;
    }

    public Score GetScore() {
        return score;
    }
}
