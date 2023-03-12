package entities;

import java.awt.*;

public class Bullet {
    private final int xrage = 10;
    public float x;
    public float y;
    public int height = (int) 2.5f * xrage, width = xrage;


    public Bullet(float playerX, float playerY, int playerWidth) {

        x = (playerX + playerWidth / 2) - (width / 2);
        y = playerY;
    }
    public void PaintBullet(Graphics graphics) {

        graphics.setColor(Color.green);
        graphics.fillOval((int) x, (int) y - 30, width, height);
    }
    public void MoveBullet() {

        float speed = 8f;
        y -= speed;
    }
}
