package buttons;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class VolumeButton extends Buttons{

    public static BufferedImage sprite;

    public static int width;
    public static int height;
    @Override
    public void RenderButton(Graphics graphics) {
        graphics.drawImage(sprite, xPosition, yPosition, null);
    }

    @Override
    public void onHit(int x, int y) throws IOException {
        if (((xPosition <= x) & (x <= xPosition + width / 5)) & ((yPosition <= y) & (y <= yPosition + height))) {
            gamePanel.GetGame().ReduceMusicValue();
            gamePanel.menu.Settings();
        }
        else if(((xPosition + width * 0.78 <= x) & (x <= xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))){
            gamePanel.GetGame().IncreaseMusicVolume();
            gamePanel.menu.Settings();
        }
    }

    public VolumeButton(int xPosition, int yPosition, GamePanel gamePanel) {
        super(xPosition, yPosition, gamePanel);

    }
}
