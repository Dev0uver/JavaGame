package entities;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Bullet {

    public static BufferedImage bulletSprite;
    public float x;
    public float y;
    public static int height;
    public static int width;


    public Bullet(float playerX, float playerY, int playerWidth) {

        width = bulletSprite.getWidth();
        height = bulletSprite.getHeight();

        x = (playerX + playerWidth / 2) - (width / 2);
        y = playerY;
    }


    public void PaintBullet(Graphics graphics) {

        //graphics.setColor(Color.green);
        graphics.drawImage(bulletSprite, (int) x,(int) y - 30, null);
    }
    public void MoveBullet() {

        float speed = 8f;
        y -= speed;
    }
}
