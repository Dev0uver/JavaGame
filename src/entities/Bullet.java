package entities;


import java.awt.*;
import java.awt.image.BufferedImage;

public class Bullet extends Entity {

    public static BufferedImage bulletSprite;
    public static int height;
    public static int width;


    public Bullet(float x, float y) {
        super(x, y);

        width = bulletSprite.getWidth();
        height = bulletSprite.getHeight();

        this.x = (x + Player.playerSprite.getWidth() / 2) - (width / 2);
        this.y = y;
    }

    public void Render(Graphics graphics) {

        graphics.drawImage(bulletSprite, (int) x,(int) y - 30, null);
    }

    public void MoveBullet() {

        float speed = 4f;
        y -= speed;
    }


    public float GetPosX() {
        return x;
    }
    public float GetPosY() {
        return y;
    }
}
