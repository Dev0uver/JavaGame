package main;

import inputs.KeyboardInputs;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;

// Класс контейнера элементов
public class GamePanel extends JPanel {
    public int width;
    public int height;
    boolean flag = true;
    // Переменные для теста движения (!Убрать позже)
    private final int rectSize = 100;
    private float moveX = 0;
    private float moveY = 0;
    private float dirX = 2f;
    private float dirY = 2f;

    // начальные координаты игрока
    private float rectX = (float) ((GameWindow.width / 2) - rectSize / 2);
    private final float rectY = (float) (GameWindow.height - 150);

    // скорость перемещения
    private float velX;

    // Объекты
    private final List<Bullet> bulletList = new ArrayList<Bullet>();
    private final List<Enemy> enemyList = new ArrayList<Enemy>();


    public void AddBullet () {
        Bullet bullet = new Bullet();

        bullet.x = (rectX + (rectSize / 2)) - (bullet.bulletWidth / 2);
        bullet.y = rectY;

        bulletList.add(bullet);
    }

    public void AddEnemy (int count, int row) {
        int sector = 120;

        for (int i = 0; i < count; i++)
        {
            int numOfSector = (i + 1) % row;
            Enemy enemy = new Enemy();

            enemy.x_position = (sector * numOfSector);
            enemy.y_position =  (i / row) * (enemy.height + 10);

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

        rectX += velX;

    }

    // Объект и метод для рисования (!Заменить на спрайты)
    public void paintComponent(Graphics graphics) {

        // Метод очистки окна и отрисовки новых объектов
        super.paintComponent(graphics);

        if (enemyList.size() == 0) {
            AddEnemy(13, 5);
        } else {
            for (Enemy e : enemyList) {
                graphics.fillRect((int) e.x_position, (int) e.y_position, (int) e.width, (int) e.height);
            }
            MoveEnemy();
        }



        if (bulletList != null){
            synchronized (bulletList) {
                for (Bullet b : bulletList){
                    // Отрисовка
                    graphics.setColor(Color.red);
                    graphics.fillOval((int) b.x, (int) b.y - 30, (int) b.bulletWidth, (int)b.bulletHeight);
                }
                MoveBullet();
            }

        }

        // Квадрантик
        graphics.setColor(Color.black);
        graphics.fillRect( (int) rectX, (int) rectY, rectSize, rectSize);
        MovePlayer();

        // Движущийся сам по себе квадрантик (!Убрать позже)
//        graphics.setColor(Color.green);
//        graphics.fillRect( (int) moveX, (int) moveY, 50, 50);
//        MoveRect();
    }


    // Метод проверки цикла и движения (!Убрать позже)
    private void MoveRect() {

        moveX += dirX;
        if (moveX > GameWindow.width || moveX < 0) {

            dirX *= -1;

        }

        moveY += dirY;
        if (moveY > GameWindow.height || moveY < 0) {

            dirY *= -1;

        }
    }

    private void MoveBullet() {
        synchronized (bulletList) {
            for (int i = 0; i < bulletList.size(); i++){
                bulletList.get(i).y -= 8f;
                if (bulletList.get(i).y <= 0 - bulletList.get(i).bulletHeight){
                    bulletList.remove(bulletList.get(i));
                }
            }
        }
    }

    private void MoveEnemy() {
        synchronized (enemyList) {
            boolean down = false;
            for (int i = 0; i < enemyList.size(); i++){

                if (enemyList.get(i).x_position >= 1267 - enemyList.get(i).width) {
                    flag = false;
                    down = true;
                }
                if (enemyList.get(i).x_position <= 0) {
                    flag = true;
                    down = true;
                }
                if (enemyList.get(i).y_position >= 400) {
                    enemyList.remove(enemyList.get(i));
                }
            }
            if (down) {
                for (Enemy enemy : enemyList) {
                    enemy.y_position += 20;
                }
            }
            for (Enemy enemy : enemyList) {
                if (flag) {
                    enemy.x_position += 3f;
                } else {
                    enemy.x_position -= 3f;
                }
            }
        }
    }
}
