package entities;


import GUI.Score;
import audio.Audio;
import java.awt.*;
import java.awt.image.BufferedImage;


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

    public void Death() {

        Audio.Death();
        Score.score++;
    }
}
