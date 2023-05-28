package main;

import GUI.Score;
import inputs.KeyboardInputs;
import buttons.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

// Класс контейнера элементов
public class GamePanel extends JPanel {

    public static BufferedImage backgroundSprite;

    // Для движения врагов

    public boolean pauseFlag = true; // флаг установки на паузу

    public boolean flag = false;

    public List<Buttons> buttonsList = new ArrayList<>(); // массив кнопок

    private final Score score = new Score();

    private Game game;

    //private final Background background = new Background();

    public void Pause() { // установка флага паузы
        if (!pauseFlag) {
            pauseFlag = true;
        }
        else{
            pauseFlag = false;
        }
    }


    // Конструктор класса
    public GamePanel(Game game) {
        this.game = game;
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
        game.render(graphics);
    }

    public void PaintBackground(Graphics graphics) {

        graphics.drawImage(backgroundSprite, 0, 0, GameWindow.size.width, GameWindow.size.height, null);
    }

    public Game GetGame() {
        return game;
    }
}
