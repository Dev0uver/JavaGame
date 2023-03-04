package main;

import inputs.KeyboardInputs;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;

// Класс контейнера элементов
public class GamePanel extends JPanel {
    // Для движения врагов
    //boolean down = false;
    boolean flag = true;
    // Для управления врагами !
    float enemySpeed = 2f;
    int jumpDown = 30;
    int removeLine = 400;
    // Количество врагов на поле
    int count = 30;
    // Количество врагов в 1 строке
    int row = 10;

    // Для управления героем
    int leftMark = 0;
    int rightMark = GameWindow.width;

    int playerWidth = 80;
    int playerHeight = 100;


    // начальные координаты игрока
    private float rectX = (float) ((GameWindow.width / 2) - playerWidth / 2);
    private final float rectY = (float) (GameWindow.height - 150);

    // скорость перемещения
    private float velX;

    // Объекты
    private final List<Bullet> bulletList = new ArrayList<>();
    private final List<Enemy> enemyList = new ArrayList<>();


    public void AddBullet () {
        Bullet bullet = new Bullet();

        bullet.x = (rectX + playerWidth / 2) - (bullet.width / 2);
        bullet.y = rectY;

        bulletList.add(bullet);
    }

    public void AddEnemy (int count, int row) {
        int sector = 120;

        for (int i = 0; i < count; i++)
        {
            int numOfSector = (i + 1) % row;
            Enemy enemy = new Enemy();

            enemy.xPosition = (sector * numOfSector);
            enemy.yPosition =  (i / row) * (enemy.height + 10);

            enemyList.add(enemy);
        }
    }


    // set для velX
    public void setVelX(float velX) {

        this.velX = velX;

    }

    // Конструктор класса
    public GamePanel() {

        addKeyListener(new KeyboardInputs(this));

    }

    // Изменение координаты x игрока
    public void MovePlayer() {
        if (leftMark <= rectX + velX && rectX + velX <= rightMark - playerWidth)
            rectX += velX;
    }

    // Объект и метод для рисования (!Заменить на спрайты)
    public void paintComponent(Graphics graphics) {

        // Метод очистки окна и отрисовки новых объектов
        super.paintComponent(graphics);

        if (enemyList.size() == 0) {
            AddEnemy(count, row);
        } else {
            for (Enemy enemy : enemyList) {
                graphics.fillRect((int) enemy.xPosition, (int) enemy.yPosition, enemy.width, enemy.height);
            }
            MoveEnemy();
        }


        graphics.setColor(Color.red);
        if (bulletList != null){
            synchronized (bulletList) {
                for (Bullet bullet : bulletList){
                    // Отрисовка
                    graphics.fillOval((int) bullet.x, (int) bullet.y - 30, (int) bullet.width, (int)bullet.height);
                }
                MoveBullet();
                CheckCollision();
            }

        }

        // Герой
        graphics.setColor(Color.black);
        graphics.fillRect( (int) rectX, (int) rectY, playerWidth, playerHeight);
        MovePlayer();
    }
    private void MoveBullet() {
        synchronized (bulletList) {
            for (int i = 0; i < bulletList.size(); i++){
                bulletList.get(i).y -= 8f;
                if (bulletList.get(i).y <= 0 - bulletList.get(i).height){
                    bulletList.remove(bulletList.get(i));
                }
            }
        }
    }


    // Коллизия
    private void CheckCollision() {
        if (bulletList != null && enemyList != null) {
            for (int i = 0; i < enemyList.size(); i ++) {

                // позиция и размеры пришельца
                float enemyX = enemyList.get(i).xPosition;
                float enemyY = enemyList.get(i).yPosition;
                int enemyWidth = enemyList.get(i).width;
                int enemyHeight = enemyList.get(i).height;

                for (int j = 0; j < bulletList.size(); j++) {

                    // позиция и размеры снаряда
                    float bulletX = bulletList.get(j).x;
                    float bulletY = bulletList.get(j).y;
                    float bulletWidth = bulletList.get(j).width;
                    float bulletHeight = bulletList.get(j).height;

                    // проверка столкновения снаряда и пришельца и их удаление в случае подтверждения
                    if ( bulletX + bulletWidth >= enemyX
                            && bulletX <= enemyX + enemyWidth
                            && bulletY >= enemyY
                            && bulletY - bulletHeight <= enemyY + enemyHeight ) {
                        enemyList.remove(i);
                        bulletList.remove(j);
                    }
                }
            }
        }
    }


    private void MoveEnemy() {
        // !! Управление врагами происходит из переменных описанных выше !!
        boolean down = false;
        for (int i = 0; i < enemyList.size(); i++){
            // Проверка достижения правого края
            if (enemyList.get(i).xPosition + enemyList.get(i).width >= rightMark) {
                // Означает что надо двигать впараво
                flag = false;
                // Означает что надо двигать вниз
                down = true;
            }
            // Проверка достижения левого края
            if (enemyList.get(i).xPosition <= leftMark) {
                flag = true;
                down = true;
            }
            // Проверка не вышли ли враги за нижнюю границу
            if (enemyList.get(i).yPosition >= removeLine) {
                enemyList.remove(enemyList.get(i));
            }
        }
        // Перемещение вниз при достижении края
        if (down) {
            for (Enemy enemy : enemyList) {
                enemy.yPosition += jumpDown;
            }
        }
        // Движение вправо или влево
        for (Enemy enemy : enemyList) {
            if (flag) {
                enemy.xPosition += enemySpeed;
            } else {
                enemy.xPosition -= enemySpeed;
            }
        }
    }
}
