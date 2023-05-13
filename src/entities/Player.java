package entities;

import main.GameWindow;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player {

    public static int playerWidth;
    public static int playerHeight;

    public static BufferedImage playerSprite;

    // начальные координаты игрока

    public float rectX;
    public final float rectY;

    // скорость перемещения игрока
    private float velX;

    public void PaintPlayer(Graphics graphics) {

        graphics.drawImage(playerSprite, (int) rectX, (int) rectY, null);

    }

    // Изменение координаты x игрока
    public void MovePlayer() {

        if (0 <= rectX + velX && rectX + velX <= GameWindow.size.getWidth() - playerWidth) {

            rectX += velX;
        }

    }
    // set для velX
    public void setVelX(float velX) {

        this.velX = velX;
    }

    public Player() {

        playerWidth = playerSprite.getWidth();
        playerHeight = playerSprite.getHeight();

        rectX = (float) ((GameWindow.size.getWidth() / 2) - playerWidth / 2);
        rectY = (float) (GameWindow.size.getHeight() - 200);
    }

}
