package buttons;

import main.GamePanel;

import java.awt.*;
import java.io.IOException;

public abstract class Buttons {
    public final GamePanel gamePanel;
    public Font font = new Font("Courier", Font.BOLD, 40); // объявление шрифта
    public int xPosition; // позиция кнопки на экране
    public int yPosition;
    public int width = 20; // размеры кнопки
    public int height = 50;


    public abstract void RenderButton(Graphics graphics);

    public abstract void onHit(int x, int y) throws IOException;
    public Buttons(int xPosition, int yPosition, GamePanel gamePanel) {

        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.gamePanel = gamePanel;
    }


}

