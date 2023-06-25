package buttons;

import main.GamePanel;
import main.GameState;
import main.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SettingsButton extends Buttons{

    public static BufferedImage sprite;

    public static int width;
    public static int height;

    @Override
    public void RenderButton(Graphics graphics) {

        graphics.drawImage(sprite, xPosition, yPosition, null);
    }

    @Override
    public void onHit(int x, int y) throws IOException {

        if (((xPosition <= x) & (x <= xPosition + width)) & ((yPosition <= y) & (y <= yPosition + height))) {

            gamePanel.GetGame().SetPrevState(gamePanel.GetGame().GetState());
            gamePanel.GetGame().SetState(GameState.SETTINGS);
            GameWindow.pressed = true;
            gamePanel.buttonsList.clear();
            gamePanel.buttonsList.add(gamePanel.menu.reduceVolume);
            gamePanel.buttonsList.add(gamePanel.menu.increaseVolume);
            gamePanel.buttonsList.add(gamePanel.menu.back);
            gamePanel.paintComponent(gamePanel.getGraphics());
            gamePanel.menu.Settings();
        }

    }

    public SettingsButton(int xPosition, int yPosition, GamePanel gamePanel) {

        super(xPosition, yPosition, gamePanel);
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }
}
