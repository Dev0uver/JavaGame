package buttons;


import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PlayAgain extends Buttons{
    Image img = new ImageIcon("src/Assets/Sprites/Retry.png").getImage();

    public int width = 400;
    @Override
    public void RenderButton(Graphics graphics) {
        //graphics.setColor(Color.lightGray);
        //graphics.fillRect( xPosition , yPosition, width, height);
        graphics.drawImage(img, xPosition, yPosition, null);
        //graphics.setColor(Color.BLACK);
        //graphics.setFont(font); // установка шрифта
        //graphics.drawString("Play again", xPosition + 90, yPosition + 40); // рисование надписи в кнопке
    }

    @Override
    public void onHit(int x, int y) throws IOException {
        if (((xPosition <=x) & (x <=xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))){
            gamePanel.pauseFlag = false; // снятие флага паузы

        }
    }

    public PlayAgain(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
        this.xPosition = xPosition - width / 2;
        this.yPosition = yPosition;
    }
}
