package buttons;


import main.GamePanel;
import main.GameState;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RetryButton extends Buttons{

    public static BufferedImage sprite;

    public static int width;
    public static int height;
    @Override
    public void RenderButton(Graphics graphics) {

        graphics.drawImage(sprite, xPosition, yPosition, null);
    }

    @Override
    public void onHit(int x, int y) throws IOException {

        if (((xPosition <= x) & (x <=xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))) {

            gamePanel.GetGame().SetState(GameState.PLAYING); // снятие флага паузы
            gamePanel.GetGame().Reset();
        }
    }

    public RetryButton(int xPosition, int yPosition, GamePanel gamePanel) {

        super(xPosition, yPosition, gamePanel);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
