package buttons;

import main.GamePanel;
import main.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class IncreaseVolume extends Buttons {

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

            GameWindow.pressed = true;
            gamePanel.GetGame().IncreaseMusicVolume();
            gamePanel.paintComponent(gamePanel.getGraphics());
            gamePanel.menu.Settings();
        }
    }
    public IncreaseVolume(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
    }
}
