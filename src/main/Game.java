package main;

import GUI.Score;
import audio.Audio;
import entities.Bullet;
import entities.Enemy;
import entities.EnemysBullet;
import entities.Player;

import java.awt.*;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Класс игры
public class Game implements Runnable {

    private final GamePanel gamePanel;
    private final GameTimer gameTimer;

    private TimerBuffer timerBuffer;

    private Player player;

    public long lastCheckk;

    private int playerLives = 3;
    int hit = 0;
    private Score score;

    private final List<Enemy> enemyList = new ArrayList<>();
    private final List<Bullet> bulletList = new ArrayList<>();
    private final List<EnemysBullet> enemyBulletList = new ArrayList<EnemysBullet>();

    private final Audio audio;

    private float enemySpeed = 1f;
    private float playerSpeed = 3f;

    private GameState state = GameState.MENU;
    private GameState prevState = null;

    public Game() throws InterruptedException {

        Initializer.Initialization();
        gamePanel = new GamePanel(this); // Инициализация Контейнера
        gamePanel.setFocusable(true); // Позволяет "захватить" экран
        gamePanel.requestFocus(); // Запрашивает захват экрана для ввода
        GameWindow gameWindow = new GameWindow(gamePanel);

        timerBuffer = new TimerBuffer();
        gameTimer = new GameTimer(timerBuffer);
        //menu  = new Menu(gamePanel);
        audio = new Audio();
        //Initializer.Initialization();

        gamePanel.RenderBackground(gamePanel.getGraphics()); // отрисовка фона до меню
        //gamePanel.menu.InitButtons();
        gamePanel.menu.MainMenu();
        InitClasses();

        audio.Soundtrack();

        // запуск потока игры
        StartGameLoop();
        //Thread.sleep(Long.MAX_VALUE);

    }

    public int GetVolumePer() {

        return audio.musicVolumePer;
    }

    public void IncreaseMusicVolume() {

        if(!(audio.musicVolumePer >= 100)){
            audio.musicVolumePer += 1;
            audio.SetMusicVolume();
        }
    }

    public void ReduceMusicValue() {

        if(!(audio.musicVolumePer <= 0)){
            audio.musicVolumePer -= 1;
            audio.SetMusicVolume();
        }
    }


