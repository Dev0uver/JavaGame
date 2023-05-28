package buttons;

import buttons.Buttons;
import main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PlayButton extends Buttons {
    //int xPosition;
    //int yPosition;

    int width = 400;
    Image img = new ImageIcon("src/Assets/Sprites/Play.png").getImage();

    @Override
    public void RenderButton(Graphics graphics) { // отрисовка кнопки
        //graphics.setColor(Color.lightGray);
        //graphics.fillRect( xPosition, yPosition, width, height);
        graphics.drawImage(img, xPosition, yPosition, 400, 50, null);
        //graphics.setColor(Color.BLACK);
        //graphics.setFont(font); // установка шрифта
        //graphics.drawString("Play", xPosition + 80, yPosition + 40); // рисование надписи в кнопке
    }

    @Override
    public void onHit(int x, int y) {
        if (((xPosition<=x) & (x <=xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))){

            gamePanel.pauseFlag = false; // снятие флага паузы
            if (!gamePanel.flag){
                gamePanel.flag = true;
            }

        }
    }

    public PlayButton(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
        this.xPosition = xPosition - width / 2;
        this.yPosition = yPosition;
    }
}
