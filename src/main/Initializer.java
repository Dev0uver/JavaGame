package main;


import entities.Bullet;
import entities.Enemy;
import entities.Player;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class Initializer {

    public static void Initialization() {

        InitSprites();
    }

    private static void InitSprites() {

        InputStream inputStream = GamePanel.class.getResourceAsStream("/Assets/Sprites/background.png");
        try {
            if (inputStream != null) {
                GamePanel.backgroundSprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        inputStream = Player.class.getResourceAsStream("/Assets/Sprites/Player.png");
        try {
            if (inputStream != null) {
                Player.playerSprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        inputStream = Enemy.class.getResourceAsStream("/Assets/Sprites/Enemy.png");
        try {
            if (inputStream != null) {
                Enemy.enemySprite = ImageIO.read(inputStream);
            }
        }
        catch (IOException e) {
            throw new RuntimeException();
        }

        inputStream = Bullet.class.getResourceAsStream("/Assets/Sprites/Bullet.png");
        try {
            if (inputStream != null) {
                Bullet.bulletSprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
