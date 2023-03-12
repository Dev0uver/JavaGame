package entities;


import java.awt.*;

public class Enemy {
    public int height = 50;
    public int width = 100;
    public float xPosition;
    public float yPosition;

    public float speed = 2f;

    public int jumpDown = 30;

    public Enemy(int sector, int number, int row) {

        int numOfSector = (number + 1) % row;

        xPosition = (sector * numOfSector);
        yPosition =  (number / row) * (height + 10);
    }

    public void PaintEnemy(Graphics graphics) {

        graphics.setColor(Color.red);
        graphics.fillRect((int) xPosition, (int) yPosition, width, height);
    }
}
