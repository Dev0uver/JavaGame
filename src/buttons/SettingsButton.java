package buttons;

import main.GamePanel;
import main.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

public class SettingsButton extends Buttons{

    public static BufferedImage sprite;

    public static int width;
    public static int height;

    @Override
    public void RenderButton(Graphics graphics) {
        graphics.drawImage(sprite, xPosition, yPosition, null);
    }

    @Override
    public void onHit(int x, int y) throws IOException {
        if (((xPosition <= x) & (x <= xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))) {

            if (gamePanel.pauseFlag) {
                gamePanel.previousFlag = 0;
                gamePanel.pauseFlag = false;
            }
            else {
                gamePanel.previousFlag = 1;
                gamePanel.retryFlag = false;
            }

            gamePanel.settingsFlag = true;
            GameWindow.pressed = true;
            gamePanel.paintComponent(gamePanel.getGraphics());
            gamePanel.menu.Settings();
        }

    }

    public SettingsButton(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
