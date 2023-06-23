package main;

import entities.Player;

import java.awt.*;

public class GameSettings {

    private int playerLives = 3;
    private int hp = 3;
    private float playerSpeed = 3f;
    private float enemySpeed = 1f;

    public static Font GUIFont = new Font("Courier", Font.BOLD, 30);


    public void ResetVariables() {

        enemySpeed = 1f;
        playerSpeed = 3f;
        hp = 3;
    }

    public void SpeedUp() {

        enemySpeed += enemySpeed * 0.02f;
        playerSpeed += playerSpeed * 0.01f;
    }

    public void RenderLives(Graphics graphics) {

        graphics.setFont(GUIFont);
        graphics.setColor(Color.white);
        for (int i = 0; i < playerLives; i++) {
            graphics.drawImage(Player.playerSprite, (int) (i * Player.playerWidth * 1.2), 0, null);
        }
    }

    public void RenderHP(Graphics graphics) {

        graphics.setFont(GUIFont);
        graphics.setColor(Color.white);
        graphics.drawString("HP: " + hp, (int) (3 * Player.playerWidth * 1.2), GUIFont.getSize());
    }

    public int GetPlayerLives() {
        return playerLives;
    }
    public void SetPlayerLives(int playerLives) {
        this.playerLives = playerLives;
    }

    public int GetHP() {
        return hp;
    }
    public void SetHP(int hp) {
        this.hp = hp;
    }

    public float GetPlayerSpeed() {
        return playerSpeed;
    }
    public float GetEnemySpeed() {
        return enemySpeed;
    }
}
