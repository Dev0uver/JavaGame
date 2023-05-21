package entities;

import main.GameWindow;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Player extends Entity {

    public static int playerWidth;
    public static int playerHeight;

    public boolean left, right, shooting;

    public static BufferedImage playerSprite;

    // начальные координаты игрока

    public long lastCheck;

    public Player(float x, float y) {

        super(x, y);

        playerWidth = playerSprite.getWidth();
        playerHeight = playerSprite.getHeight();
    }

    public void Render(Graphics graphics) {

        graphics.drawImage(playerSprite, (int) x, (int) y, null);
    }

    // Изменение координаты x игрок
    public void UpdatePos() {

        float velX = 3f;
        if (left && !right && x > 0) {
            x -= velX;
        }
        else if (right && !left && (x + playerWidth) <= GameWindow.size.getWidth()) {
            x += velX;
        }

    }

    public void ResetDirBooleans() {
        left = false;
        right = false;
        shooting = false;
    }

    public boolean IsLeft() {
        return left;
    }

    public void SetLeft(boolean left) {
        this.left = left;
    }

    public boolean IsRight() {
        return right;
    }

    public void SetRight(boolean right) {
        this.right = right;
    }

    public boolean IsShooting() {
        return shooting;
    }

    public void SetShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public float GetPosX() {
        return x;
    }

    public float GetPosY() {
        return y;
    }
}
