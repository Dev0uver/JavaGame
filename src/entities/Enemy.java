package entities;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Enemy {
    public static int height;
    public static int width;

    public static BufferedImage enemySprite;
    public float xPosition;
    public float yPosition;

    public float speed = 2f;

    public int jumpDown = 30;

    public Enemy(int sector, int number, int row) {

        width = enemySprite.getWidth();
        height = enemySprite.getHeight();

        int numOfSector = (number + 1) % row;

        xPosition = (sector * numOfSector);
        yPosition =  (number / row) * (height + 10) + 100;
    }

    public void PaintEnemy(Graphics graphics) {

        graphics.drawImage(enemySprite, (int) xPosition, (int) yPosition,null);
    }

}
