package main;

import inputs.KeyboardInputs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

// Класс контейнера элементов
public class GamePanel extends JPanel {

    private int xDelta;

    // Переменные для теста движения (!Убрать позже)
    private float moveX = 0;
    private float dirX = 1f;
    private float moveY = 0;
    private float dirY = 1f;

    // Конструктор класса
    public GamePanel() {

        addKeyListener(new KeyboardInputs(this));

    }

    // Изменение координаты x
    public void ChangeXDelta(int value) {

        this.xDelta += value;

    }

    // Объект и метод для рисования (!Заменить на спрайты)
    public void paintComponent(Graphics graphics) {

        // Метод очистки окна и отрисовки новых объектов
        super.paintComponent(graphics);

        // graphics.drawRect(500, 500, 100, 100);

        // Квадрантик
        graphics.fillRect(((GameWindow.width / 2) - 50) + xDelta, (GameWindow.height - 150), 100, 100);

        graphics.setColor(Color.green);
        MoveRect();
        // Движущийся сам по себе квадрантик (!Убрать позже)
        graphics.fillRect( (int) moveX, (int) moveY, 50, 50);


        graphics.setColor(Color.orange);
        graphics.drawLine(0, 0, GameWindow.width, GameWindow.height);
        //graphics.drawLine(640, 0, 640, 720);

    }


    // Метод проверки цикла и движения (!Убрать позже)
    private void MoveRect() {

        moveX += dirX;

        if (moveX + 50 >= GameWindow.width  || moveX < 0) {

            dirX *= -1;

        }

        moveY += dirY;
        if (moveY + 50 >= GameWindow.height || moveY < 0) {

            dirY *= -1;

        }
    }
}
