package buttons;

import buttons.Buttons;
import main.GamePanel;

import java.awt.*;

public class PlayButton extends Buttons {


    @Override
    public void RenderButton(Graphics graphics) { // отрисовка кнопки
        graphics.setColor(Color.lightGray);
        graphics.fillRect( xPosition, yPosition, width, height);
        graphics.setColor(Color.BLACK);
        graphics.setFont(font); // установка шрифта
        graphics.drawString("Play", xPosition + 80, yPosition + 40); // рисование надписи в кнопке
    }

    @Override
    public void onHit(int x, int y) {
        if (((xPosition <=x) & (x <=xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))){

            gamePanel.pauseFlag = false; // снятие флага паузы
            if (gamePanel.flag){
                gamePanel.flag = true;
            }

        }
    }

    public PlayButton(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
    }
}