    private void StartGameLoop() {

        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    private void Update() throws IOException {

        if (state == GameState.PLAYING) {
            player.UpdatePos(playerSpeed);

            for (EnemysBullet bullet : enemyBulletList) {
                bullet.MoveEnemyBullet();
            }
            if (player.shooting) {
                Shot();
            }

            MoveEnemy();

            for (Bullet bullet : bulletList) {
                bullet.MoveBullet();
            }




            CheckEnemyCollision();
            CheckPlayerCollision();
            CheckTopReach();
            CheckDownReach();
        }
    }

    public void Render(Graphics graphics) {

            if (player != null) {
                player.Render(graphics);
                RenderLives(graphics);
            }
            for (EnemysBullet enemysBullet : enemyBulletList) {
                enemysBullet.Render(graphics);
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
                score.RenderWave(graphics); // счетчик волн
            }
            graphics.drawString(timerBuffer.GetFullTime(), (int) GameWindow.size.getWidth() - 400, 70);
    }

    private void RenderLives(Graphics graphics) {

        for (int i = 0; i < playerLives; i++) {
            graphics.drawImage(Player.playerSprite, i * 100, 0, null);
        }
    }

    private void InitClasses() {

        player = new Player((float) ((GameWindow.size.getWidth() / 2) - Player.playerSprite.getWidth() / 2), (float) (GameWindow.size.getHeight() - 200));
        score = new Score();

        try {
            score.InitScore();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        InitFleet();
    }

    private void InitFleet() {

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
        enemy.SetPosY(Enemy.enemySprite.getHeight() + 2 * Enemy.enemySprite.getHeight() * rowNumber + Player.playerWidth / 2);
        return enemy;
    }

    private void Shot() {

        if (System.currentTimeMillis() - player.lastCheck >= 300 && bulletList.size() < 2) {
            CreateBullet();
            player.lastCheck = System.currentTimeMillis();
        }
    }

    private void enemyShot() {

        if (System.currentTimeMillis() - lastCheckk >= 1000 && enemyBulletList.size() < 3) {

            Random random = new Random();
            Enemy bul = enemyList.get(random.nextInt(enemyList.size()));
            //Enemy bul = enemyList.get(0);
            CreateEnemysBullet(bul.GetPosX(), bul.GetPosY());
            lastCheckk = System.currentTimeMillis();
        }
    }

    public void CreateBullet() {

        Bullet bullet = new Bullet(player.GetPosX(), player.GetPosY());
        bulletList.add(bullet);
        audio.Shot();
    }

    public void CreateEnemysBullet(float x, float y) {

        EnemysBullet enemysBullet = new EnemysBullet(x, y);
        enemyBulletList.add(enemysBullet);
        audio.Shot();
    }

    public void Reset() {

        enemyList.clear();
        bulletList.clear();
        InitClasses();
        playerLives = 3;
        enemySpeed = 1f;
        timerBuffer = new TimerBuffer();
    }

    public void LiveLoss() {

        enemyList.clear();
        bulletList.clear();
        enemyBulletList.clear();
        InitFleet();
        player = new Player((float) ((GameWindow.size.getWidth() / 2) - Player.playerSprite.getWidth() / 2), (float) (GameWindow.size.getHeight() - 200));
        playerLives--;
        enemySpeed = 1f;
    }

    private void MoveEnemy() throws IOException {
        enemyShot();
        boolean down = false;
        if(enemyList.size() != 0) { // проверка, закончились ли враги
            for (int i = 0, enemyListSize = enemyList.size(); i < enemyListSize; i++) {
                Enemy enemy = enemyList.get(i);
                // Проверка достижения правого края
                if (enemy.GetPosX() + Enemy.width >= GameWindow.size.getWidth()) {

                    // Означает что надо двигать впараво
                    Enemy.direction = false;
                    // Означает что надо двигать вниз
                    down = true;
                }
                // Проверка достижения левого края
                if (enemy.GetPosX() <= 0) {

                    Enemy.direction = true;
                    down = true;
                }
                // Проверка не вышли ли враги за нижнюю границу
                if (enemy.GetPosY() >= player.GetPosY() - Player.playerSprite.getHeight()) {
                    if (playerLives == 0) {
                        score.SaveHighScore();
                        state = GameState.GAMEOVER;
                    }
                    else {
                        LiveLoss();
                    }
                }
            }
        }
        else
        {
            bulletList.clear();
            enemySpeed = 1f;
            InitFleet();
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
            enemy.Move(enemySpeed);
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

    private void CheckDownReach() {

        synchronized (enemyBulletList) {

            for (int i = 0; i < enemyBulletList.size(); i++) {

                if (enemyBulletList.get(i).GetPosY() >= EnemysBullet.height + GameWindow.size.height) {

                    enemyBulletList.remove(i);
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
                if ( bulletX + Bullet.width >= enemyX + enemySpeed
                        && bulletX <= enemyX + Enemy.width - enemySpeed
                        && bulletY >= enemyY
                        && bulletY - Bullet.height <= enemyY + Enemy.height ) {
                    score.score++;
                    enemyList.remove(j);
                    bulletList.remove(i);
                    enemySpeed += enemySpeed * 0.02f;
                    playerSpeed += playerSpeed * 0.01f;
                    audio.Death();
                }
            }
        }
    }

    private void CheckPlayerCollision () throws IOException {



        for (int i = 0; i < enemyBulletList.size(); i++) {

            // позиция и размеры снаряда
            float bulletX = enemyBulletList.get(i).GetPosX();
            float bulletY = enemyBulletList.get(i).GetPosY();


                // позиция и размеры пришельца
                float PlayerX = player.GetPosX();
                float PlayerY = player.GetPosY();

                // проверка столкновения снаряда и пришельца и их удаление в случае подтверждения
                if ( bulletX + EnemysBullet.width >= PlayerX + enemySpeed
                        && bulletX <= PlayerX + Player.playerWidth
                        && bulletY >= PlayerY
                        && bulletY - EnemysBullet.height <= PlayerY + Player.playerHeight ) {

                    enemyBulletList.remove(i);
                    enemySpeed += enemySpeed * 0.02f;
                    playerSpeed += playerSpeed * 0.01f;
                    audio.Death();
                    hit++;
                    System.out.println(hit);
                    if(hit % 3 == 0) {

                        if (playerLives == 0) {
                            score.SaveHighScore();
                            state = GameState.GAMEOVER;
                        }
                        else {
                            LiveLoss();
                        }
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
                    Update();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                updates++;
                deltaUpd--;
            }

            if (deltaFps >= 1) {

                switch (state) {

                    case MENU -> {
                        gamePanel.paintComponent(gamePanel.getGraphics());
                        gamePanel.menu.MainMenu();
                    }
                    case SETTINGS -> {
                        gamePanel.paintComponent(gamePanel.getGraphics());
                        gamePanel.menu.Settings();
                    }
                    case GAMEOVER -> {
                        gamePanel.paintComponent(gamePanel.getGraphics());
                        gamePanel.menu.GameOver();
                    }
                    default -> gamePanel.repaint();
                }

                frames++;
                deltaFps--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {

                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS: " + frames + " | " + "Updates: " + updates);
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
    public void SetState(GameState state) {

        this.state = state;
    }
    public GameState GetState() {

        return state;
    }
    public void SetPrevState(GameState state) {

        this.prevState = state;
    }
    public GameState GetPrevState() {

        return prevState;
    }
    public GameTimer GetGameTimer() {

        return gameTimer;
    }
}
