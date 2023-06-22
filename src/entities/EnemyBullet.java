package entities;


import java.awt.*;
import java.awt.image.BufferedImage;

public class EnemyBullet extends Entity {

    public static BufferedImage enemyBulletSprite;
    public static int height;
    public static int width;


    public void MoveEnemyBullet() {

        float speed = 1f;
        y += speed;
    }

    public EnemyBullet(float x, float y) {
        super(x, y);

        width = enemyBulletSprite.getWidth();
        height = enemyBulletSprite.getHeight();

        this.x = x + width / 2;
        this.y = y + height;
    }

    public void Render(Graphics graphics) {

        graphics.drawImage(enemyBulletSprite, (int) x,(int) y - 30, null);
    }

    public float GetPosX() {
        return x;
    }

    public float GetPosY() {
        return y;
    }
}
