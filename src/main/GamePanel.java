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

    // Конструктор класса
    public GamePanel() {
        addKeyListener(new KeyboardInputs(this));
    }

    // Изменение координаты x
    public void ChangeXDelta(int value) {
        this.xDelta += value;
        repaint();
    }

    // Объект и метод для рисования (!Заменить на спрайты)
    public void paintComponent(Graphics graphics) {
        // Метод очистки окна и отрисовки новых объектов
        super.paintComponent(graphics);

        // graphics.drawRect(500, 500, 100, 100);

        // Квадрантик
        graphics.fillRect(590 + xDelta, 310, 100, 100);

        /*
        graphics.setColor(Color.orange);
        graphics.drawLine(1280, 360, 0, 360);
        graphics.drawLine(640, 0, 640, 720);
        */
    }
}
