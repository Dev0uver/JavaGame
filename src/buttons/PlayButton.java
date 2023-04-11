package buttons;

import main.GamePanel;

import java.awt.*;

public class PlayButton extends Buttons {

    @Override
    public void paintButton(Graphics graphics) { // отрисовка кнопки
        graphics.setColor(Color.lightGray);
        graphics.fillRect(xPosition - width / 2, yPosition, width, height);
        graphics.setColor(Color.BLACK);
        graphics.setFont(font); // установка шрифта
        graphics.drawString("Play", xPosition - 35, yPosition + 40); // рисование надписи в кнопке
    }

    @Override
    public void onHit(int x, int y) {
        if (((xPosition - width / 2 <= x) & (x <=xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))){
            synchronized (gamePanel) {
                gamePanel.notify(); // снятие потока с ожидания
            }
            gamePanel.pauseFlag = false; // снятие флага паузы

        }
    }

    public PlayButton(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
    }
}
