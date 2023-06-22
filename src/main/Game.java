package main;

import GUI.Score;
import audio.Audio;
import entities.Bullet;
import entities.Enemy;
import entities.EnemyBullet;
import entities.Player;

import java.awt.*;
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
    private GameSettings gameSettings;

    private Player player;

    private long shotCheck;

    private Score score;

    private final List<Enemy> enemyList = new ArrayList<>();
    private final List<Bullet> bulletList = new ArrayList<>();
    private final List<EnemyBullet> enemyBulletList = new ArrayList<>();

    private final Audio audio;

    private GameState state = GameState.MENU;
    private GameState prevState = null;

    public Game() {

        Initializer.Initialization();
        gamePanel = new GamePanel(this); // Инициализация Контейнера
        gamePanel.setFocusable(true); // Позволяет "захватить" экран
        gamePanel.requestFocus(); // Запрашивает захват экрана для ввода
        GameWindow gameWindow = new GameWindow(gamePanel);

        timerBuffer = new TimerBuffer();
        gameTimer = new GameTimer(timerBuffer);
        gameSettings = new GameSettings();
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
            player.UpdatePos(gameSettings.GetPlayerSpeed());

            for (EnemyBullet bullet : enemyBulletList) {
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
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.Render(graphics);
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

            RenderHP(graphics);
            timerBuffer.Render(graphics);
    }

    private void RenderLives(Graphics graphics) {

        for (int i = 0; i < gameSettings.GetPlayerLives(); i++) {
            graphics.drawImage(Player.playerSprite, (int) (i * Player.playerWidth * 1.2), 0, null);
        }
    }

    private void RenderHP(Graphics graphics) {

        Font font = new Font("Courier", Font.BOLD, 30);
        graphics.drawString("HP: " + gameSettings.GetHP(), (int) (3 * Player.playerWidth * 1.2), font.getSize());
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

        if (System.currentTimeMillis() - shotCheck >= 1000 && enemyBulletList.size() < 3) {

            Random random = new Random();
            Enemy bullet = enemyList.get(random.nextInt(enemyList.size()));
            //Enemy bul = enemyList.get(0);
            CreateEnemyBullets(bullet.GetPosX(), bullet.GetPosY());
            shotCheck = System.currentTimeMillis();
        }
    }

    private void CreateBullet() {

        Bullet bullet = new Bullet(player.GetPosX(), player.GetPosY());
        bulletList.add(bullet);
        audio.Shot();
    }

    private void CreateEnemyBullets(float x, float y) {

        EnemyBullet enemyBullet = new EnemyBullet(x, y);
        enemyBulletList.add(enemyBullet);
        audio.Shot();
    }

    public void Reset() {

        enemyList.clear();
        bulletList.clear();
        gameSettings = new GameSettings();
        InitClasses();
        timerBuffer = new TimerBuffer();
    }
    private void LiveLoss() {

        enemyList.clear();
        bulletList.clear();
        enemyBulletList.clear();
        gameSettings.SetPlayerLives(gameSettings.GetPlayerLives() - 1);
        gameSettings.ResetVariables();
        player = new Player((float) ((GameWindow.size.getWidth() / 2) - Player.playerSprite.getWidth() / 2), (float) (GameWindow.size.getHeight() * 0.9));
        InitFleet();
    }
    private void NextWave() {

        bulletList.clear();
        enemyBulletList.clear();
        gameSettings.ResetVariables();
        InitFleet();
        score.wave++;
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
                    if (gameSettings.GetPlayerLives() == 0) {
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
            NextWave();
        }

        // Перемещение вниз при достижении края
        if (down) {
            for (Enemy enemy : enemyList) {
                enemy.JumpDown();
            }
        }
        // Движение вправо или влево
        for (Enemy enemy : enemyList) {
            enemy.Move(gameSettings.GetEnemySpeed());
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

                if (enemyBulletList.get(i).GetPosY() >= EnemyBullet.height + GameWindow.size.height) {

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
                if ( bulletX + Bullet.width >= enemyX + gameSettings.GetEnemySpeed()
                        && bulletX <= enemyX + Enemy.width - gameSettings.GetEnemySpeed()
                        && bulletY >= enemyY
                        && bulletY - Bullet.height <= enemyY + Enemy.height ) {
                    score.score += score.wave;
                    enemyList.remove(j);
                    bulletList.remove(i);
                    gameSettings.SpeedUp();
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
            if ( bulletX + EnemyBullet.width >= PlayerX + gameSettings.GetEnemySpeed()
                    && bulletX <= PlayerX + Player.playerWidth
                    && bulletY >= PlayerY
                    && bulletY - EnemyBullet.height <= PlayerY + Player.playerHeight ) {
                enemyBulletList.remove(i);
                gameSettings.SpeedUp();
                audio.Death();
                gameSettings.SetHP(gameSettings.GetHP() - 1);
                if (gameSettings.GetHP() == 0) {
                    if (gameSettings.GetPlayerLives() == 0) {
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
    public GameSettings GetGameSettings() {
        return gameSettings;
    }
}
