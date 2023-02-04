package main;

// Класс игры
public class Game {

    private GameWindow gameWindow;
    private GamePanel gamePanel;

    public Game() {
        gamePanel = new GamePanel(); // Инициализация Контейнера
        gameWindow = new GameWindow(gamePanel); // Инициализация объекта окна
        gamePanel.setFocusable(true); // Позволяет "захватить" экран
        gamePanel.requestFocus(); // Запрашивает захват экрана для ввода
    }
}
