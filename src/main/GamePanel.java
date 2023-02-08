package main;

import inputs.KeyboardInputs;

import java.awt.Color;
import java.awt.Graphics;


import javax.swing.*;

// Класс контейнера элементов
public class GamePanel extends JPanel {

    private int xDelta;

    // Переменные для теста движения (!Убрать позже)
    private float moveX = 0;
    private float dirX = 5f;
    private float moveY = 0;
    private float dirY = 5f;

    // начальные координаты игрока
    private float rectX = (float) ((GameWindow.width / 2) - 50);
    private float rectY = (float) (GameWindow.height - 150);

    // скорость перемещения
    private float velX;

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

        // graphics.drawRect(500, 500, 100, 100);

        // Квадрантик
        graphics.fillRect( (int) rectX, (int) rectY, 100, 100);
        MovePlayer();

        graphics.setColor(Color.green);
        // Движущийся сам по себе квадрантик (!Убрать позже)
        graphics.fillRect( (int) moveX, (int) moveY, 50, 50);
        MoveRect();
        /*
        graphics.setColor(Color.orange);
        graphics.drawLine(1280, 360, 0, 360);
        graphics.drawLine(640, 0, 640, 720);
        */
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
}
