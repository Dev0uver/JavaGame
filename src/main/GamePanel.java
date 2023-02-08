package main;

import inputs.KeyboardInputs;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;

// Класс контейнера элементов
public class GamePanel extends JPanel {
    // Переменные для теста движения (!Убрать позже)
    private final int rectSize = 100;
    private float moveX = 0;
    private float moveY = 0;
    private float dirX = 2f;
    private float dirY = 2f;

    // начальные координаты игрока
    private float rectX = (float) ((GameWindow.width / 2) - rectSize / 2); //  ((GameWindow.width / 2) - 50) + (100 / 2)
    private final float rectY = (float) (GameWindow.height - 150);

    // скорость перемещения
    private float velX;

    // Объекты
    private final List<Bullet> bulletList = new ArrayList<Bullet>();
//    private Bullet[] bullets = new Bullet();


    public void AddBullet () {
        Bullet bullet = new Bullet();

        bullet.x = (rectX + (rectSize / 2)) - (bullet.bulletWidth / 2);
        bullet.y = rectY;

        bulletList.add(bullet);
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
        graphics.setColor(Color.green);
        graphics.fillRect( (int) moveX, (int) moveY, 50, 50);
        MoveRect();
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
}
