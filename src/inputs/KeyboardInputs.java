package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Класс обрабочика нажатий
public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel; // Экземпляр контейнера для вызова методов

    public KeyboardInputs(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            // Смещение на 5 пикселей влево
            case KeyEvent.VK_A -> gamePanel.setVelX(-10f);
            // Смещение на 5 пикселей вправо
            case KeyEvent.VK_D -> gamePanel.setVelX(10f);

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            // Смещение на 5 пикселей влево
            case KeyEvent.VK_A -> gamePanel.setVelX(0);
            // Смещение на 5 пикселей вправо
            case KeyEvent.VK_D -> gamePanel.setVelX(0);

        }
    }
}
