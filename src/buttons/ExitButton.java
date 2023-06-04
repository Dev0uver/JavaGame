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
        //graphics.setColor(Color.lightGray);
        //graphics.fillRect(xPosition, yPosition, width, height);
        graphics.drawImage(sprite, xPosition, yPosition, null);
        //graphics.setColor(Color.BLACK);
        //graphics.setFont(font); // установка шрифта
        //graphics.drawString("Exit", xPosition + 80, yPosition + 40); // рисование надписи в кнопке

    }
    public ExitButton(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
