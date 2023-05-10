package entities;

import main.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


public class Player {

    public int playerWidth;
    public int playerHeight;

    private BufferedImage playerSprite;

    //public static setSprite() {

    //}

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

        ImportSprite();

        playerWidth = playerSprite.getWidth();
        playerHeight = playerSprite.getHeight();

        rectX = (float) ((GameWindow.size.getWidth() / 2) - playerWidth / 2);
        rectY = (float) (GameWindow.size.getHeight() - 200);
    }

    private void ImportSprite() {

        InputStream inputStream = getClass().getResourceAsStream("/Assets/Sprites/Player.png");
        try {
            if (inputStream != null) {
                playerSprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
