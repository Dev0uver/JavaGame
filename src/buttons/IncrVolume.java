package buttons;

import audio.Audio;
import main.Game;
import main.GamePanel;

import java.awt.*;
import java.io.IOException;

public class IncrVolume extends Buttons {


    int width = 50;
    @Override
    public void RenderButton(Graphics graphics) {
        graphics.setColor(Color.lightGray);
        graphics.fillRect( xPosition, yPosition, width, height);
        graphics.setColor(Color.BLACK);
        graphics.setFont(font); // установка шрифта
        graphics.drawString("+", xPosition + 300, yPosition + 300);
    }

    @Override
    public void onHit(int x, int y) throws IOException {
        gamePanel.GetGame().IncrMusicValue();
    }
    public IncrVolume(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);
    }
}
