package buttons;

import main.GamePanel;

import java.awt.*;

public abstract class buttons {
    public final GamePanel gamePanel;
    public Font font = new Font("Courier", Font.BOLD, 40); // объявление шрифта
    public int xPosition; // позиция кнопки на экране
    public int yPosition;
    public int width = 250; // размеры кнопки
    public int height = 50;

    public abstract void paintButton(Graphics g);

    public abstract void onHit(int x, int y);
    public buttons(int xPosition, int yPosition, GamePanel gamePanel){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.gamePanel = gamePanel;
    }


}

