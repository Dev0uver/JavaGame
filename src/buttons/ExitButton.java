package buttons;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ExitButton extends Buttons {

    public static BufferedImage sprite;

    public static int width;
    public static int height;

    public void onHit(int x, int y) throws IOException {

        if (((xPosition <= x) & (x <= xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))) {
            gamePanel.GetGame().GetScore().SaveHighScore();
            System.exit(0);
        }
    }

    public void RenderButton(Graphics graphics) { // отрисовка кнопки

        graphics.drawImage(sprite, xPosition, yPosition, null);
    }
    public ExitButton(int xPosition, int yPosition, GamePanel gamePanel) {

        super(xPosition, yPosition, gamePanel);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
