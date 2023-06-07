package main;

import inputs.KeyboardInputs;
import buttons.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.*;

// Класс контейнера элементов
public class GamePanel extends JPanel {

    public static BufferedImage backgroundSprite;

    public boolean pauseFlag = true; // флаг установки на паузу

    public boolean flag = false;

    public HashMap<String, Buttons> buttonsList = new HashMap<>(); // массив кнопок

    private final Game game;

    public final Menu menu = new Menu(this);
    public boolean retryFlag = false;


    public void Pause() { // установка флага паузы
        pauseFlag = !pauseFlag;
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


    // Объект и метод для рисования
    public void paintComponent(Graphics graphics) {
        // Метод очистки окна и отрисовки новых объектов
        super.paintComponent(graphics);
        PaintBackground(graphics);
        if (!pauseFlag) {
            game.Render(graphics);
        }

    }

    public void PaintBackground(Graphics graphics) {

        graphics.drawImage(backgroundSprite, 0, 0, GameWindow.size.width, GameWindow.size.height, null);
    }

    public Game GetGame() {
        return game;
    }
}
