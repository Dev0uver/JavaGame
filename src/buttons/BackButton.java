package buttons;

import main.GamePanel;
import main.GameWindow;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BackButton extends Buttons {
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

            gamePanel.GetGame().SetState(gamePanel.GetGame().GetPrevState());
            gamePanel.GetGame().SetPrevState(null);
            GameWindow.pressed = true;
            switch (gamePanel.GetGame().GetState()) {

                case MENU -> {
                    gamePanel.buttonsList.clear();
                    gamePanel.buttonsList.add(gamePanel.menu.play);
                    gamePanel.buttonsList.add(gamePanel.menu.settings);
                    gamePanel.buttonsList.add(gamePanel.menu.exit);
                    gamePanel.paintComponent(gamePanel.getGraphics());
                    gamePanel.menu.MainMenu();
                }
                case GAMEOVER -> {
                    gamePanel.buttonsList.clear();
                    gamePanel.buttonsList.add(gamePanel.menu.retry);
                    gamePanel.buttonsList.add(gamePanel.menu.settings);
                    gamePanel.buttonsList.add(gamePanel.menu.exit);
                    gamePanel.paintComponent(gamePanel.getGraphics());
                    gamePanel.menu.GameOver();
                }
            }
        }
    }

    public BackButton(int xPosition, int yPosition, GamePanel gamePanel) {

        super(xPosition, yPosition, gamePanel);

    }
}
