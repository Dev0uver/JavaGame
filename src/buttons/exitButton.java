package buttons;

import main.GamePanel;

import java.awt.*;

public class exitButton extends buttons {

    public void onHit(int x, int y){
        if (((xPosition <=x) & (x <=xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))){
            System.exit(0);
        }
    }


    public void paintButton(Graphics g){ // отрисовка кнопки
        g.setColor(Color.lightGray);
        g.fillRect( xPosition, yPosition, width, height);
        g.setColor(Color.BLACK);
        g.setFont(font); // установка шрифта
        g.drawString("Exit", xPosition + 100, yPosition + 40); // рисование надписи в кнопке

    }
    public exitButton(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
    }
}
