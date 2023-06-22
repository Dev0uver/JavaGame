package main;


import buttons.*;
import entities.Bullet;
import entities.Enemy;
import entities.EnemyBullet;
import entities.Player;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class Initializer {

    public static void Initialization() {

        InitEntitySprites();
        InitButtonSprites();
    }


    private static void InitEntitySprites() {

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
        inputStream = EnemyBullet.class.getResourceAsStream("/Assets/Sprites/EnemyBullet.png");
        try {
            if (inputStream != null) {
                EnemyBullet.enemyBulletSprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static void InitButtonSprites() {

        InputStream inputStream = PlayButton.class.getResourceAsStream("/Assets/Sprites/Play.png");
        try {
            if (inputStream != null) {
                PlayButton.sprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        PlayButton.width = PlayButton.sprite.getWidth();
        PlayButton.height = PlayButton.sprite.getHeight();

        inputStream = ExitButton.class.getResourceAsStream("/Assets/Sprites/Exit.png");
        try {
            if (inputStream != null) {
                ExitButton.sprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ExitButton.width = ExitButton.sprite.getWidth();
        ExitButton.height = ExitButton.sprite.getHeight();

        inputStream = ExitButton.class.getResourceAsStream("/Assets/Sprites/Retry.png");
        try {
            if (inputStream != null) {
                RetryButton.sprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        RetryButton.width = RetryButton.sprite.getWidth();
        RetryButton.height = RetryButton.sprite.getHeight();

        inputStream = SettingsButton.class.getResourceAsStream("/Assets/Sprites/Settings.png");
        try {
            if (inputStream != null) {
                SettingsButton.sprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        SettingsButton.width = SettingsButton.sprite.getWidth();
        SettingsButton.height = SettingsButton.sprite.getHeight();

        inputStream = IncreaseVolume.class.getResourceAsStream("/Assets/Sprites/Increase.png");
        try {
            if (inputStream != null) {
                IncreaseVolume.sprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        IncreaseVolume.width = IncreaseVolume.sprite.getWidth();
        IncreaseVolume.height = IncreaseVolume.sprite.getHeight();

        inputStream = ReducedVolume.class.getResourceAsStream("/Assets/Sprites/Reduce.png");
        try {
            if (inputStream != null) {
                ReducedVolume.sprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ReducedVolume.width = ReducedVolume.sprite.getWidth();
        ReducedVolume.height = ReducedVolume.sprite.getHeight();

        inputStream = BackButton.class.getResourceAsStream("/Assets/Sprites/Back.png");
        try {
            if (inputStream != null) {
                BackButton.sprite = ImageIO.read(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BackButton.width = BackButton.sprite.getWidth();
        BackButton.height = BackButton.sprite.getHeight();

    }
}
