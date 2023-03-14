package entities;

import main.GameWindow;

import java.awt.*;

public class Player {

    public int playerWidth = 80;
    public int playerHeight = 100;

    // начальные координаты игрока

    public float rectX = (float) ((GameWindow.width / 2) - playerWidth / 2);
    public final float rectY = (float) (GameWindow.height - 120);

    // скорость перемещения игрока
    private float velX;

    public void PaintPlayer(Graphics graphics) {

        graphics.setColor(Color.black);
        graphics.fillRect((int) rectX, (int) rectY, playerWidth, playerHeight);
    }

    // Изменение координаты x игрока
    public void MovePlayer() {

        if (0 <= rectX + velX && rectX + velX <= GameWindow.width - playerWidth) {

            rectX += velX;
        }

    }
    // set для velX
    public void setVelX(float velX) {

        this.velX = velX;
    }

}
