package buttons;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayButton extends Buttons {

    public static BufferedImage sprite;

    public static int width;
    public static int height;

    @Override
    public void RenderButton(Graphics graphics) { // отрисовка кнопки
//        graphics.setColor(Color.lightGray);
//        graphics.fillRect( xPosition, yPosition, width, height);
//        graphics.setColor(Color.BLACK);
//        graphics.setFont(font); // установка шрифта
//        graphics.drawString("Play", xPosition + 80, yPosition + 40); // рисование надписи в кнопке
        graphics.drawImage(sprite, xPosition, yPosition, null);
    }

    @Override
    public void onHit(int x, int y) {
        if (((xPosition <=x) & (x <= xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))) {

            gamePanel.pauseFlag = false; // снятие флага паузы
            if (!gamePanel.flag) {
                gamePanel.flag = true;
            }
        }
    }

    public PlayButton(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
