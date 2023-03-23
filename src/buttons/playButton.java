package buttons;

import main.GamePanel;

import java.awt.*;

public class playButton extends buttons{

    @Override
    public void paintButton(Graphics g) { // отрисовка кнопки
        g.setColor(Color.lightGray);
        g.fillRect( xPosition, yPosition, width, height);
        g.setColor(Color.BLACK);
        g.setFont(font); // установка шрифта
        g.drawString("Play", xPosition + 100, yPosition + 40); // рисование надписи в кнопке
    }

    @Override
    public void onHit(int x, int y) {
        if (((xPosition <=x) & (x <=xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))){
            synchronized (gamePanel){
                gamePanel.notify(); // снятие потока с ожидания
            }
            gamePanel.pauseFlag = false; // снятие флага паузы

        }
    }

    public playButton(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
    }
}
