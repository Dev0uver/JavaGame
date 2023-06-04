package buttons;


import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class RetryButton extends Buttons{

    public static BufferedImage sprite;

    public static int width;
    public static int height;
    @Override
    public void RenderButton(Graphics graphics) {
        //graphics.setColor(Color.lightGray);
        //graphics.fillRect( xPosition , yPosition, width, height);
        graphics.drawImage(sprite, xPosition, yPosition, null);
        //graphics.setColor(Color.BLACK);
        //graphics.setFont(font); // установка шрифта
        //graphics.drawString("Play again", xPosition + 90, yPosition + 40); // рисование надписи в кнопке
    }

    @Override
    public void onHit(int x, int y) throws IOException {
        if (((xPosition <= x) & (x <=xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))) {
            gamePanel.retryFlag = false; // снятие флага паузы
            gamePanel.GetGame().Reset();
        }
    }

    public RetryButton(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
