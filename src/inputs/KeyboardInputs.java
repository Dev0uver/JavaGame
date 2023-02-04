package inputs;

import main.Game;
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
            case KeyEvent.VK_A -> gamePanel.ChangeXDelta(-5);
            // Смещение на 5 пикселей вправо
            case KeyEvent.VK_D -> gamePanel.ChangeXDelta(5);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
