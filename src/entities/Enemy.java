package entities;


import GUI.Score;
import audio.Audio;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Enemy extends Entity {
    public static int height;
    public static int width;

    public static BufferedImage enemySprite;

    public float speed = 1f;

    public int jumpDown = 15;

    public static boolean direction = true;

    public Enemy(float x, float y) {

        super(x, y);

        width = enemySprite.getWidth();
        height = enemySprite.getHeight();

    }

    public void Render(Graphics graphics) {

        graphics.drawImage(enemySprite, (int) x, (int) y,null);
    }

    public void JumpDown() {
        y += jumpDown;
    }

    public void Move() {
        if (direction) {
            x += speed;
        }
        else {
            x -= speed;
        }
    }

    public void Death(Score score) {

        Audio.Death();
        score.score++;
    }

    public float GetPosX() {
        return x;
    }
    public void SetPosX(float x) {
        this.x = x;
    }

    public float GetPosY() {
        return y;
    }

    public void SetPosY(float y) {
        this.y = y;
    }
}
