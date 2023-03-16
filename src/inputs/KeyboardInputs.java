package inputs;

import main.GamePanel;
import main.Menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

// Класс обрабочика нажатий
public class KeyboardInputs implements KeyListener {
    private final GamePanel gamePanel; // Экземпляр контейнера для вызова методов

    private long lastCheck;


    // все нажатые кнопки (в данный момент)
    public static final List<Integer> keyChain = new ArrayList<>();
    public KeyboardInputs(GamePanel gamePanel) {

        this.gamePanel = gamePanel;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int pressedKey = e.getKeyCode();

        if (pressedKey == KeyEvent.VK_Q) {
            System.exit(0);
        }

        if (!isPressed(pressedKey)) {
            keyChain.add(pressedKey);
        }

        if (isPressed(KeyEvent.VK_SPACE)) {
            if (System.currentTimeMillis() - lastCheck >= 300) {
                gamePanel.CreateBullet();
                lastCheck = System.currentTimeMillis();
            }
        }

        if (keyChain.contains(KeyEvent.VK_A)) {
            gamePanel.player.setVelX(-10f);
        }

        if (keyChain.contains(KeyEvent.VK_D)) {
            gamePanel.player.setVelX(10f);
        }
        if (pressedKey == KeyEvent.VK_D) {
           gamePanel.pause();
        }
    }

    // true, если кнопка с кодом keyCode нажата
    public static boolean isPressed(int keyCode) {
        return keyChain.contains(keyCode);
    }
    @Override
    public void keyReleased(KeyEvent e) {

        int releasedKey = e.getKeyCode();
        if (isPressed(releasedKey)) {
            if (releasedKey == KeyEvent.VK_A || releasedKey == KeyEvent.VK_D){
                gamePanel.player.setVelX(0);

            }

            keyChain.remove((Integer) releasedKey);
        }
    }

}
