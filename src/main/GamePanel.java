package main;

import GUI.Score;
import audio.Audio;
import entities.Bullet;
import entities.Enemy;
import entities.Player;
import inputs.KeyboardInputs;
import buttons.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import javax.imageio.ImageIO;
import javax.swing.*;

// Класс контейнера элементов
public class GamePanel extends JPanel {

    public Player player = new Player();

    private BufferedImage backgroundSprite;

    // Для движения врагов
    private boolean direction = true;

    public boolean pauseFlag = true; // флаг установки на паузу

    // Объекты
    private final List<Bullet> bulletList = new ArrayList<>();
    private final List<Enemy> enemyList = new ArrayList<>();

    public List<Buttons> buttonsList = new ArrayList<>(); // массив кнопок

    private final Audio audio = new Audio();

    private final Score score = new Score();

    //private final Background background = new Background();

    public void pause() { // установка флага паузы

        pauseFlag = true;
    }


    public void CreateBullet() {

        Bullet bullet = new Bullet(player.rectX, player.rectY, player.playerWidth);
        bulletList.add(bullet);
        //audio.shot();
    }

    public void AddEnemy (int count, int row) {

        int sector = 120;

        for (int number = 0; number < count; number++) {

            Enemy enemy = new Enemy(sector, number, row);
            enemyList.add(enemy);
        }
    }


    // Конструктор класса
    public GamePanel() {

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
    }

    private void setPanelSize() {

        setPreferredSize(GameWindow.size);
    }


    // Объект и метод для рисования (!Заменить на спрайты)
    public void paintComponent(Graphics graphics) {

        // Метод очистки окна и отрисовки новых объектов
        super.paintComponent(graphics);

        PaintBackground(graphics);

        if (enemyList.size() == 0) {
            try {
                Score.saveHighScore();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Количество врагов в 1 строке
            int row = 10;
            // Количество врагов на поле
            int count = 30;
            AddEnemy(count, row);
        }
        else {
            for (Enemy enemy : enemyList) {
                enemy.PaintEnemy(graphics);
            }
            try {
                MoveEnemy();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (bulletList != null) {

            synchronized (bulletList) {

                for (Bullet bullet : bulletList) {
                    // Отрисовка
                    bullet.PaintBullet(graphics);
                    bullet.MoveBullet();
                }
                CheckTopReach();
                CheckEnemyCollision();
            }
        }

        // Отрисовка и движение игрока
        player.PaintPlayer(graphics);
        player.MovePlayer();

        score.PaintScore(graphics);
        Score.PaintHighScore(graphics);
    }

    public void PaintBackground(Graphics graphics) {

        InputStream inputStream = getClass().getResourceAsStream("/Assets/Sprites/background.png");
        try {
            if (inputStream != null) {
                backgroundSprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        graphics.drawImage(backgroundSprite, 0, 0, 1920, 1080, null);
    }


    private void CheckTopReach() {

        synchronized (bulletList) {

            for (int i = 0; i < bulletList.size(); i++) {

                if (bulletList.get(i).y <= -bulletList.get(i).height) {

                    bulletList.remove(i);
                }
            }
        }
    }


    // Коллизия
    private void CheckEnemyCollision() {
        if (bulletList != null && enemyList != null) {
            for (int i = 0; i < bulletList.size(); i++) {

                // позиция и размеры снаряда
                float bulletX = bulletList.get(i).x;
                float bulletY = bulletList.get(i).y;
                int bulletWidth = bulletList.get(i).width;
                int bulletHeight = bulletList.get(i).height;

                for (int j = 0; j < enemyList.size(); j++) {
                    // позиция и размеры пришельца
                    float enemyX = enemyList.get(j).xPosition;
                    float enemyY = enemyList.get(j).yPosition;
                    int enemyWidth = enemyList.get(j).width;
                    int enemyHeight = enemyList.get(j).height;

                    // проверка столкновения снаряда и пришельца и их удаление в случае подтверждения
                    if ( bulletX + bulletWidth >= enemyX
                            && bulletX <= enemyX + enemyWidth
                            && bulletY >= enemyY
                            && bulletY - bulletHeight <= enemyY + enemyHeight ) {
                        enemyList.remove(j);
                        bulletList.remove(i);
                        //audio.kill();
                        Score.score++;
                    }
                }
            }
        }
    }

    private void MoveEnemy() throws IOException {
        // !! Управление врагами происходит из переменных описанных выше !!
        boolean down = false;
        for (int i = 0; i < enemyList.size(); i++){
            // Проверка достижения правого края
            if (enemyList.get(i).xPosition + enemyList.get(i).width >= GameWindow.size.getWidth()) {
                // Означает что надо двигать впараво
                direction = false;
                // Означает что надо двигать вниз
                down = true;
            }
            // Проверка достижения левого края
            if (enemyList.get(i).xPosition <= 0) {
                direction = true;
                down = true;
            }
            // Проверка не вышли ли враги за нижнюю границу
            if (enemyList.get(i).yPosition >= GameWindow.size.getHeight() - player.playerHeight - 120) {
                enemyList.remove(enemyList.get(i));
                Score.score = 0;
                Score.saveHighScore();
            }
        }
        // Перемещение вниз при достижении края
        if (down) {
            for (Enemy enemy : enemyList) {
                enemy.yPosition += enemy.jumpDown;
            }
        }
        // Движение вправо или влево
        for (Enemy enemy : enemyList) {
            if (direction) {
                enemy.xPosition += enemy.speed;
            }
            else {
                enemy.xPosition -= enemy.speed;
            }
        }
    }
}
