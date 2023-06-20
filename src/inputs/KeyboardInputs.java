package inputs;

import main.GamePanel;
import main.GameState;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

// Класс обрабочика нажатий
public class KeyboardInputs implements KeyListener {
    private final GamePanel gamePanel; // Экземпляр контейнера для вызова методов

    public KeyboardInputs(GamePanel gamePanel) {

        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_A -> gamePanel.GetGame().GetPlayer().SetLeft(true);
                case KeyEvent.VK_D -> gamePanel.GetGame().GetPlayer().SetRight(true);
                case KeyEvent.VK_SPACE -> gamePanel.GetGame().GetPlayer().SetShooting(true);
                case KeyEvent.VK_ESCAPE -> {
                    gamePanel.GetGame().GetGameTimer().Stop();
                    gamePanel.GetGame().SetState(GameState.MENU);
                }
                case KeyEvent.VK_Q -> {
                    System.exit(0);
                    try {
                        gamePanel.GetGame().GetScore().SaveHighScore();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> gamePanel.GetGame().GetPlayer().SetLeft(false);
            case KeyEvent.VK_D -> gamePanel.GetGame().GetPlayer().SetRight(false);
            case KeyEvent.VK_SPACE -> gamePanel.GetGame().GetPlayer().SetShooting(false);
        }
    }

}
