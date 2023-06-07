package buttons;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BackButton extends Buttons {
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
            gamePanel.paintComponent(gamePanel.getGraphics());
            gamePanel.buttonsList.clear();
            gamePanel.menu.MainMenu();

        }
    }

    public BackButton(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);

    }
}
