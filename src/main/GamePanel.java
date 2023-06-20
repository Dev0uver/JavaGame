package main;

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
    public List<Buttons> buttonsList = new ArrayList<>(); // массив кнопок
    private final Game game;
    public final Menu menu = new Menu(this);


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
        RenderBackground(graphics);

        switch (game.GetState()) {
            case MENU -> menu.MainMenu();
            case GAMEOVER -> menu.GameOver();
            case SETTINGS -> menu.Settings();
            default -> game.Render(graphics);
        }
    }

    public void RenderBackground(Graphics graphics) {

        graphics.drawImage(backgroundSprite, 0, 0, GameWindow.size.width, GameWindow.size.height, null);
    }

    public Game GetGame() {
        return game;
    }
}
